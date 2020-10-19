package com.partos.chess.logic.listeners

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import com.partos.chess.R
import com.partos.chess.logic.BoardHelper
import com.partos.chess.models.Piece

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
    private lateinit var pieceFocused: Piece
    private var isChoose = false
    private var moveX = 0
    private var moveY = 0
    private var turn = 0
    private var pawnSpecialX = 0
    private var pawnSpecialY = 0
    private var pawnSpecialWhite = false
    private var pawnSpecialBlack = false

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
                            piecesList.set(
                                piecesList.indexOf(pieceFocused), Piece(
                                    pieceFocused.type,
                                    pieceFocused.color,
                                    pawnSpecialX,
                                    pawnSpecialY - 1,
                                    true
                                )
                            )
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            resetBoard()
                            pawnSpecialBlack = false
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                        } else if (pieceFocused.color == 1 && pawnSpecialWhite && i == pawnSpecialY + 1 && j == pawnSpecialX && pieceFocused.type == 0) {

                            findPiece(pawnSpecialY, pawnSpecialX).isActive = false
                            piecesList.set(
                                piecesList.indexOf(pieceFocused), Piece(
                                    pieceFocused.type,
                                    pieceFocused.color,
                                    pawnSpecialX,
                                    pawnSpecialY + 1,
                                    true
                                )
                            )
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            resetBoard()
                            pawnSpecialWhite = false
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                        } else if (i == pieceFocused.positionY + 2 && pieceFocused.color == 1 && pieceFocused.type == 0) {
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
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            resetBoard()
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                        } else if (i == pieceFocused.positionY - 2 && pieceFocused.color == 0 && pieceFocused.type == 0) {
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
                            pieceFocused = Piece(0, 0, 0, 0, false)
                            resetBoard()
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                        } else {
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
                            pawnSpecialWhite = false
                            pawnSpecialBlack = false
                            if (turn == 0) {
                                turn = 1
                            } else {
                                turn = 0
                            }
                        }
                    } else if (isPiece(board[i][j])) {
                        isChoose = false
                        resetBoard()
                        if (findPiece(i, j).color == turn) {
                            pieceFocused = findPiece(i, j)
                            showMoves(pieceFocused)
                        }
                    } else {
                        isChoose = false
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
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY - 1),
                        (pieceFocused.positionX)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY - 1][pieceFocused.positionX] = true
                }
            }
        }
        if (pieceFocused.positionY >= 1 && pieceFocused.positionX <= 6) {
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 1] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY - 1),
                        (pieceFocused.positionX + 1)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 1] = true
                }
            }
        }
        if (pieceFocused.positionX <= 6) {
            if (!isPiece(board[pieceFocused.positionY][pieceFocused.positionX + 1])) {
                movesList[pieceFocused.positionY][pieceFocused.positionX + 1] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY),
                        (pieceFocused.positionX + 1)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY][pieceFocused.positionX + 1] = true
                }
            }
        }
        if (pieceFocused.positionY <= 6 && pieceFocused.positionX <= 6) {
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX + 1] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY + 1),
                        (pieceFocused.positionX + 1)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY + 1][pieceFocused.positionX + 1] = true
                }
            }
        }
        if (pieceFocused.positionY <= 6) {
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY + 1),
                        (pieceFocused.positionX)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY + 1][pieceFocused.positionX] = true
                }
            }
        }
        if (pieceFocused.positionY <= 6 && pieceFocused.positionX >= 1) {
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX - 1] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY + 1),
                        (pieceFocused.positionX - 1)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY + 1][pieceFocused.positionX - 1] = true
                }
            }
        }
        if (pieceFocused.positionX >= 1) {
            if (!isPiece(board[pieceFocused.positionY][pieceFocused.positionX - 1])) {
                movesList[pieceFocused.positionY][pieceFocused.positionX - 1] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY),
                        (pieceFocused.positionX - 1)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY][pieceFocused.positionX - 1] = true
                }
            }
        }
        if (pieceFocused.positionY >= 1 && pieceFocused.positionX >= 1) {
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 1] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY - 1),
                        (pieceFocused.positionX - 1)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 1] = true
                }
            }
        }
    }

    private fun showQueenMoves(pieceFocused: Piece, i: Int) {
        showBishopMoves(pieceFocused, i)
        showRookMoves(pieceFocused, i)
    }

    private fun showKnightMoves(pieceFocused: Piece, color: Int) {
        if (pieceFocused.positionX + 1 <= 7 && pieceFocused.positionY - 2 >= 0) {
            if (!isPiece(board[pieceFocused.positionY - 2][pieceFocused.positionX + 1])) {
                movesList[pieceFocused.positionY - 2][pieceFocused.positionX + 1] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY - 2),
                        (pieceFocused.positionX + 1)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY - 2][pieceFocused.positionX + 1] = true
                }
            }
        }
        if (pieceFocused.positionX + 2 <= 7 && pieceFocused.positionY - 1 >= 0) {
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 2])) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 2] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY - 1),
                        (pieceFocused.positionX + 2)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 2] = true
                }
            }
        }
        if (pieceFocused.positionX + 2 <= 7 && pieceFocused.positionY + 1 <= 7) {
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 2])) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX + 2] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY + 1),
                        (pieceFocused.positionX + 2)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY + 1][pieceFocused.positionX + 2] = true
                }
            }
        }
        if (pieceFocused.positionX + 1 <= 7 && pieceFocused.positionY + 2 <= 7) {
            if (!isPiece(board[pieceFocused.positionY + 2][pieceFocused.positionX + 1])) {
                movesList[pieceFocused.positionY + 2][pieceFocused.positionX + 1] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY + 2),
                        (pieceFocused.positionX + 1)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY + 2][pieceFocused.positionX + 1] = true
                }
            }
        }
        if (pieceFocused.positionX - 1 >= 0 && pieceFocused.positionY + 2 <= 7) {
            if (!isPiece(board[pieceFocused.positionY + 2][pieceFocused.positionX - 1])) {
                movesList[pieceFocused.positionY + 2][pieceFocused.positionX - 1] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY + 2),
                        (pieceFocused.positionX - 1)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY + 2][pieceFocused.positionX - 1] = true
                }
            }
        }
        if (pieceFocused.positionX - 2 >= 0 && pieceFocused.positionY + 1 <= 7) {
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 2])) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX - 2] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY + 1),
                        (pieceFocused.positionX - 2)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY + 1][pieceFocused.positionX - 2] = true
                }
            }
        }
        if (pieceFocused.positionX - 2 >= 0 && pieceFocused.positionY - 1 >= 0) {
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 2])) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 2] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY - 1),
                        (pieceFocused.positionX - 2)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 2] = true
                }
            }
        }
        if (pieceFocused.positionX - 1 >= 0 && pieceFocused.positionY - 2 >= 0) {
            if (!isPiece(board[pieceFocused.positionY - 2][pieceFocused.positionX - 1])) {
                movesList[pieceFocused.positionY - 2][pieceFocused.positionX - 1] = true
            } else {
                if (findPiece(
                        (pieceFocused.positionY - 2),
                        (pieceFocused.positionX - 1)
                    ).color == color
                ) {
                    movesList[pieceFocused.positionY - 2][pieceFocused.positionX - 1] = true
                }
            }
        }
    }

    private fun showRookMoves(pieceFocused: Piece, color: Int) {
        for (i in 1..8) {
            if ((pieceFocused.positionX + i) <= 7) {
                if (!isPiece(board[pieceFocused.positionY][pieceFocused.positionX + i])) {
                    movesList[pieceFocused.positionY][pieceFocused.positionX + i] = true
                } else {
                    if (findPiece(
                            (pieceFocused.positionY),
                            (pieceFocused.positionX + i)
                        ).color == color
                    ) {
                        movesList[pieceFocused.positionY][pieceFocused.positionX + i] = true
                    }
                    break
                }
            } else {
                break
            }
        }
        for (i in 1..8) {
            if ((pieceFocused.positionX - i) >= 0) {
                if (!isPiece(board[pieceFocused.positionY][pieceFocused.positionX - i])) {
                    movesList[pieceFocused.positionY][pieceFocused.positionX - i] = true
                } else {
                    if (findPiece(
                            (pieceFocused.positionY),
                            (pieceFocused.positionX - i)
                        ).color == color
                    ) {
                        movesList[pieceFocused.positionY][pieceFocused.positionX - i] = true
                    }
                    break
                }
            } else {
                break
            }
        }

        for (i in 1..8) {
            if ((pieceFocused.positionY - i) >= 0) {
                if (!isPiece(board[pieceFocused.positionY - i][pieceFocused.positionX])) {
                    movesList[pieceFocused.positionY - i][pieceFocused.positionX] = true
                } else {
                    if (findPiece(
                            (pieceFocused.positionY - i),
                            (pieceFocused.positionX)
                        ).color == color
                    ) {
                        movesList[pieceFocused.positionY - i][pieceFocused.positionX] = true
                    }
                    break
                }
            } else {
                break
            }
        }

        for (i in 1..8) {
            if ((pieceFocused.positionY + i) <= 7) {
                if (!isPiece(board[pieceFocused.positionY + i][pieceFocused.positionX])) {
                    movesList[pieceFocused.positionY + i][pieceFocused.positionX] = true
                } else {
                    if (findPiece(
                            (pieceFocused.positionY + i),
                            (pieceFocused.positionX)
                        ).color == color
                    ) {
                        movesList[pieceFocused.positionY + i][pieceFocused.positionX] = true
                    }
                    break
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
                if (!isPiece(board[pieceFocused.positionY - i][pieceFocused.positionX - i])) {
                    movesList[pieceFocused.positionY - i][pieceFocused.positionX - i] = true
                } else {
                    if (findPiece(
                            (pieceFocused.positionY - i),
                            (pieceFocused.positionX - i)
                        ).color == color
                    ) {
                        movesList[pieceFocused.positionY - i][pieceFocused.positionX - i] = true
                    }
                    break
                }
            } else {
                break
            }
        }

        // Show top-right moves
        for (i in 1..8) {
            if (pieceFocused.positionX + i <= 7 && pieceFocused.positionY - i >= 0) {
                if (!isPiece(board[pieceFocused.positionY - i][pieceFocused.positionX + i])) {
                    movesList[pieceFocused.positionY - i][pieceFocused.positionX + i] = true
                } else {
                    if (findPiece(
                            (pieceFocused.positionY - i),
                            (pieceFocused.positionX + i)
                        ).color == color
                    ) {
                        movesList[pieceFocused.positionY - i][pieceFocused.positionX + i] = true
                    }
                    break
                }
            } else {
                break
            }
        }

        // Show bottom-right moves
        for (i in 1..8) {
            if (pieceFocused.positionX + i <= 7 && pieceFocused.positionY + i <= 7) {
                if (!isPiece(board[pieceFocused.positionY + i][pieceFocused.positionX + i])) {
                    movesList[pieceFocused.positionY + i][pieceFocused.positionX + i] = true
                } else {
                    if (findPiece(
                            (pieceFocused.positionY + i),
                            (pieceFocused.positionX + i)
                        ).color == color
                    ) {
                        movesList[pieceFocused.positionY + i][pieceFocused.positionX + i] = true
                    }
                    break
                }
            } else {
                break
            }
        }

        // Show bottom-left moves
        for (i in 1..8) {
            if (pieceFocused.positionX - i >= 0 && pieceFocused.positionY + i <= 7) {
                if (!isPiece(board[pieceFocused.positionY + i][pieceFocused.positionX - i])) {
                    movesList[pieceFocused.positionY + i][pieceFocused.positionX - i] = true
                } else {
                    if (findPiece(
                            (pieceFocused.positionY + i),
                            (pieceFocused.positionX - i)
                        ).color == color
                    ) {
                        movesList[pieceFocused.positionY + i][pieceFocused.positionX - i] = true
                    }
                    break
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
                    if (findPiece(
                            (pieceFocused.positionY + 1),
                            (pieceFocused.positionX - 1)
                        ).color == 0
                    ) {
                        movesList[pieceFocused.positionY + 1][pieceFocused.positionX - 1] = true
                    }
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    if (findPiece(
                            (pieceFocused.positionY + 1),
                            (pieceFocused.positionX + 1)
                        ).color == 0
                    ) {
                        movesList[pieceFocused.positionY + 1][pieceFocused.positionX + 1] = true
                    }
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX] = true
            }
            if (!isPiece(board[pieceFocused.positionY + 2][pieceFocused.positionX]) && !isPiece(
                    board[pieceFocused.positionY + 1][pieceFocused.positionX]
                )
            ) {
                movesList[pieceFocused.positionY + 2][pieceFocused.positionX] = true
            }
        } else if (pieceFocused.positionY == 4) {
            if (pawnSpecialWhite) {
                if (pawnSpecialY == pieceFocused.positionY && (pawnSpecialX == (pieceFocused.positionX + 1) || pawnSpecialX == (pieceFocused.positionX - 1))) {
                    movesList[pawnSpecialY + 1][pawnSpecialX] = true
                }
            }
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                    if (findPiece(
                            (pieceFocused.positionY + 1),
                            (pieceFocused.positionX - 1)
                        ).color == 0
                    ) {
                        movesList[pieceFocused.positionY + 1][pieceFocused.positionX - 1] = true
                    }
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    if (findPiece(
                            (pieceFocused.positionY + 1),
                            (pieceFocused.positionX + 1)
                        ).color == 0
                    ) {
                        movesList[pieceFocused.positionY + 1][pieceFocused.positionX + 1] = true
                    }
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX] = true
            }
        } else if (pieceFocused.positionY < 6) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                    if (findPiece(
                            (pieceFocused.positionY + 1),
                            (pieceFocused.positionX - 1)
                        ).color == 0
                    ) {
                        movesList[pieceFocused.positionY + 1][pieceFocused.positionX - 1] = true
                    }
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    if (findPiece(
                            (pieceFocused.positionY + 1),
                            (pieceFocused.positionX + 1)
                        ).color == 0
                    ) {
                        movesList[pieceFocused.positionY + 1][pieceFocused.positionX + 1] = true
                    }
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX] = true
            }
        } else if (pieceFocused.positionY == 6) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                    if (findPiece(
                            (pieceFocused.positionY + 1),
                            (pieceFocused.positionX - 1)
                        ).color == 0
                    ) {
                        movesList[pieceFocused.positionY + 1][pieceFocused.positionX - 1] = true
                    }
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    if (findPiece(
                            (pieceFocused.positionY + 1),
                            (pieceFocused.positionX + 1)
                        ).color == 0
                    ) {
                        movesList[pieceFocused.positionY + 1][pieceFocused.positionX + 1] = true
                    }
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                movesList[pieceFocused.positionY + 1][pieceFocused.positionX] = true
            }
            isChoose = true
        }
    }

    private fun showWhitePawnMoves(pieceFocused: Piece) {
        // Ruch startowy
        if (pieceFocused.positionY == 6) {
            // Bicie
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    if (findPiece(
                            (pieceFocused.positionY - 1),
                            (pieceFocused.positionX - 1)
                        ).color == 1
                    ) {
                        movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 1] = true
                    }
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    if (findPiece(
                            (pieceFocused.positionY - 1),
                            (pieceFocused.positionX + 1)
                        ).color == 1
                    ) {
                        movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 1] = true
                    }
                }
            }
            // Ruch o 1 do przodu
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX] = true
            }

            // Ruch o 2 do przodu
            if (!isPiece(board[pieceFocused.positionY - 2][pieceFocused.positionX]) && !isPiece(
                    board[pieceFocused.positionY - 1][pieceFocused.positionX]
                )
            ) {
                movesList[pieceFocused.positionY - 2][pieceFocused.positionX] = true
            }
        } else if (pieceFocused.positionY == 3) {
            if (pawnSpecialBlack) {
                if (pawnSpecialY == pieceFocused.positionY && (pawnSpecialX == (pieceFocused.positionX + 1) || pawnSpecialX == (pieceFocused.positionX - 1))) {
                    movesList[pawnSpecialY - 1][pawnSpecialX] = true
                }
            }
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    if (findPiece(
                            (pieceFocused.positionY - 1),
                            (pieceFocused.positionX - 1)
                        ).color == 1
                    ) {
                        movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 1] = true
                    }
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    if (findPiece(
                            (pieceFocused.positionY - 1),
                            (pieceFocused.positionX + 1)
                        ).color == 1
                    ) {
                        movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 1] = true
                    }
                }
            }
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX] = true
            }
        } else if (pieceFocused.positionY > 1) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    if (findPiece(
                            (pieceFocused.positionY - 1),
                            (pieceFocused.positionX - 1)
                        ).color == 1
                    ) {
                        movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 1] = true
                    }
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    if (findPiece(
                            (pieceFocused.positionY - 1),
                            (pieceFocused.positionX + 1)
                        ).color == 1
                    ) {
                        movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 1] = true
                    }
                }
            }
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX] = true
            }
        } else if (pieceFocused.positionY == 1) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    if (findPiece(
                            (pieceFocused.positionY - 1),
                            (pieceFocused.positionX - 1)
                        ).color == 1
                    ) {
                        movesList[pieceFocused.positionY - 1][pieceFocused.positionX - 1] = true
                    }
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    if (findPiece(
                            (pieceFocused.positionY - 1),
                            (pieceFocused.positionX + 1)
                        ).color == 1
                    ) {
                        movesList[pieceFocused.positionY - 1][pieceFocused.positionX + 1] = true
                    }
                }
            }
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                movesList[pieceFocused.positionY - 1][pieceFocused.positionX] = true
            }
            isChoose = true
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
        chooseBishop.setOnClickListener {
            pieceFocused.type = 1
            if (isPiece(board[moveY][moveX])) {
                findPiece(moveY, moveX).isActive = false
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
            pieceFocused = Piece(0, 0, 0, 0, false)
            resetBoard()
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
                findPiece(moveY, moveX).isActive = false
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
            pieceFocused = Piece(0, 0, 0, 0, false)
            resetBoard()
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
                findPiece(moveY, moveX).isActive = false
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
            pieceFocused = Piece(0, 0, 0, 0, false)
            resetBoard()
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
                findPiece(moveY, moveX).isActive = false
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
            pieceFocused = Piece(0, 0, 0, 0, false)
            resetBoard()
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
        chooseLayout = rootView.findViewById(R.id.board_choose_layout)
        chooseBishop = rootView.findViewById(R.id.board_choose_bishop)
        chooseKnight = rootView.findViewById(R.id.board_choose_knight)
        chooseRook = rootView.findViewById(R.id.board_choose_rook)
        chooseQueen = rootView.findViewById(R.id.board_choose_queen)
    }
}