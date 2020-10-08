package com.partos.chess.logic.listeners

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.partos.chess.R
import com.partos.chess.logic.BoardHelper
import com.partos.chess.logic.PiecesHelper
import com.partos.chess.models.Piece

class BoardListeners {

    private lateinit var backButton: ImageView
    private lateinit var movesList: Array<Array<Boolean>>
    private lateinit var board: Array<Array<ImageView>>
    private lateinit var moves: Array<Array<ImageView>>
    private lateinit var piecesList: ArrayList<Piece>
    private lateinit var context: Context
    private lateinit var pieceFocused: Piece

    fun initListeners(
        rootView: View,
        fragmentManager: FragmentManager,
        board: Array<Array<ImageView>>,
        moves: Array<Array<ImageView>>,
        piecesList: ArrayList<Piece>
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
                        if (isPiece(board[i][j])) {
                            findPiece(i, j).isActive = false
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
                        pieceFocused = Piece(0, 0, 0, 0, false)
                        resetBoard()
                    } else if (isPiece(board[i][j])) {
                        resetBoard()
                        pieceFocused = findPiece(i, j)
                        showMoves(pieceFocused)
                    } else {
                        resetBoard()
                    }
                }
            }
        }
    }

    private fun showMoves(pieceFocused: Piece) {
        when (pieceFocused.type) {
            0 -> {
                if (pieceFocused.color == 0) {
                    if (pieceFocused.positionY == 6) {
                        if (pieceFocused.positionX > 0) {
                            if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                                movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 1] =
                                    true
                            }
                        }
                        if (pieceFocused.positionX < 7) {
                            if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                                movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 1] =
                                    true
                            }
                        }
                        if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                            movesList[pieceFocused.positionY - 1][pieceFocused.positionX] = true
                        }
                        if (!isPiece(board[pieceFocused.positionY - 2][pieceFocused.positionX])) {
                            movesList[pieceFocused.positionY - 2][pieceFocused.positionX] = true
                        }
                    } else if (pieceFocused.positionY > 1) {
                        if (pieceFocused.positionX > 0) {
                            if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                                movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 1] =
                                    true
                            }
                        }
                        if (pieceFocused.positionX < 7) {
                            if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                                movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 1] =
                                    true
                            }
                        }
                        if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                            movesList[pieceFocused.positionY - 1][pieceFocused.positionX] = true
                        }
                    } else if (pieceFocused.positionY == 1) {
                        if (pieceFocused.positionX > 0) {
                            if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                                movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 1] =
                                    true
                            }
                        }
                        if (pieceFocused.positionX < 7) {
                            if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                                movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 1] =
                                    true
                            }
                        }
                        if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                            movesList[pieceFocused.positionY - 1][pieceFocused.positionX] = true
                        }
                    }
                }
                resetMoves()
            }
        }
    }

    private fun resetMoves() {
        for (i in 0..7) {
            for (j in 0..7) {
                if (movesList[i][j]) {
                    moves[i][j].visibility = View.VISIBLE
                }
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
        for (i in 0..7) {
            for (j in 0..7) {
                movesList[i][j] = false
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

    private fun attachViews(rootView: View) {
        backButton = rootView.findViewById(R.id.board_image_back)
    }
}