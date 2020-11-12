package com.partos.chess.logic.listeners

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.partos.chess.R
import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.GameHelper
import com.partos.chess.models.GameFlags
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.logic.logic.GameLogic
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.PawnBeforeMoveParameters
import com.partos.chess.models.parameters.PieceParameters
import com.partos.chess.models.parameters.TakenEndGameParameters
import kotlin.math.abs
import kotlin.random.Random

class BoardListeners {

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

    fun initListeners(
        rootView: View,
        fragmentManager: FragmentManager,
        board: Array<Array<ImageView>>,
        moves: Array<Array<ImageView>>,
        piecesList: ArrayList<Piece>,
        gameType: Int,
        computerType: Int
    ) {
        initializeGame(board, moves, piecesList, rootView, fragmentManager, gameType, computerType)
        attachPiecesListeners(gameType, computerType, rootView)
    }

    private fun initializeGame(
        board: Array<Array<ImageView>>,
        moves: Array<Array<ImageView>>,
        piecesList: ArrayList<Piece>,
        rootView: View,
        fragmentManager: FragmentManager,
        gameType: Int,
        computerType: Int
    ) {
        setGameFlags()
        attachParameters(board, moves, piecesList, rootView.context)
        attachViews(rootView)
        attachBaseListeners(fragmentManager)
        createMovesList()
        checkGameType(gameType, computerType)
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

    private fun checkGameType(gameType: Int, computerType: Int) {
        if (gameType == 1) {
            val rand = Random.nextInt(0, 2)
            gameFlags.playerTurn = rand == 0
            if (!gameFlags.playerTurn) {
                Handler().postDelayed({
                    handleComputerMove(computerType)
                }, 2000)
            }
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
        val array1 = arrayOf(false, false, false, false, false, false, false, false)
        val array2 = arrayOf(false, false, false, false, false, false, false, false)
        val array3 = arrayOf(false, false, false, false, false, false, false, false)
        val array4 = arrayOf(false, false, false, false, false, false, false, false)
        val array5 = arrayOf(false, false, false, false, false, false, false, false)
        val array6 = arrayOf(false, false, false, false, false, false, false, false)
        val array7 = arrayOf(false, false, false, false, false, false, false, false)
        val array8 = arrayOf(false, false, false, false, false, false, false, false)
        movesList = arrayOf(array1, array2, array3, array4, array5, array6, array7, array8)
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
                                if (gameFlags.didPlayerMoved) {
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
        when (computerType) {
            0 -> {
                makeRandomComputerMove()
            }
        }
        gameFlags.playerTurn = true
    }

    private fun makeRandomComputerMove() {
        val availablePieces = getAvailablePieces()
        var piece: Piece
        do {
            piece = availablePieces.get(Random.nextInt(0, availablePieces.size))
        } while (!PiecesHelper().canPieceMakeMove(piecesList, piece, board, context))
        Handler().postDelayed({
            makeRandomMove(piece)
        }, 700)
    }

    private fun makeRandomMove(piece: Piece) {
        val movesAvailable = PiecesHelper().getPossibleMoves(piecesList, piece, board)
        val rand = Random.nextInt(0, movesAvailable.size)
        pieceFocused = piece
        makeMove(movesAvailable[rand].positionY, movesAvailable[rand].positionX)
    }

    private fun getAvailablePieces(): ArrayList<Piece> {
        val availablePieces = ArrayList<Piece>()
        for (piece in piecesList) {
            if (piece.isActive && piece.color == turn) {
                availablePieces.add(piece.copy())
            }
        }
        return availablePieces
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
                GameHelper().checkEndOfGame(TakenEndGameParameters(
                    PieceParameters(
                        pieceFocused,
                        board,
                        movesList,
                        piecesList,
                        context
                    ),
                    gameFlags,
                    rootView,
                    movesWithNoCaptureBlack,
                    movesWithNoCaptureWhite,
                    turn
                ))
                gameFlags.didPlayerMoved = true
            } else if (pieceFocused.type == 3 && pieceFocused.color == 0 && pieceFocused.positionY == 7 && pieceFocused.positionX == 0) {
                gameFlags.canCastleLongWhite = false
                makeMove(positionY, positionX)
                GameHelper().checkEndOfGame(TakenEndGameParameters(
                    PieceParameters(
                        pieceFocused,
                        board,
                        movesList,
                        piecesList,
                        context
                    ),
                    gameFlags,
                    rootView,
                    movesWithNoCaptureBlack,
                    movesWithNoCaptureWhite,
                    turn
                ))
                gameFlags.didPlayerMoved = true
            } else if (pieceFocused.type == 3 && pieceFocused.color == 0 && pieceFocused.positionY == 7 && pieceFocused.positionX == 7) {
                gameFlags.canCastleShortWhite = false
                makeMove(positionY, positionX)
                GameHelper().checkEndOfGame(TakenEndGameParameters(
                    PieceParameters(
                        pieceFocused,
                        board,
                        movesList,
                        piecesList,
                        context
                    ),
                    gameFlags,
                    rootView,
                    movesWithNoCaptureBlack,
                    movesWithNoCaptureWhite,
                    turn
                ))
                gameFlags.didPlayerMoved = true
            } else if (pieceFocused.type == 5 && pieceFocused.color == 1) {
                resetBlackKingCastlePossibilities()
                makeMove(positionY, positionX)
                GameHelper().checkEndOfGame(TakenEndGameParameters(
                    PieceParameters(
                        pieceFocused,
                        board,
                        movesList,
                        piecesList,
                        context
                    ),
                    gameFlags,
                    rootView,
                    movesWithNoCaptureBlack,
                    movesWithNoCaptureWhite,
                    turn
                ))
                gameFlags.didPlayerMoved = true
            } else if (pieceFocused.type == 3 && pieceFocused.color == 1 && pieceFocused.positionY == 0 && pieceFocused.positionX == 0) {
                gameFlags.canCastleLongBlack = false
                makeMove(positionY, positionX)
                GameHelper().checkEndOfGame(TakenEndGameParameters(
                    PieceParameters(
                        pieceFocused,
                        board,
                        movesList,
                        piecesList,
                        context
                    ),
                    gameFlags,
                    rootView,
                    movesWithNoCaptureBlack,
                    movesWithNoCaptureWhite,
                    turn
                ))
                gameFlags.didPlayerMoved = true
            } else if (pieceFocused.type == 3 && pieceFocused.color == 1 && pieceFocused.positionY == 0 && pieceFocused.positionX == 7) {
                gameFlags.canCastleShortBlack = false
                makeMove(positionY, positionX)
                GameHelper().checkEndOfGame(TakenEndGameParameters(
                    PieceParameters(
                        pieceFocused,
                        board,
                        movesList,
                        piecesList,
                        context
                    ),
                    gameFlags,
                    rootView,
                    movesWithNoCaptureBlack,
                    movesWithNoCaptureWhite,
                    turn
                ))
                gameFlags.didPlayerMoved = true
            } else {
                makeMove(positionY, positionX)
                GameHelper().checkEndOfGame(TakenEndGameParameters(
                    PieceParameters(
                        pieceFocused,
                        board,
                        movesList,
                        piecesList,
                        context
                    ),
                    gameFlags,
                    rootView,
                    movesWithNoCaptureBlack,
                    movesWithNoCaptureWhite,
                    turn
                ))
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
        resetMovesList()
        resetBoard()
        checkChecks()
        resetPieceFocused()
        resetSpecialPawnMovesFlags()
        changeTurn()
        GameHelper().checkEndOfGame(TakenEndGameParameters(
            PieceParameters(
                pieceFocused,
                board,
                movesList,
                piecesList,
                context
            ),
            gameFlags,
            rootView,
            movesWithNoCaptureBlack,
            movesWithNoCaptureWhite,
            turn
        ))
        gameFlags.didPlayerMoved = true
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
        resetMovesList()
        resetBoard()
        checkChecks()
        resetPieceFocused()
        resetSpecialPawnMovesFlags()
        changeTurn()
        GameHelper().checkEndOfGame(TakenEndGameParameters(
            PieceParameters(
                pieceFocused,
                board,
                movesList,
                piecesList,
                context
            ),
            gameFlags,
            rootView,
            movesWithNoCaptureBlack,
            movesWithNoCaptureWhite,
            turn
        ))
        gameFlags.didPlayerMoved = true
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
        resetMovesList()
        resetBoard()
        checkChecks()
        resetPieceFocused()
        resetSpecialPawnMovesFlags()
        changeTurn()
        GameHelper().checkEndOfGame(TakenEndGameParameters(
            PieceParameters(
                pieceFocused,
                board,
                movesList,
                piecesList,
                context
            ),
            gameFlags,
            rootView,
            movesWithNoCaptureBlack,
            movesWithNoCaptureWhite,
            turn
        ))
        gameFlags.didPlayerMoved = true
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
        resetMovesList()
        resetBoard()
        checkChecks()
        resetPieceFocused()
        resetSpecialPawnMovesFlags()
        changeTurn()
        GameHelper().checkEndOfGame(TakenEndGameParameters(
            PieceParameters(
                pieceFocused,
                board,
                movesList,
                piecesList,
                context
            ),
            gameFlags,
            rootView,
            movesWithNoCaptureBlack,
            movesWithNoCaptureWhite,
            turn
        ))
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
        checkChecks()
        resetPieceFocused()
        changeTurn()
        GameHelper().checkEndOfGame(TakenEndGameParameters(
            PieceParameters(
                pieceFocused,
                board,
                movesList,
                piecesList,
                context
            ),
            gameFlags,
            rootView,
            movesWithNoCaptureBlack,
            movesWithNoCaptureWhite,
            turn
        ))
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
        checkChecks()
        resetPieceFocused()
        changeTurn()
        GameHelper().checkEndOfGame(TakenEndGameParameters(
            PieceParameters(
                pieceFocused,
                board,
                movesList,
                piecesList,
                context
            ),
            gameFlags,
            rootView,
            movesWithNoCaptureBlack,
            movesWithNoCaptureWhite,
            turn
        ))
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
        checkChecks()
        resetPieceFocused()
        gameFlags.pawnSpecialWhite = false
        changeTurn()
        GameHelper().checkEndOfGame(TakenEndGameParameters(
            PieceParameters(
                pieceFocused,
                board,
                movesList,
                piecesList,
                context
            ),
            gameFlags,
            rootView,
            movesWithNoCaptureBlack,
            movesWithNoCaptureWhite,
            turn
        ))
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
        checkChecks()
        resetPieceFocused()
        gameFlags.pawnSpecialBlack = false
        changeTurn()
        GameHelper().checkEndOfGame(TakenEndGameParameters(
            PieceParameters(
                pieceFocused,
                board,
                movesList,
                piecesList,
                context
            ),
            gameFlags,
            rootView,
            movesWithNoCaptureBlack,
            movesWithNoCaptureWhite,
            turn
        ))
        gameFlags.didPlayerMoved = true
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

    private fun makeMove(positionY: Int, positionX: Int) {
        if (isPiece(board[positionY][positionX])) {
            resetMovesWithNoCapture(pieceFocused.color)
            findPiece(positionY, positionX).isActive = false
        } else {
            incrementMovesWithNoCapture(pieceFocused.color)
        }
        changePiecePosition(positionY, positionX, pieceFocused)
        resetMovesList()
        resetBoard()
        checkChecks()
        resetPieceFocused()
        resetSpecialPawnMovesFlags()
        changeTurn()
    }

    private fun checkChecks() {
        if (isWhiteCheck()) {
            if (isWhiteCheckMate()) {
                GameLogic().showEndGameMessage("BLACK WINS", rootView)
            } else {
                gameFlags.checkWhite = true
//                checkWhiteTextView.visibility = View.VISIBLE
            }
        } else {
            gameFlags.checkWhite = false
//            checkWhiteTextView.visibility = View.GONE
        }
        if (isBlackCheck()) {
            if (isBlackCheckMate()) {
                GameLogic().showEndGameMessage("WHITE WINS", rootView)
            } else {
                gameFlags.checkBlack = true
//                checkBlackTextView.visibility = View.VISIBLE
            }
        } else {
            gameFlags.checkBlack = false
//            checkBlackTextView.visibility = View.GONE
        }
    }

    private fun isBlackCheckMate(): Boolean {
        return PiecesHelper().isCheckMate(piecesList, 1, board, context)
    }

    private fun isWhiteCheckMate(): Boolean {
        return PiecesHelper().isCheckMate(piecesList, 0, board, context)
    }

    private fun isBlackCheck(): Boolean {
        return PiecesHelper().isCheck(piecesList, 1, board)
    }

    private fun isBlackCheck(piecesGiven: ArrayList<Piece>): Boolean {
        return PiecesHelper().isCheck(piecesGiven, 1, board)
    }

    private fun isWhiteCheck(): Boolean {
        return PiecesHelper().isCheck(piecesList, 0, board)
    }

    private fun isWhiteCheck(piecesGiven: ArrayList<Piece>): Boolean {
        return PiecesHelper().isCheck(piecesGiven, 0, board)
    }

    private fun showMoves(pieceFocused: Piece) {
        val pieceParams = PieceParameters(
            pieceFocused,
            board,
            movesList,
            piecesList,
            context
        )
        val pawnBeforeMoveParams = PawnBeforeMoveParameters(
            pawnSpecialX,
            pawnSpecialY,
            gameFlags.pawnSpecialBlack,
            gameFlags.pawnSpecialWhite
        )
        when (pieceFocused.type) {
            0 -> {
                if (pieceFocused.color == 0) {
                    val afterMovePawnHelper =
                        PiecesHelper().showPieceMoves(pieceParams, pawnBeforeMoveParams, gameFlags)
                    gameFlags.isChoose = afterMovePawnHelper.isChoose
                    movesList = afterMovePawnHelper.moves
                } else {
                    val afterMovePawnHelper =
                        PiecesHelper().showPieceMoves(pieceParams, pawnBeforeMoveParams, gameFlags)
                    gameFlags.isChoose = afterMovePawnHelper.isChoose
                    movesList = afterMovePawnHelper.moves
                }
            }
            else -> {
                val afterMovePawnHelper =
                    PiecesHelper().showPieceMoves(pieceParams, pawnBeforeMoveParams, gameFlags)
                movesList = afterMovePawnHelper.moves
                gameFlags.longBlackCastleAvailable = afterMovePawnHelper.longBlackCastleAvailable
                gameFlags.shortBlackCastleAvailable = afterMovePawnHelper.shortBlackCastleAvailable
                gameFlags.shortWhiteCastleAvailable = afterMovePawnHelper.shortWhiteCastleAvailable
                gameFlags.longWhiteCastleAvailable = afterMovePawnHelper.longWhiteCastleAvailable
            }
        }
        resetMoves()
    }




    private fun resetBoard(
        piecesList: ArrayList<Piece>,
        boardTaken: Array<Array<ImageView>>,
        context: Context
    ) {
        for (array in boardTaken) {
            for (image in array) {
                image.setImageDrawable(null)
            }
        }
        for (piece in piecesList) {
            if (piece.isActive) {
                BoardHelper().drawPiece(piece, boardTaken, context)
            }
        }
    }

    private fun showQueenMoves(pieceFocused: Piece, i: Int) {
//        showBishopMoves(pieceFocused, i)
//        showRookMoves(pieceFocused, i)
    }

    private fun isOtherKingTooClose(positionY: Int, positionX: Int, color: Int): Boolean {
        val otherKing = findKing(color)
        if (abs(otherKing.positionX - positionX) <= 1) {
            if (abs(otherKing.positionY - positionY) <= 1) {
                return true
            }
        }
        return false
    }

    private fun canPieceMove(positionY: Int, positionX: Int, color: Int): Boolean {
        val colorOpposite = if (color == 0) {
            1
        } else {
            0
        }
        if (!isPiece(board[positionY][positionX])) {
            val piece = pieceFocused.copy()
            piece.positionY = positionY
            piece.positionX = positionX
            val piecesListCopy = piecesList.toMutableList() as ArrayList<Piece>
            piecesListCopy.set(
                piecesListCopy.indexOf(pieceFocused),
                Piece(
                    pieceFocused.type,
                    pieceFocused.color,
                    piece.positionX,
                    piece.positionY,
                    pieceFocused.isActive
                )
            )
            val boardCopy = board.clone()
            resetBoard(piecesListCopy, boardCopy, context)
            if (!PiecesHelper().isCheck(piecesListCopy, colorOpposite, boardCopy)) {
                movesList[positionY][positionX] = true
            }
            resetBoard(piecesList, board, context)
            return true
        } else {
            if (findPiece(
                    (positionY),
                    (positionX)
                ).color == color
            ) {
                val piece = pieceFocused.copy()
                piece.positionY = positionY
                piece.positionX = positionX
                val piecesListCopy = piecesList.toMutableList() as ArrayList<Piece>
                piecesListCopy.set(
                    piecesListCopy.indexOf(pieceFocused),
                    Piece(
                        pieceFocused.type,
                        pieceFocused.color,
                        piece.positionX,
                        piece.positionY,
                        pieceFocused.isActive
                    )
                )
                piecesListCopy.set(
                    piecesListCopy.indexOf(
                        findPiece(
                            positionY,
                            positionX
                        )
                    ),
                    Piece(
                        0,
                        0,
                        0,
                        0,
                        false
                    )
                )
                val boardCopy = board.clone()
                resetBoard(piecesListCopy, boardCopy, context)
                if (!PiecesHelper().isCheck(piecesListCopy, colorOpposite, boardCopy)) {
                    movesList[positionY][positionX] = true
                }
                resetBoard(piecesList, board, context)
                return false
            } else {
                return false
            }
        }
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
        checkChecks()
        resetPieceFocused()
        gameFlags.isChoose = false
        resetSpecialPawnMovesFlags()
        changeTurn()
        gameFlags.didPlayerMoved = true
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

    private fun findKing(color: Int): Piece {
        for (piece in piecesList) {
            if (piece.isActive) {
                if (piece.type == 5 && piece.color == color) {
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
}