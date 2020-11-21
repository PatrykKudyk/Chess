package com.partos.chess.logic

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import com.partos.chess.R
import com.partos.chess.logic.computer.BestMoveComputer
import com.partos.chess.logic.computer.RandomMoveComputer
import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.GameHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.models.GameFlags
import com.partos.chess.models.Move
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.*
import kotlin.random.Random

class UserInteractionLogic {

    private lateinit var backButton: ImageView
    private lateinit var chooseLayout: LinearLayout
    private lateinit var chooseBishop: ImageView
    private lateinit var chooseKnight: ImageView
    private lateinit var chooseRook: ImageView
    private lateinit var chooseQueen: ImageView
    private lateinit var movesList: Array<Array<Boolean>>
    private lateinit var board: Array<Array<ImageView>>
    private lateinit var moves: Array<Array<ImageView>>
    private lateinit var piecesList: ArrayList<Piece>
    private lateinit var context: Context
    private lateinit var rootView: View
    private lateinit var pieceFocused: Piece
    private lateinit var gameFlags: GameFlags

    private var moveX = 0
    private var moveY = 0
    private var turn = 0
    private var pawnSpecialX = 0
    private var pawnSpecialY = 0
    private var movesWithNoCaptureWhite = 0
    private var movesWithNoCaptureBlack = 0
    private var endOfGame = false
    private var computer1Type = 0
    private var computer2Type = 0

    private var computer1Turn = false
    private var canComputerMove = true
    private var whiteComp = 0
    private var blackComp = 0

    fun initGame(
        rootView: View,
        fragmentManager: FragmentManager,
        board: Array<Array<ImageView>>,
        moves: Array<Array<ImageView>>,
        piecesList: ArrayList<Piece>,
        gameType: Int,
        computerType1: Int,
        computerType2: Int
    ) {
        initializeGame(
            board,
            moves,
            piecesList,
            rootView,
            fragmentManager,
            gameType,
            computerType1,
            computerType2
        )
        attachPiecesListeners(gameType, computerType1, rootView)
    }

    private fun initializeGame(
        board: Array<Array<ImageView>>,
        moves: Array<Array<ImageView>>,
        piecesList: ArrayList<Piece>,
        rootView: View,
        fragmentManager: FragmentManager,
        gameType: Int,
        computer1Type: Int,
        computer2Type: Int
    ) {
        this.computer1Type = computer1Type
        this.computer2Type = computer2Type
        setGameFlags()
        attachParameters(board, moves, piecesList, rootView.context)
        attachViews(rootView)
        attachBaseListeners(fragmentManager)
        createMovesList()
        checkGameType(gameType, computer1Type, computer2Type)
    }

    private fun setGameFlags() {
        gameFlags = GameFlags(
            canCastleLongBlack = true,
            canCastleLongWhite = true,
            canCastleShortBlack = true,
            canCastleShortWhite = true,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false,
            playerTurn = false,
            didPlayerMoved = false,
            isChoose = false,
            pawnSpecialWhite = false,
            pawnSpecialBlack = false,
            checkBlack = false,
            checkWhite = false
        )
    }

    private fun checkGameType(gameType: Int, computer1Type: Int, computer2Type: Int) {
        if (gameType == 1) {
            val rand = Random.nextInt(0, 2)
            gameFlags.playerTurn = rand == 0
            if (!gameFlags.playerTurn) {
                Handler().postDelayed({
                    handleComputerMove(computer1Type)
                }, 1000)
            }
        } else if (gameType == 2) {
            val rand = Random.nextInt(0, 2)
            computer1Turn = rand == 0
            Handler().postDelayed({
                if (computer1Turn) {
                    whiteComp = computer1Type
                    blackComp = computer2Type
                } else {
                    whiteComp = computer2Type
                    blackComp = computer1Type
                }
                handleComputerVsComputerMove(whiteComp)
            }, 2000)
        }
    }

    private fun attachParameters(
        board: Array<Array<ImageView>>,
        moves: Array<Array<ImageView>>,
        piecesList: java.util.ArrayList<Piece>,
        context: Context
    ) {
        this.board = board
        this.moves = moves
        this.piecesList = piecesList
        this.context = context
        pieceFocused = Piece(0, 0, 0, 0, false)
    }

    private fun createMovesList() {
        movesList = MovesHelper().createMovesList()
    }

    private fun attachPiecesListeners(gameType: Int, computerType: Int, rootView: View) {
        for (i in 0..7) {
            for (j in 0..7) {
                board[i][j].setOnClickListener {
                    when (gameType) {
                        0 -> {
                            handlePlayerMove(i, j, rootView)
                        }
                        1 -> {
                            if (gameFlags.playerTurn) {
                                handlePlayerMove(i, j, rootView)
                                if (gameFlags.didPlayerMoved && !endOfGame) {
                                    gameFlags.playerTurn = false
                                    handleComputerMove(computerType)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun handleComputerMove(computerType: Int) {
        lateinit var params: ComputerMoveParameters
        when (computerType) {
            0 -> params = RandomMoveComputer().makeRandomMove(createBaseParametersGroup(), turn)
            1 -> params = BestMoveComputer().makeBestMove(createBaseParametersGroup(), turn, 0)
        }
        Handler().postDelayed({
            pieceFocused = params.move.piece
            checkFlagsFromComputerMove(params)
            endOfGame = GameHelper().checkChecks(createBaseParametersGroup(), rootView)
            if (!endOfGame) {
                endOfGame = checkEndOfGame(rootView)
            }
            gameFlags.playerTurn = true
        }, 250)
    }

    private fun handleComputerVsComputerMove(computerType: Int) {
        lateinit var params: ComputerMoveParameters
        when (computerType) {
            0 -> params = RandomMoveComputer().makeRandomMove(createBaseParametersGroup(), turn)
            1 -> params = BestMoveComputer().makeBestMove(createBaseParametersGroup(), turn, 0)
        }
        Handler().postDelayed({
            pieceFocused = params.move.piece
            checkFlagsFromComputerMove(params)
            endOfGame = GameHelper().checkChecks(createBaseParametersGroup(), rootView)
            if (!endOfGame) {
                endOfGame = checkEndOfGame(rootView)
            }
            if (!endOfGame){
                if (turn == 0) {
                    handleComputerVsComputerMove(whiteComp)
                } else {
                    handleComputerVsComputerMove(blackComp)
                }
            }
        }, 250)
    }

    private fun checkFlagsFromComputerMove(params: ComputerMoveParameters) {
        val positionX = params.move.positionX
        val positionY = params.move.positionY
        if (isEnPassantWhite(positionY, positionX)) {
            makeWhiteEnPassantMove()
        } else if (isEnPassantBlack(positionY, positionX)) {
            makeBlackEnPassantMove()
        } else if (isSpecialBlackPawnMove(positionY)) {
            makeSpecialBlackPawnMove(positionY, positionX)
        } else if (isSpecialWhitePawnMove(positionY)) {
            makeSpecialWhitePawnMove(positionY, positionX)
        } else if (isWhiteKingLongCastle(positionY, positionX)) {
            makeWhiteKingLongCastleMove(positionY, positionX)
        } else if (isWhiteKingShortCastle(positionY, positionX)) {
            makeWhiteKingShortCastleMove(positionY, positionX)
        } else if (isBlackKingLongCastle(positionY, positionX)) {
            makeBlackKingLongCastleMove(positionY, positionX)
        } else if (isBlackKingShortCastle(positionY, positionX)) {
            makeBlackKingShortCastleMove(positionY, positionX)
        } else if (pieceFocused.type == 5 && pieceFocused.color == 0) {
            resetWhiteKingCastlePossibilities()
            makeMove(positionY, positionX)
            if (!endOfGame) {
                endOfGame = checkEndOfGame(rootView)
            }
            gameFlags.didPlayerMoved = true
        } else if (pieceFocused.type == 3 && pieceFocused.color == 0 && pieceFocused.positionY == 7 && pieceFocused.positionX == 0) {
            gameFlags.canCastleLongWhite = false
            makeMove(positionY, positionX)
            if (!endOfGame) {
                endOfGame = checkEndOfGame(rootView)
            }
            gameFlags.didPlayerMoved = true
        } else if (pieceFocused.type == 3 && pieceFocused.color == 0 && pieceFocused.positionY == 7 && pieceFocused.positionX == 7) {
            gameFlags.canCastleShortWhite = false
            makeMove(positionY, positionX)
            if (!endOfGame) {
                endOfGame = checkEndOfGame(rootView)
            }
            gameFlags.didPlayerMoved = true
        } else if (pieceFocused.type == 5 && pieceFocused.color == 1) {
            resetBlackKingCastlePossibilities()
            makeMove(positionY, positionX)
            if (!endOfGame) {
                endOfGame = checkEndOfGame(rootView)
            }
            gameFlags.didPlayerMoved = true
        } else if (pieceFocused.type == 3 && pieceFocused.color == 1 && pieceFocused.positionY == 0 && pieceFocused.positionX == 0) {
            gameFlags.canCastleLongBlack = false
            makeMove(positionY, positionX)
            if (!endOfGame) {
                endOfGame = checkEndOfGame(rootView)
            }
            gameFlags.didPlayerMoved = true
        } else if (pieceFocused.type == 3 && pieceFocused.color == 1 && pieceFocused.positionY == 0 && pieceFocused.positionX == 7) {
            gameFlags.canCastleShortBlack = false
            makeMove(positionY, positionX)
            if (!endOfGame) {
                endOfGame = checkEndOfGame(rootView)
            }
            gameFlags.didPlayerMoved = true
        } else {
            makeComputerMove(params.move)
        }
    }

    private fun handlePlayerMove(positionY: Int, positionX: Int, rootView: View) {
        if (isMove(positionY, positionX)) {
            if (gameFlags.isChoose) {
                moveX = positionX
                moveY = positionY
                chooseLayout.visibility = View.VISIBLE
                if (pieceFocused.color == 0) {
                    setChoiceWhite()
                } else {
                    setChoiceBlack()
                }
            } else if (isEnPassantWhite(positionY, positionX)) {
                makeWhiteEnPassantMove()
            } else if (isEnPassantBlack(positionY, positionX)) {
                makeBlackEnPassantMove()
            } else if (isSpecialBlackPawnMove(positionY)) {
                makeSpecialBlackPawnMove(positionY, positionX)
            } else if (isSpecialWhitePawnMove(positionY)) {
                makeSpecialWhitePawnMove(positionY, positionX)
            } else if (isWhiteKingLongCastle(positionY, positionX)) {
                makeWhiteKingLongCastleMove(positionY, positionX)
            } else if (isWhiteKingShortCastle(positionY, positionX)) {
                makeWhiteKingShortCastleMove(positionY, positionX)
            } else if (isBlackKingLongCastle(positionY, positionX)) {
                makeBlackKingLongCastleMove(positionY, positionX)
            } else if (isBlackKingShortCastle(positionY, positionX)) {
                makeBlackKingShortCastleMove(positionY, positionX)
            } else if (pieceFocused.type == 5 && pieceFocused.color == 0) {
                resetWhiteKingCastlePossibilities()
                makeMove(positionY, positionX)
                if (!endOfGame) {
                    endOfGame = checkEndOfGame(rootView)
                }
                gameFlags.didPlayerMoved = true
            } else if (pieceFocused.type == 3 && pieceFocused.color == 0 && pieceFocused.positionY == 7 && pieceFocused.positionX == 0) {
                gameFlags.canCastleLongWhite = false
                makeMove(positionY, positionX)
                if (!endOfGame) {
                    endOfGame = checkEndOfGame(rootView)
                }
                gameFlags.didPlayerMoved = true
            } else if (pieceFocused.type == 3 && pieceFocused.color == 0 && pieceFocused.positionY == 7 && pieceFocused.positionX == 7) {
                gameFlags.canCastleShortWhite = false
                makeMove(positionY, positionX)
                if (!endOfGame) {
                    endOfGame = checkEndOfGame(rootView)
                }
                gameFlags.didPlayerMoved = true
            } else if (pieceFocused.type == 5 && pieceFocused.color == 1) {
                resetBlackKingCastlePossibilities()
                makeMove(positionY, positionX)
                if (!endOfGame) {
                    endOfGame = checkEndOfGame(rootView)
                }
                gameFlags.didPlayerMoved = true
            } else if (pieceFocused.type == 3 && pieceFocused.color == 1 && pieceFocused.positionY == 0 && pieceFocused.positionX == 0) {
                gameFlags.canCastleLongBlack = false
                makeMove(positionY, positionX)
                if (!endOfGame) {
                    endOfGame = checkEndOfGame(rootView)
                }
                gameFlags.didPlayerMoved = true
            } else if (pieceFocused.type == 3 && pieceFocused.color == 1 && pieceFocused.positionY == 0 && pieceFocused.positionX == 7) {
                gameFlags.canCastleShortBlack = false
                makeMove(positionY, positionX)
                if (!endOfGame) {
                    endOfGame = checkEndOfGame(rootView)
                }
                gameFlags.didPlayerMoved = true
            } else {
                makeMove(positionY, positionX)
                if (!endOfGame) {
                    endOfGame = checkEndOfGame(rootView)
                }
                gameFlags.didPlayerMoved = true
            }
        } else if (isPiece(board[positionY][positionX])) {
            gameFlags.isChoose = false
            resetMovesList()
            resetBoard()
            if (findPiece(positionY, positionX).color == turn) {
                pieceFocused = findPiece(positionY, positionX)
                showMoves(pieceFocused)
            }
            gameFlags.didPlayerMoved = false
        } else {
            gameFlags.isChoose = false
            resetMovesList()
            resetBoard()
        }
    }

    private fun makeBlackKingShortCastleMove(positionY: Int, positionX: Int) {
        incrementMovesWithNoCapture(pieceFocused.color)
        resetBlackKingCastlePossibilities()
        changePiecePosition(positionY, positionX, pieceFocused)
        val rook = findPiece(0, 7)
        changePiecePosition(0, 5, rook)
        handleLogic()
    }

    private fun isBlackKingShortCastle(positionY: Int, positionX: Int): Boolean {
        return pieceFocused.type == 5 && pieceFocused.color == 1 && pieceFocused.positionY == 0 && pieceFocused.positionX == 4 && positionY == 0 && positionX == 6
    }

    private fun makeBlackKingLongCastleMove(positionY: Int, positionX: Int) {
        incrementMovesWithNoCapture(pieceFocused.color)
        resetBlackKingCastlePossibilities()
        changePiecePosition(positionY, positionX, pieceFocused)
        val rook = findPiece(0, 0)
        changePiecePosition(0, 3, rook)
        handleLogic()
    }

    private fun resetBlackKingCastlePossibilities() {
        gameFlags.canCastleLongBlack = false
        gameFlags.canCastleShortBlack = false
    }

    private fun isBlackKingLongCastle(positionY: Int, positionX: Int): Boolean {
        return pieceFocused.type == 5 && pieceFocused.color == 1 && pieceFocused.positionY == 0 && pieceFocused.positionX == 4 && positionY == 0 && positionX == 2
    }

    private fun makeWhiteKingShortCastleMove(positionY: Int, positionX: Int) {
        incrementMovesWithNoCapture(pieceFocused.color)
        resetWhiteKingCastlePossibilities()
        changePiecePosition(positionY, positionX, pieceFocused)
        val rook = findPiece(7, 7)
        changePiecePosition(7, 5, rook)
        handleLogic()
    }

    private fun isWhiteKingShortCastle(positionY: Int, positionX: Int): Boolean {
        return pieceFocused.type == 5 && pieceFocused.color == 0 && pieceFocused.positionY == 7 && pieceFocused.positionX == 4 && positionY == 7 && positionX == 6
    }

    private fun makeWhiteKingLongCastleMove(positionY: Int, positionX: Int) {
        incrementMovesWithNoCapture(pieceFocused.color)
        resetWhiteKingCastlePossibilities()
        changePiecePosition(positionY, positionX, pieceFocused)
        val rook = findPiece(7, 0)
        changePiecePosition(7, 3, rook)
        handleLogic()
    }

    private fun handleLogic() {
        resetMovesList()
        resetBoard()
        endOfGame = GameHelper().checkChecks(createBaseParametersGroup(), rootView)
        resetPieceFocused()
        resetSpecialPawnMovesFlags()
        changeTurn()
        if (!endOfGame) {
            endOfGame = checkEndOfGame(rootView)
        }
        gameFlags.didPlayerMoved = true
    }

    private fun changePiecePosition(positionY: Int, positionX: Int, piece: Piece) {
        piecesList.set(
            piecesList.indexOf(piece), Piece(
                piece.type,
                piece.color,
                positionX,
                positionY,
                true
            )
        )
    }

    private fun promotePawn(positionY: Int, positionX: Int) {
        if (isPiece(board[positionY][positionX])) {
            resetMovesWithNoCapture(pieceFocused.color)
            findPiece(positionY, positionX).isActive = false
        } else {
            incrementMovesWithNoCapture(pieceFocused.color)
        }
        changePiecePositionPromotion(positionY, positionX, pieceFocused)
        resetMovesList()
        resetBoard()
        endOfGame = GameHelper().checkChecks(createBaseParametersGroup(), rootView)
        resetPieceFocused()
        resetSpecialPawnMovesFlags()
        changeTurn()
    }

    private fun changePiecePositionPromotion(positionY: Int, positionX: Int, piece: Piece) {
        piecesList.set(
            piecesList.indexOf(piece), Piece(
                4,
                piece.color,
                positionX,
                positionY,
                true
            )
        )
    }

    private fun resetWhiteKingCastlePossibilities() {
        gameFlags.canCastleLongWhite = false
        gameFlags.canCastleShortWhite = false
    }

    private fun resetSpecialPawnMovesFlags() {
        gameFlags.pawnSpecialWhite = false
        gameFlags.pawnSpecialBlack = false
    }

    private fun isWhiteKingLongCastle(positionY: Int, positionX: Int): Boolean {
        return pieceFocused.type == 5 && pieceFocused.color == 0 && pieceFocused.positionY == 7 && pieceFocused.positionX == 4 && positionY == 7 && positionX == 2
    }

    private fun makeSpecialWhitePawnMove(positionY: Int, positionX: Int) {
        incrementMovesWithNoCapture(pieceFocused.color)
        pawnSpecialX = pieceFocused.positionX
        pawnSpecialY = pieceFocused.positionY - 2
        gameFlags.pawnSpecialWhite = true
        changePiecePosition(positionY, positionX, pieceFocused)
        resetMovesList()
        resetBoard()
        endOfGame = GameHelper().checkChecks(createBaseParametersGroup(), rootView)
        resetPieceFocused()
        changeTurn()
        if (!endOfGame) {
            endOfGame = checkEndOfGame(rootView)
        }
        gameFlags.didPlayerMoved = true
    }

    private fun isSpecialWhitePawnMove(positionY: Int): Boolean {
        return positionY == pieceFocused.positionY - 2 && pieceFocused.color == 0 && pieceFocused.type == 0
    }

    private fun makeSpecialBlackPawnMove(positionY: Int, positionX: Int) {
        incrementMovesWithNoCapture(pieceFocused.color)
        pawnSpecialX = pieceFocused.positionX
        pawnSpecialY = pieceFocused.positionY + 2
        gameFlags.pawnSpecialBlack = true
        changePiecePosition(positionY, positionX, pieceFocused)
        resetMovesList()
        resetBoard()
        endOfGame = GameHelper().checkChecks(createBaseParametersGroup(), rootView)
        resetPieceFocused()
        changeTurn()
        if (!endOfGame) {
            endOfGame = checkEndOfGame(rootView)
        }
        gameFlags.didPlayerMoved = true
    }

    private fun isSpecialBlackPawnMove(positionY: Int): Boolean {
        return positionY == pieceFocused.positionY + 2 && pieceFocused.color == 1 && pieceFocused.type == 0
    }

    private fun makeBlackEnPassantMove() {
        findPiece(pawnSpecialY, pawnSpecialX).isActive = false
        resetMovesWithNoCapture(pieceFocused.color)
        changePiecePosition(pawnSpecialY + 1, pawnSpecialX, pieceFocused)
        resetMovesList()
        resetBoard()
        endOfGame = GameHelper().checkChecks(createBaseParametersGroup(), rootView)
        resetPieceFocused()
        gameFlags.pawnSpecialWhite = false
        changeTurn()
        if (!endOfGame) {
            endOfGame = checkEndOfGame(rootView)
        }
        gameFlags.didPlayerMoved = true
    }

    private fun resetPieceFocused() {
        pieceFocused = Piece(0, 0, 0, 0, false)
    }

    private fun isEnPassantBlack(positionY: Int, positionX: Int): Boolean {
        return pieceFocused.color == 1 && gameFlags.pawnSpecialWhite && positionY == pawnSpecialY + 1 && positionX == pawnSpecialX && pieceFocused.type == 0
    }

    private fun makeWhiteEnPassantMove() {
        findPiece(pawnSpecialY, pawnSpecialX).isActive = false
        resetMovesWithNoCapture(pieceFocused.color)
        changePiecePosition(pawnSpecialY - 1, pawnSpecialX, pieceFocused)
        resetMovesList()
        resetBoard()
        endOfGame = GameHelper().checkChecks(createBaseParametersGroup(), rootView)
        resetPieceFocused()
        gameFlags.pawnSpecialBlack = false
        changeTurn()
        if (!endOfGame) {
            endOfGame = checkEndOfGame(rootView)
        }
        gameFlags.didPlayerMoved = true
    }

    private fun checkEndOfGame(rootView: View): Boolean {
        return GameHelper().checkEndOfGame(
            TakenEndGameParameters(
                createBaseParametersGroup(),
                rootView,
                movesWithNoCaptureBlack,
                movesWithNoCaptureWhite,
                turn
            )
        )
    }

    private fun changeTurn() {
        turn = if (turn == 0) {
            1
        } else {
            0
        }
    }

    private fun isEnPassantWhite(positionY: Int, positionX: Int): Boolean {
        return pieceFocused.color == 0 && gameFlags.pawnSpecialBlack && positionY == pawnSpecialY - 1 && positionX == pawnSpecialX && pieceFocused.type == 0
    }

    private fun setChoiceBlack() {
        chooseBishop.setImageDrawable(context.getDrawable(R.drawable.bishop_black))
        chooseKnight.setImageDrawable(context.getDrawable(R.drawable.knight_black))
        chooseRook.setImageDrawable(context.getDrawable(R.drawable.rook_black))
        chooseQueen.setImageDrawable(context.getDrawable(R.drawable.queen_black))
    }

    private fun setChoiceWhite() {
        chooseBishop.setImageDrawable(context.getDrawable(R.drawable.bishop_white))
        chooseKnight.setImageDrawable(context.getDrawable(R.drawable.knight_white))
        chooseRook.setImageDrawable(context.getDrawable(R.drawable.rook_white))
        chooseQueen.setImageDrawable(context.getDrawable(R.drawable.queen_white))
    }

    private fun makeComputerMove(move: Move) {
        if (isBlackPromotion(move.piece) || isWhitePromotion(move.piece)) {
            promotePawn(move.positionY, move.positionX)
        } else {
            pieceFocused = findPiece(pieceFocused.positionY, pieceFocused.positionX)
            makeMove(move.positionY, move.positionX)
        }
    }

    private fun isWhitePromotion(piece: Piece): Boolean {
        return piece.color == 0 && piece.type == 0 && piece.positionY == 1
    }

    private fun isBlackPromotion(piece: Piece): Boolean {
        return piece.color == 1 && piece.type == 0 && piece.positionY == 6
    }

    private fun makeMove(positionY: Int, positionX: Int) {
        if (isPiece(board[positionY][positionX])) {
            resetMovesWithNoCapture(pieceFocused.color)
            findPiece(positionY, positionX).isActive = false
        } else {
            incrementMovesWithNoCapture(pieceFocused.color)
        }
        pieceFocused = findPiece(pieceFocused.positionY, pieceFocused.positionX)
        changePiecePosition(positionY, positionX, pieceFocused)
        resetMovesList()
        resetBoard()
        endOfGame = GameHelper().checkChecks(createBaseParametersGroup(), rootView)
        resetPieceFocused()
        resetSpecialPawnMovesFlags()
        changeTurn()
    }


    private fun showMoves(pieceFocused: Piece) {
        when (pieceFocused.type) {
            0 -> {
                if (pieceFocused.color == 0) {
                    val afterMovePawnHelper =
                        PiecesHelper().showPieceMoves(createBaseParametersGroup())
                    gameFlags.isChoose = afterMovePawnHelper.isChoose
                    movesList = afterMovePawnHelper.moves
                } else {
                    val afterMovePawnHelper =
                        PiecesHelper().showPieceMoves(createBaseParametersGroup())
                    gameFlags.isChoose = afterMovePawnHelper.isChoose
                    movesList = afterMovePawnHelper.moves
                }
            }
            else -> {
                val afterMovePawnHelper =
                    PiecesHelper().showPieceMoves(createBaseParametersGroup())
                movesList = afterMovePawnHelper.moves
                gameFlags.longBlackCastleAvailable = afterMovePawnHelper.longBlackCastleAvailable
                gameFlags.shortBlackCastleAvailable = afterMovePawnHelper.shortBlackCastleAvailable
                gameFlags.shortWhiteCastleAvailable = afterMovePawnHelper.shortWhiteCastleAvailable
                gameFlags.longWhiteCastleAvailable = afterMovePawnHelper.longWhiteCastleAvailable
            }
        }
        resetMoves()
    }

    private fun resetMoves() {
        for (i in 0..7) {
            for (j in 0..7) {
                if (movesList[i][j]) {
                    moves[i][j].visibility = View.VISIBLE
                } else {
                    moves[i][j].visibility = View.GONE
                }
            }
        }
    }

    private fun resetMovesList() {
        for (i in 0..7) {
            for (j in 0..7) {
                movesList[i][j] = false
            }
        }
    }

    private fun isMove(i: Int, j: Int): Boolean {
        if (movesList[i][j]) {
            return true
        }
        return false
    }

    private fun resetBoard() {
        chooseLayout.visibility = View.GONE
        for (array in board) {
            for (image in array) {
                image.setImageDrawable(null)
            }
        }
        for (array in moves) {
            for (image in array) {
                image.visibility = View.GONE
            }
        }
        for (piece in piecesList) {
            if (piece.isActive) {
                BoardHelper().drawPiece(piece, board, context)
            }
        }
    }

    private fun isPiece(image: ImageView): Boolean {
        if (image.drawable != null) {
            return true
        }
        return false
    }

    private fun attachBaseListeners(fragmentManager: FragmentManager) {
        backButton.setOnClickListener {
            fragmentManager
                .popBackStack()
        }
        chooseBishop.setOnClickListener {
            setChoiceListener(type = 1)
        }
        chooseKnight.setOnClickListener {
            setChoiceListener(type = 2)
        }
        chooseRook.setOnClickListener {
            setChoiceListener(type = 3)
        }
        chooseQueen.setOnClickListener {
            setChoiceListener(type = 4)
        }
    }

    private fun setChoiceListener(type: Int) {
        pieceFocused.type = type
        if (isPiece(board[moveY][moveX])) {
            resetMovesWithNoCapture(pieceFocused.color)
            findPiece(moveY, moveX).isActive = false
        } else {
            incrementMovesWithNoCapture(pieceFocused.color)
        }
        changePiecePosition(moveY, moveX, pieceFocused)
        resetBoard()
        endOfGame = GameHelper().checkChecks(createBaseParametersGroup(), rootView)
        resetPieceFocused()
        gameFlags.isChoose = false
        resetSpecialPawnMovesFlags()
        changeTurn()
        gameFlags.didPlayerMoved = true
        gameFlags.playerTurn = false
        handleComputerMove(computer1Type)
    }

    private fun incrementMovesWithNoCapture(color: Int) {
        if (color == 0) {
            movesWithNoCaptureWhite++
        } else {
            movesWithNoCaptureBlack++
        }
    }

    private fun resetMovesWithNoCapture(color: Int) {
        if (color == 0) {
            movesWithNoCaptureWhite = 0
        } else {
            movesWithNoCaptureBlack = 0
        }
    }

    private fun findPiece(positionY: Int, positionX: Int): Piece {
        for (piece in piecesList) {
            if (piece.isActive) {
                if (piece.positionX == positionX && piece.positionY == positionY) {
                    return piece
                }
            }
        }
        return Piece(0, 0, 0, 0, false)
    }

    private fun attachViews(rootView: View) {
        this.rootView = rootView
        backButton = rootView.findViewById(R.id.board_image_back)
        chooseLayout = rootView.findViewById(R.id.board_choose_layout)
        chooseBishop = rootView.findViewById(R.id.board_choose_bishop)
        chooseKnight = rootView.findViewById(R.id.board_choose_knight)
        chooseRook = rootView.findViewById(R.id.board_choose_rook)
        chooseQueen = rootView.findViewById(R.id.board_choose_queen)
    }

    private fun createBaseParametersGroup(): BaseParametersGroup {
        return BaseParametersGroup(
            PieceParameters(
                pieceFocused,
                board,
                movesList,
                piecesList,
                context
            ),
            gameFlags,
            PawnBeforeMoveParameters(
                pawnSpecialX,
                pawnSpecialY,
                gameFlags.pawnSpecialBlack,
                gameFlags.pawnSpecialWhite
            )
        )
    }
}