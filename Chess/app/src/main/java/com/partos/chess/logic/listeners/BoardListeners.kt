package com.partos.chess.logic.listeners

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.partos.chess.R
import com.partos.chess.logic.BoardHelper
import com.partos.chess.logic.PiecesHelper
import com.partos.chess.models.Piece
import kotlin.math.abs

class BoardListeners {

    private lateinit var backButton: ImageView
    private lateinit var chooseLayout: LinearLayout
    private lateinit var chooseBishop: ImageView
    private lateinit var chooseKnight: ImageView
    private lateinit var chooseRook: ImageView
    private lateinit var chooseQueen: ImageView
    private lateinit var checkBlackTextView: TextView
    private lateinit var checkWhiteTextView: TextView
    private lateinit var winTextView: TextView
    private lateinit var movesList: Array<Array<Boolean>>
    private lateinit var board: Array<Array<ImageView>>
    private lateinit var moves: Array<Array<ImageView>>
    private lateinit var piecesList: ArrayList<Piece>
    private lateinit var context: Context
    private lateinit var pieceFocused: Piece
    private var isChoose = false
    private var moveX = 0
    private var moveY = 0
    private var turn = 0
    private var pawnSpecialX = 0
    private var pawnSpecialY = 0
    private var pawnSpecialWhite = false
    private var pawnSpecialBlack = false
    private var checkBlack = false
    private var checkWhite = false
    private var canCastleLongBlack = true
    private var canCastleLongWhite = true
    private var canCastleShortBlack = true
    private var canCastleShortWhite = true
    private var longWhiteCastleAvailable = false
    private var longBlackCastleAvailable = false
    private var shortWhiteCastleAvailable = false
    private var shortBlackCastleAvailable = false
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
        attachParameters(board, moves, piecesList, rootView.context)
        attachViews(rootView)
        attachBaseListeners(fragmentManager)
        createMovesList()
        attachPiecesListeners()
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
        var array1 = arrayOf(false, false, false, false, false, false, false, false)
        var array2 = arrayOf(false, false, false, false, false, false, false, false)
        var array3 = arrayOf(false, false, false, false, false, false, false, false)
        var array4 = arrayOf(false, false, false, false, false, false, false, false)
        var array5 = arrayOf(false, false, false, false, false, false, false, false)
        var array6 = arrayOf(false, false, false, false, false, false, false, false)
        var array7 = arrayOf(false, false, false, false, false, false, false, false)
        var array8 = arrayOf(false, false, false, false, false, false, false, false)
        movesList = arrayOf(array1, array2, array3, array4, array5, array6, array7, array8)
    }

    private fun attachPiecesListeners() {
        for (i in 0..7) {
            for (j in 0..7) {
                board[i][j].setOnClickListener {
                    if (isMove(i, j)) {
                        if (isChoose) {
                            moveX = j
                            moveY = i
                            chooseLayout.visibility = View.VISIBLE
                            if (pieceFocused.color == 0) {
                                chooseBishop.setImageDrawable(context.getDrawable(R.drawable.bishop_white))
                                chooseKnight.setImageDrawable(context.getDrawable(R.drawable.knight_white))
                                chooseRook.setImageDrawable(context.getDrawable(R.drawable.rook_white))
                                chooseQueen.setImageDrawable(context.getDrawable(R.drawable.queen_white))
                            } else {
                                chooseBishop.setImageDrawable(context.getDrawable(R.drawable.bishop_black))
                                chooseKnight.setImageDrawable(context.getDrawable(R.drawable.knight_black))
                                chooseRook.setImageDrawable(context.getDrawable(R.drawable.rook_black))
                                chooseQueen.setImageDrawable(context.getDrawable(R.drawable.queen_black))
                            }
                        } else if (pieceFocused.color == 0 && pawnSpecialBlack && i == pawnSpecialY - 1 && j == pawnSpecialX && pieceFocused.type == 0) {
                            findPiece(pawnSpecialY, pawnSpecialX).isActive = false
                            resetMovesWithNoCapture(pieceFocused.color)
                            piecesList.set(
                                piecesList.indexOf(pieceFocused), Piece(
                                    pieceFocused.type,
                                    pieceFocused.color,
                                    pawnSpecialX,
                                    pawnSpecialY - 1,
                                    true
                                )
                            )
                            resetMovesList()
                            resetBoard()
                            checkChecks()
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            pawnSpecialBlack = false
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                            checkIfEndOfGame()
                        } else if (pieceFocused.color == 1 && pawnSpecialWhite && i == pawnSpecialY + 1 && j == pawnSpecialX && pieceFocused.type == 0) {
                            findPiece(pawnSpecialY, pawnSpecialX).isActive = false
                            resetMovesWithNoCapture(pieceFocused.color)
                            piecesList.set(
                                piecesList.indexOf(pieceFocused), Piece(
                                    pieceFocused.type,
                                    pieceFocused.color,
                                    pawnSpecialX,
                                    pawnSpecialY + 1,
                                    true
                                )
                            )
                            resetMovesList()
                            resetBoard()
                            checkChecks()
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            pawnSpecialWhite = false
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                            checkIfEndOfGame()
                        } else if (i == pieceFocused.positionY + 2 && pieceFocused.color == 1 && pieceFocused.type == 0) {
                            incrementMovesWithNoCapture(pieceFocused.color)
                            pawnSpecialX = pieceFocused.positionX
                            pawnSpecialY = pieceFocused.positionY + 2
                            pawnSpecialBlack = true
                            piecesList.set(
                                piecesList.indexOf(pieceFocused), Piece(
                                    pieceFocused.type,
                                    pieceFocused.color,
                                    j,
                                    i,
                                    true
                                )
                            )
                            resetMovesList()
                            resetBoard()
                            checkChecks()
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                            checkIfEndOfGame()
                        } else if (i == pieceFocused.positionY - 2 && pieceFocused.color == 0 && pieceFocused.type == 0) {
                            incrementMovesWithNoCapture(pieceFocused.color)
                            pawnSpecialX = pieceFocused.positionX
                            pawnSpecialY = pieceFocused.positionY - 2
                            pawnSpecialWhite = true
                            piecesList.set(
                                piecesList.indexOf(pieceFocused), Piece(
                                    pieceFocused.type,
                                    pieceFocused.color,
                                    j,
                                    i,
                                    true
                                )
                            )
                            resetMovesList()
                            resetBoard()
                            checkChecks()
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                            checkIfEndOfGame()
                        } else if (pieceFocused.type == 5 && pieceFocused.color == 0 &&
                            pieceFocused.positionY == 7 && pieceFocused.positionX == 4 &&
                            i == 7 && j == 2
                        ) {
                            incrementMovesWithNoCapture(pieceFocused.color)
                            canCastleLongWhite = false
                            canCastleShortWhite = false
                            piecesList.set(
                                piecesList.indexOf(pieceFocused), Piece(
                                    pieceFocused.type,
                                    pieceFocused.color,
                                    j,
                                    i,
                                    true
                                )
                            )
                            val rook = findPiece(7, 0)
                            piecesList.set(
                                piecesList.indexOf(rook), Piece(
                                    rook.type,
                                    rook.color,
                                    3,
                                    7,
                                    true
                                )
                            )
                            resetMovesList()
                            resetBoard()
                            checkChecks()
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            pawnSpecialWhite = false
                            pawnSpecialBlack = false
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                            checkIfEndOfGame()
                        } else if (pieceFocused.type == 5 && pieceFocused.color == 0 &&
                            pieceFocused.positionY == 7 && pieceFocused.positionX == 4 &&
                            i == 7 && j == 6
                        ) {
                            incrementMovesWithNoCapture(pieceFocused.color)
                            canCastleLongWhite = false
                            canCastleShortWhite = false
                            piecesList.set(
                                piecesList.indexOf(pieceFocused), Piece(
                                    pieceFocused.type,
                                    pieceFocused.color,
                                    j,
                                    i,
                                    true
                                )
                            )
                            val rook = findPiece(7, 7)
                            piecesList.set(
                                piecesList.indexOf(rook), Piece(
                                    rook.type,
                                    rook.color,
                                    5,
                                    7,
                                    true
                                )
                            )
                            resetMovesList()
                            resetBoard()
                            checkChecks()
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            pawnSpecialWhite = false
                            pawnSpecialBlack = false
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                            checkIfEndOfGame()
                        } else if (pieceFocused.type == 5 && pieceFocused.color == 1 &&
                            pieceFocused.positionY == 0 && pieceFocused.positionX == 4 &&
                            i == 0 && j == 2
                        ) {
                            incrementMovesWithNoCapture(pieceFocused.color)
                            canCastleLongBlack = false
                            canCastleShortBlack = false
                            piecesList.set(
                                piecesList.indexOf(pieceFocused), Piece(
                                    pieceFocused.type,
                                    pieceFocused.color,
                                    j,
                                    i,
                                    true
                                )
                            )
                            val rook = findPiece(0, 0)
                            piecesList.set(
                                piecesList.indexOf(rook), Piece(
                                    rook.type,
                                    rook.color,
                                    3,
                                    0,
                                    true
                                )
                            )
                            resetMovesList()
                            resetBoard()
                            checkChecks()
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            pawnSpecialWhite = false
                            pawnSpecialBlack = false
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                            checkIfEndOfGame()
                        } else if (pieceFocused.type == 5 && pieceFocused.color == 1 &&
                            pieceFocused.positionY == 0 && pieceFocused.positionX == 4 &&
                            i == 0 && j == 6
                        ) {
                            incrementMovesWithNoCapture(pieceFocused.color)
                            canCastleLongBlack = false
                            canCastleShortBlack = false
                            piecesList.set(
                                piecesList.indexOf(pieceFocused), Piece(
                                    pieceFocused.type,
                                    pieceFocused.color,
                                    j,
                                    i,
                                    true
                                )
                            )
                            val rook = findPiece(0, 7)
                            piecesList.set(
                                piecesList.indexOf(rook), Piece(
                                    rook.type,
                                    rook.color,
                                    5,
                                    0,
                                    true
                                )
                            )
                            resetMovesList()
                            resetBoard()
                            checkChecks()
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            pawnSpecialWhite = false
                            pawnSpecialBlack = false
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                            checkIfEndOfGame()
                        } else if (pieceFocused.type == 5 && pieceFocused.color == 0) {
                            canCastleLongWhite = false
                            canCastleShortWhite = false
                            makeMove(i, j)
                            checkIfEndOfGame()
                        } else if (pieceFocused.type == 3 && pieceFocused.color == 0 && pieceFocused.positionY == 7 && pieceFocused.positionX == 0) {
                            canCastleLongWhite = false
                            makeMove(i, j)
                            checkIfEndOfGame()
                        } else if (pieceFocused.type == 3 && pieceFocused.color == 0 && pieceFocused.positionY == 7 && pieceFocused.positionX == 7) {
                            canCastleShortWhite = false
                            makeMove(i, j)
                            checkIfEndOfGame()
                        } else if (pieceFocused.type == 5 && pieceFocused.color == 1) {
                            canCastleLongBlack = false
                            canCastleShortBlack = false
                            makeMove(i, j)
                            checkIfEndOfGame()
                        } else if (pieceFocused.type == 3 && pieceFocused.color == 1 && pieceFocused.positionY == 0 && pieceFocused.positionX == 0) {
                            canCastleLongBlack = false
                            makeMove(i, j)
                            checkIfEndOfGame()
                        } else if (pieceFocused.type == 3 && pieceFocused.color == 1 && pieceFocused.positionY == 0 && pieceFocused.positionX == 7) {
                            canCastleShortBlack = false
                            makeMove(i, j)
                            checkIfEndOfGame()
                        } else {
                            makeMove(i, j)
                            checkIfEndOfGame()
                        }
                    } else if (isPiece(board[i][j])) {
                        isChoose = false
                        resetMovesList()
                        resetBoard()
                        if (findPiece(i, j).color == turn) {
                            pieceFocused = findPiece(i, j)
                            showMoves(pieceFocused)
                        }
                    } else {
                        isChoose = false
                        resetMovesList()
                        resetBoard()
                    }
                }
            }
        }
    }

    private fun checkIfEndOfGame() {
        if (isStaleMate())  {
            showStaleMate()
        } else if (isFiftyMovesWithNoCapture()) {
            showFiftyMovesWithNoCapture()
        } else if (isDeadPosition()) {
            showDeadPosition()
        }
    }

    private fun showStaleMate() {
        checkWhiteTextView.visibility = View.GONE
        checkBlackTextView.visibility = View.GONE
        winTextView.text = "STALEMATE!\nIT IS A DRAW"
        winTextView.visibility = View.VISIBLE
    }

    private fun showFiftyMovesWithNoCapture() {
        checkWhiteTextView.visibility = View.GONE
        checkBlackTextView.visibility = View.GONE
        winTextView.text = "50 MOVES WITH\nNO CAPTURE!\nIT IS A DRAW"
        winTextView.visibility = View.VISIBLE    }

    private fun isFiftyMovesWithNoCapture(): Boolean {
        if (movesWithNoCaptureBlack >= 50 || movesWithNoCaptureWhite >= 50) {
            return true
        }
        return false
    }

    private fun isDeadPosition(): Boolean {
        if (isKingVsKing()) {
            return true
        } else if (isKingVsKingAndBishop()) {
            return true
        } else if (isKingVsKingAndKnight()) {
            return true
        } else if (areKingsPlusTheSameColorBishops()) {
            return true
        }
        return false
    }

    private fun areKingsPlusTheSameColorBishops(): Boolean {
        var pieces = 0
        for (piece in piecesList) {
            if (piece.isActive) {
                pieces++
            }
        }
        if (pieces == 4) {
            if (isAnyBishopActive(0) && isAnyBishopActive(1)) {
                if (colorOfBishop(0) == colorOfBishop(1)) {
                    return true
                }
                return false
            }
            return false
        }
        return false
    }

    private fun colorOfBishop(color: Int): Int {
        for (piece in piecesList) {
            if (piece.isActive && piece.color == color && piece.type == 1) {
                return getSquareColor(piece.positionY, piece.positionX)
            }
        }
        return 3
    }

    private fun getSquareColor(positionY: Int, positionX: Int): Int {
        if (positionY % 2 == 0) {
            return if (positionX % 2 == 0) {
                0
            } else {
                1
            }
        } else {
            return if (positionX % 2 == 0) {
                1
            } else {
                0
            }
        }
    }

    private fun isKingVsKingAndKnight(): Boolean {
        var pieces = 0
        for (piece in piecesList) {
            if (piece.isActive) {
                pieces++
            }
        }
        if (pieces == 3) {
            if (isAnyKnightActive(0) || isAnyKnightActive(1))
                return true
        }
        return false
    }

    private fun isAnyKnightActive(color: Int): Boolean {
        for (piece in piecesList) {
            if (piece.isActive && piece.color == color && piece.type == 2) {
                return true
            }
        }
        return false
    }

    private fun isKingVsKingAndBishop(): Boolean {
        var pieces = 0
        for (piece in piecesList) {
            if (piece.isActive) {
                pieces++
            }
        }
        if (pieces == 3) {
            if (isAnyBishopActive(0) || isAnyBishopActive(1))
                return true
        }
        return false
    }

    private fun isAnyBishopActive(color: Int): Boolean {
        for (piece in piecesList) {
            if (piece.isActive && piece.color == color && piece.type == 1) {
                return true
            }
        }
        return false
    }

    private fun showDeadPosition() {
        checkWhiteTextView.visibility = View.GONE
        checkBlackTextView.visibility = View.GONE
        winTextView.text = "DEAD POSITION!\nIT IS A DRAW"
        winTextView.visibility = View.VISIBLE
    }

    private fun isKingVsKing(): Boolean {
        var pieces = 0
        for (piece in piecesList) {
            if (piece.isActive) {
                pieces++
            }
        }
        return pieces == 2
    }

    private fun isStaleMate(): Boolean {
        if (turn == 0) {
            return isWhiteStaleMate()
        } else {
            return isBlackStaleMate()
        }
    }

    private fun isBlackStaleMate(): Boolean{
        if (!PiecesHelper().isAnyMovePossible(piecesList, 1, board, context)) {
            if (!isBlackCheck()) {
                if (!hasKingMoves(1)) {
                    return true
                }
            }
        }
        return false
    }

    private fun isWhiteStaleMate(): Boolean{
        if (!PiecesHelper().isAnyMovePossible(piecesList, 0, board, context)) {
            if (!isWhiteCheck()) {
                if (!hasKingMoves(0)) {
                    return true
                }
            }
        }
        return false
    }

    private fun hasKingMoves(color: Int): Boolean {
        if (color == 0) {
            val king = findKing(0)
            return checkIfKingHasMoves(king, 1)
        } else {
            val king = findKing(1)
            return checkIfKingHasMoves(king, 0)
        }
    }

    private fun checkIfKingHasMoves(kingFocused: Piece, color: Int): Boolean {
        if (kingFocused.positionY >= 1) {
            if (canKingMove(
                    color,
                    kingFocused.positionY - 1,
                    kingFocused.positionX,
                    kingFocused
                )
            ) {
                return true
            }
        }
        if (kingFocused.positionY >= 1 && kingFocused.positionX <= 6) {
            if (canKingMove(
                    color,
                    kingFocused.positionY - 1,
                    kingFocused.positionX + 1,
                    kingFocused
                )
            ) {
                return true
            }
        }
        if (kingFocused.positionX <= 6) {
            if (canKingMove(
                    color,
                    kingFocused.positionY,
                    kingFocused.positionX + 1,
                    kingFocused
                )
            ) {
                return true
            }
        }
        if (kingFocused.positionY <= 6 && kingFocused.positionX <= 6) {
            if (canKingMove(
                    color,
                    kingFocused.positionY + 1,
                    kingFocused.positionX + 1,
                    kingFocused
                )
            ) {
                return true
            }
        }
        if (kingFocused.positionY <= 6) {
            if (canKingMove(
                    color,
                    kingFocused.positionY + 1,
                    kingFocused.positionX,
                    kingFocused
                )
            ) {
                return true
            }
        }
        if (kingFocused.positionY <= 6 && kingFocused.positionX >= 1) {
            if (canKingMove(
                    color,
                    kingFocused.positionY + 1,
                    kingFocused.positionX - 1,
                    kingFocused
                )
            ) {
                return true
            }
        }
        if (kingFocused.positionX >= 1) {
            if (canKingMove(
                    color,
                    kingFocused.positionY,
                    kingFocused.positionX - 1,
                    kingFocused
                )
            ) {
                return true
            }
        }
        if (kingFocused.positionY >= 1 && kingFocused.positionX >= 1) {
            if (canKingMove(
                    color,
                    kingFocused.positionY - 1,
                    kingFocused.positionX - 1,
                    kingFocused
                )
            ) {
                return true
            }
        }
        if (kingFocused.color == 0) {
            if (canCastleLongWhite &&
                !isPiece(board[7][3]) &&
                !isPiece(board[7][2]) &&
                !isPiece(board[7][1]) &&
                !isWhiteCheck()
            ) {
                return true
            }
            if (canCastleShortWhite &&
                !isPiece(board[7][5]) &&
                !isPiece(board[7][6]) &&
                !isWhiteCheck()
            ) {
                return true
            }
        } else {
            if (canCastleLongBlack &&
                !isPiece(board[0][3]) &&
                !isPiece(board[0][2]) &&
                !isPiece(board[0][1]) &&
                !isBlackCheck()
            ) {
                return true
            }
            if (canCastleShortBlack &&
                !isPiece(board[0][5]) &&
                !isPiece(board[0][6]) &&
                !isBlackCheck()
            ) {
                return true
            }
        }
        return false
    }

    private fun makeMove(i: Int, j: Int) {
        if (isPiece(board[i][j])) {
            resetMovesWithNoCapture(pieceFocused.color)
            findPiece(i, j).isActive = false
        } else {
            incrementMovesWithNoCapture(pieceFocused.color)
        }
        piecesList.set(
            piecesList.indexOf(pieceFocused), Piece(
                pieceFocused.type,
                pieceFocused.color,
                j,
                i,
                true
            )
        )
        resetMovesList()
        resetBoard()
        checkChecks()
        pieceFocused = Piece(0, 0, 0, 0, false)
        pawnSpecialWhite = false
        pawnSpecialBlack = false
        if (turn == 0) {
            turn = 1
        } else {
            turn = 0
        }
    }

    private fun checkChecks() {
        if (isWhiteCheck()) {
            if (isWhiteCheckMate()) {
                checkWhiteTextView.visibility = View.GONE
                checkBlackTextView.visibility = View.GONE
                winTextView.text = "BLACK WINS"
                winTextView.visibility = View.VISIBLE
            } else {
                checkWhite = true
                checkWhiteTextView.visibility = View.VISIBLE
            }
        } else {
            checkWhite = false
            checkWhiteTextView.visibility = View.GONE
        }
        if (isBlackCheck()) {
            if (isBlackCheckMate()) {
                checkWhiteTextView.visibility = View.GONE
                checkBlackTextView.visibility = View.GONE
                winTextView.text = "WHITE WINS"
                winTextView.visibility = View.VISIBLE
            } else {
                checkBlack = true
                checkBlackTextView.visibility = View.VISIBLE
            }
        } else {
            checkBlack = false
            checkBlackTextView.visibility = View.GONE
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

    private fun isWhiteCheck(): Boolean {
        return PiecesHelper().isCheck(piecesList, 0, board)
    }

    private fun showMoves(pieceFocused: Piece) {
        when (pieceFocused.type) {
            0 -> {
                if (pieceFocused.color == 0) {
                    showWhitePawnMoves(pieceFocused)
                } else {
                    showBlackPawnMoves(pieceFocused)
                }
            }
            1 -> {
                if (pieceFocused.color == 0) {
                    showBishopMoves(pieceFocused, 1)
                } else {
                    showBishopMoves(pieceFocused, 0)
                }
            }
            2 -> {
                if (pieceFocused.color == 0) {
                    showKnightMoves(pieceFocused, 1)
                } else {
                    showKnightMoves(pieceFocused, 0)
                }
            }
            3 -> {
                if (pieceFocused.color == 0) {
                    showRookMoves(pieceFocused, 1)
                } else {
                    showRookMoves(pieceFocused, 0)
                }
            }
            4 -> {
                if (pieceFocused.color == 0) {
                    showQueenMoves(pieceFocused, 1)
                } else {
                    showQueenMoves(pieceFocused, 0)
                }
            }
            5 -> {
                if (pieceFocused.color == 0) {
                    showKingMoves(pieceFocused, 1)
                } else {
                    showKingMoves(pieceFocused, 0)
                }
            }
        }
        resetMoves()
    }

    private fun showKingMoves(pieceFocused: Piece, color: Int) {
        if (pieceFocused.positionY >= 1) {
            if (canKingMove(
                    color,
                    pieceFocused.positionY - 1,
                    pieceFocused.positionX,
                    pieceFocused
                )
            ) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX] = true
            }
        }
        if (pieceFocused.positionY >= 1 && pieceFocused.positionX <= 6) {
            if (canKingMove(
                    color,
                    pieceFocused.positionY - 1,
                    pieceFocused.positionX + 1,
                    pieceFocused
                )
            ) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 1] = true
            }
        }
        if (pieceFocused.positionX <= 6) {
            if (canKingMove(
                    color,
                    pieceFocused.positionY,
                    pieceFocused.positionX + 1,
                    pieceFocused
                )
            ) {
                movesList[pieceFocused.positionY][pieceFocused.positionX + 1] = true
            }
        }
        if (pieceFocused.positionY <= 6 && pieceFocused.positionX <= 6) {
            if (canKingMove(
                    color,
                    pieceFocused.positionY + 1,
                    pieceFocused.positionX + 1,
                    pieceFocused
                )
            ) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX + 1] = true
            }
        }
        if (pieceFocused.positionY <= 6) {
            if (canKingMove(
                    color,
                    pieceFocused.positionY + 1,
                    pieceFocused.positionX,
                    pieceFocused
                )
            ) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX] = true
            }
        }
        if (pieceFocused.positionY <= 6 && pieceFocused.positionX >= 1) {
            if (canKingMove(
                    color,
                    pieceFocused.positionY + 1,
                    pieceFocused.positionX - 1,
                    pieceFocused
                )
            ) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX - 1] = true
            }
        }
        if (pieceFocused.positionX >= 1) {
            if (canKingMove(
                    color,
                    pieceFocused.positionY,
                    pieceFocused.positionX - 1,
                    pieceFocused
                )
            ) {
                movesList[pieceFocused.positionY][pieceFocused.positionX - 1] = true
            }
        }
        if (pieceFocused.positionY >= 1 && pieceFocused.positionX >= 1) {
            if (canKingMove(
                    color,
                    pieceFocused.positionY - 1,
                    pieceFocused.positionX - 1,
                    pieceFocused
                )
            ) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 1] = true
            }
        }
        if (pieceFocused.color == 0) {
            val king = findKing(0)
            if (canCastleLongWhite &&
                !isPiece(board[7][3]) &&
                !isPiece(board[7][2]) &&
                !isPiece(board[7][1]) &&
                !isWhiteCheck()
            ) {
                movesList[7][2] = true
                longWhiteCastleAvailable = true
            }
            if (canCastleShortWhite &&
                !isPiece(board[7][5]) &&
                !isPiece(board[7][6]) &&
                !isWhiteCheck()
            ) {
                movesList[7][6] = true
                shortWhiteCastleAvailable = true
            }
        } else {
            val king = findKing(1)
            if (canCastleLongBlack &&
                !isPiece(board[0][3]) &&
                !isPiece(board[0][2]) &&
                !isPiece(board[0][1]) &&
                !isBlackCheck()
            ) {
                movesList[0][2] = true
                longBlackCastleAvailable = true
            }
            if (canCastleShortBlack &&
                !isPiece(board[0][5]) &&
                !isPiece(board[0][6]) &&
                !isBlackCheck()
            ) {
                movesList[0][6] = true
                shortBlackCastleAvailable = true
            }
        }
    }

    private fun canKingMove(
        color: Int,
        positionY: Int,
        positionX: Int,
        pieceFocused: Piece
    ): Boolean {
        if (!isOtherKingTooClose(positionY, positionX, color)) {
            val king: Piece = pieceFocused.copy()
            king.positionY = positionY
            king.positionX = positionX
            if (isPiece(board[positionY][positionX])) {
                val piece = findPiece(positionY, positionX)
                if (piece.color == color) {
                    val piecesListCopy = piecesList.toMutableList() as ArrayList<Piece>
                    piecesListCopy.set(
                        piecesListCopy.indexOf(pieceFocused),
                        Piece(
                            pieceFocused.type,
                            pieceFocused.color,
                            king.positionX,
                            king.positionY,
                            pieceFocused.isActive
                        )
                    )
                    piecesListCopy.set(
                        piecesListCopy.indexOf(findPiece(positionY, positionX)),
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
                    if (PiecesHelper().isCheck(piecesListCopy, pieceFocused.color, boardCopy)) {
                        resetBoard(piecesList, board, context)
                        return false
                    } else {
                        resetBoard(piecesList, board, context)
                        return true
                    }
                } else {
                    return false
                }
            } else {
                val piecesListCopy = piecesList.toMutableList() as ArrayList<Piece>
                piecesListCopy.set(
                    piecesListCopy.indexOf(pieceFocused),
                    Piece(
                        pieceFocused.type,
                        pieceFocused.color,
                        king.positionX,
                        king.positionY,
                        pieceFocused.isActive
                    )
                )
                val boardCopy = board.clone()
                resetBoard(piecesListCopy, boardCopy, context)
                if (PiecesHelper().isCheck(piecesListCopy, pieceFocused.color, boardCopy)) {
                    resetBoard(piecesList, board, context)
                    return false
                } else {
                    resetBoard(piecesList, board, context)
                    return true
                }
            }
        } else {
            return false
        }
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
        showBishopMoves(pieceFocused, i)
        showRookMoves(pieceFocused, i)
    }

    private fun showKnightMoves(pieceFocused: Piece, color: Int) {
        if (pieceFocused.positionX + 1 <= 7 && pieceFocused.positionY - 2 >= 0) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX + 1, 0)
            } else {
                canPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX + 1, 1)
            }
        }
        if (pieceFocused.positionX + 2 <= 7 && pieceFocused.positionY - 1 >= 0) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX + 2, 0)
            } else {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX + 2, 1)
            }
        }
        if (pieceFocused.positionX + 2 <= 7 && pieceFocused.positionY + 1 <= 7) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX + 2, 0)
            } else {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX + 2, 1)
            }
        }
        if (pieceFocused.positionX + 1 <= 7 && pieceFocused.positionY + 2 <= 7) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX + 1, 0)
            } else {
                canPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX + 1, 1)
            }
        }
        if (pieceFocused.positionX - 1 >= 0 && pieceFocused.positionY + 2 <= 7) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX - 1, 0)
            } else {
                canPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX - 1, 1)
            }
        }
        if (pieceFocused.positionX - 2 >= 0 && pieceFocused.positionY + 1 <= 7) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX - 2, 0)
            } else {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX - 2, 1)
            }
        }
        if (pieceFocused.positionX - 2 >= 0 && pieceFocused.positionY - 1 >= 0) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX - 2, 0)
            } else {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX - 2, 1)
            }
        }
        if (pieceFocused.positionX - 1 >= 0 && pieceFocused.positionY - 2 >= 0) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX - 1, 0)
            } else {
                canPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX - 1, 1)
            }
        }
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

    private fun showRookMoves(pieceFocused: Piece, color: Int) {
        for (i in 1..8) {
            if ((pieceFocused.positionX + i) <= 7) {
                if (color == 0) {
                    if (!canPieceMove(pieceFocused.positionY, pieceFocused.positionX + i, 0)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY, pieceFocused.positionX + i, 1)) {
                        break
                    }
                }
            } else {
                break
            }
        }

        for (i in 1..8) {
            if ((pieceFocused.positionX - i) >= 0) {
                if (color == 0) {
                    if (!canPieceMove(pieceFocused.positionY, pieceFocused.positionX - i, 0)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY, pieceFocused.positionX - i, 1)) {
                        break
                    }
                }
            } else {
                break
            }
        }

        for (i in 1..8) {
            if ((pieceFocused.positionY - i) >= 0) {
                if (color == 0) {
                    if (!canPieceMove(pieceFocused.positionY - i, pieceFocused.positionX, 0)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY - i, pieceFocused.positionX, 1)) {
                        break
                    }
                }
            } else {
                break
            }
        }

        for (i in 1..8) {
            if ((pieceFocused.positionY + i) <= 7) {
                if (color == 0) {
                    if (!canPieceMove(pieceFocused.positionY + i, pieceFocused.positionX, 0)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY + i, pieceFocused.positionX, 1)) {
                        break
                    }
                }
            } else {
                break
            }
        }
    }

    private fun showBishopMoves(pieceFocused: Piece, color: Int) {
        // Show top-left moves
        for (i in 1..8) {
            if (pieceFocused.positionX - i >= 0 && pieceFocused.positionY - i >= 0) {
                if (color == 0) {
                    if (!canPieceMove(pieceFocused.positionY - i, pieceFocused.positionX - i, 0)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY - i, pieceFocused.positionX - i, 1)) {
                        break
                    }
                }
            } else {
                break
            }
        }

        // Show top-right moves
        for (i in 1..8) {
            if (pieceFocused.positionX + i <= 7 && pieceFocused.positionY - i >= 0) {
                if (color == 0) {
                    if (!canPieceMove(pieceFocused.positionY - i, pieceFocused.positionX + i, 0)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY - i, pieceFocused.positionX + i, 1)) {
                        break
                    }
                }
            } else {
                break
            }
        }

        // Show bottom-right moves
        for (i in 1..8) {
            if (pieceFocused.positionX + i <= 7 && pieceFocused.positionY + i <= 7) {
                if (color == 0) {
                    if (!canPieceMove(pieceFocused.positionY + i, pieceFocused.positionX + i, 0)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY + i, pieceFocused.positionX + i, 1)) {
                        break
                    }
                }
            } else {
                break
            }
        }

        // Show bottom-left moves
        for (i in 1..8) {
            if (pieceFocused.positionX - i >= 0 && pieceFocused.positionY + i <= 7) {
                if (color == 0) {
                    if (!canPieceMove(pieceFocused.positionY + i, pieceFocused.positionX - i, 0)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY + i, pieceFocused.positionX - i, 1)) {
                        break
                    }
                }
            } else {
                break
            }
        }
    }

    private fun showBlackPawnMoves(pieceFocused: Piece) {
        if (pieceFocused.positionY == 1) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                    canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX - 1, 0)
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX + 1, 0)
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX, 0)
            }
            if (!isPiece(board[pieceFocused.positionY + 2][pieceFocused.positionX]) && !isPiece(
                    board[pieceFocused.positionY + 1][pieceFocused.positionX]
                )
            ) {
                canPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX, 0)
            }
        } else if (pieceFocused.positionY == 4) {
            if (pawnSpecialWhite) {
                if (pawnSpecialY == pieceFocused.positionY && (pawnSpecialX == (pieceFocused.positionX + 1) || pawnSpecialX == (pieceFocused.positionX - 1))) {
                    canPieceMove(pawnSpecialY + 1, pawnSpecialX, 0)
                }
            }
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                    canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX - 1, 0)
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX + 1, 0)
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX, 0)
            }
        } else if (pieceFocused.positionY < 6) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                    canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX - 1, 0)
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX + 1, 0)
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX, 0)
            }
        } else if (pieceFocused.positionY == 6) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                    canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX - 1, 0)
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX + 1, 0)
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX, 0)
            }
            isChoose = true
        }
    }

    private fun showWhitePawnMoves(pieceFocused: Piece) {
        if (pieceFocused.positionY == 6) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX - 1, 1)
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX + 1, 1)
                }
            }
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX, 1)
            }
            if (!isPiece(board[pieceFocused.positionY - 2][pieceFocused.positionX]) && !isPiece(
                    board[pieceFocused.positionY - 1][pieceFocused.positionX]
                )
            ) {
                canPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX, 1)
            }
        } else if (pieceFocused.positionY == 3) {
            if (pawnSpecialBlack) {
                if (pawnSpecialY == pieceFocused.positionY && (pawnSpecialX == (pieceFocused.positionX + 1) || pawnSpecialX == (pieceFocused.positionX - 1))) {
                    canPieceMove(pawnSpecialY - 1, pawnSpecialX, 1)
                }
            }
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX - 1, 1)
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX + 1, 1)
                }
            }
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX, 1)
            }
        } else if (pieceFocused.positionY > 1) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX - 1, 1)
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX + 1, 1)
                }
            }
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX, 1)
            }
        } else if (pieceFocused.positionY == 1) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX - 1, 1)
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX + 1, 1)
                }
            }
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX, 1)
            }
            isChoose = true
        }
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
            pieceFocused.type = 1
            if (isPiece(board[moveY][moveX])) {
                resetMovesWithNoCapture(pieceFocused.color)
                findPiece(moveY, moveX).isActive = false
            } else {
                incrementMovesWithNoCapture(pieceFocused.color)
            }
            piecesList.set(
                piecesList.indexOf(pieceFocused), Piece(
                    pieceFocused.type,
                    pieceFocused.color,
                    moveX,
                    moveY,
                    true
                )
            )
            resetBoard()
            checkChecks()
            pieceFocused = Piece(0, 0, 0, 0, false)
            isChoose = false
            pawnSpecialWhite = false
            pawnSpecialBlack = false
            if (turn == 0) {
                turn = 1
            } else {
                turn = 0
            }
        }
        chooseKnight.setOnClickListener {
            pieceFocused.type = 2
            if (isPiece(board[moveY][moveX])) {
                resetMovesWithNoCapture(pieceFocused.color)
                findPiece(moveY, moveX).isActive = false
            } else {
                incrementMovesWithNoCapture(pieceFocused.color)
            }
            piecesList.set(
                piecesList.indexOf(pieceFocused), Piece(
                    pieceFocused.type,
                    pieceFocused.color,
                    moveX,
                    moveY,
                    true
                )
            )
            resetBoard()
            checkChecks()
            pieceFocused = Piece(0, 0, 0, 0, false)
            isChoose = false
            pawnSpecialWhite = false
            pawnSpecialBlack = false
            if (turn == 0) {
                turn = 1
            } else {
                turn = 0
            }
        }
        chooseRook.setOnClickListener {
            pieceFocused.type = 3
            if (isPiece(board[moveY][moveX])) {
                resetMovesWithNoCapture(pieceFocused.color)
                findPiece(moveY, moveX).isActive = false
            } else {
                incrementMovesWithNoCapture(pieceFocused.color)
            }
            piecesList.set(
                piecesList.indexOf(pieceFocused), Piece(
                    pieceFocused.type,
                    pieceFocused.color,
                    moveX,
                    moveY,
                    true
                )
            )
            resetBoard()
            checkChecks()
            pieceFocused = Piece(0, 0, 0, 0, false)
            isChoose = false
            pawnSpecialWhite = false
            pawnSpecialBlack = false
            if (turn == 0) {
                turn = 1
            } else {
                turn = 0
            }
        }
        chooseQueen.setOnClickListener {
            pieceFocused.type = 4
            if (isPiece(board[moveY][moveX])) {
                resetMovesWithNoCapture(pieceFocused.color)
                findPiece(moveY, moveX).isActive = false
            } else {
                incrementMovesWithNoCapture(pieceFocused.color)
            }
            piecesList.set(
                piecesList.indexOf(pieceFocused), Piece(
                    pieceFocused.type,
                    pieceFocused.color,
                    moveX,
                    moveY,
                    true
                )
            )
            resetBoard()
            checkChecks()
            pieceFocused = Piece(0, 0, 0, 0, false)
            isChoose = false
            pawnSpecialWhite = false
            pawnSpecialBlack = false
            if (turn == 0) {
                turn = 1
            } else {
                turn = 0
            }
        }
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

    private fun findPiece(i: Int, j: Int): Piece {
        for (piece in piecesList) {
            if (piece.isActive) {
                if (piece.positionX == j && piece.positionY == i) {
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
        backButton = rootView.findViewById(R.id.board_image_back)
        chooseLayout = rootView.findViewById(R.id.board_choose_layout)
        chooseBishop = rootView.findViewById(R.id.board_choose_bishop)
        chooseKnight = rootView.findViewById(R.id.board_choose_knight)
        chooseRook = rootView.findViewById(R.id.board_choose_rook)
        chooseQueen = rootView.findViewById(R.id.board_choose_queen)
        checkBlackTextView = rootView.findViewById(R.id.blackCheckTextView)
        checkWhiteTextView = rootView.findViewById(R.id.whiteCheckTextView)
        winTextView = rootView.findViewById(R.id.winTextView)
    }
}