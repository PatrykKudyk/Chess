package com.partos.chess.logic

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.partos.chess.R
import com.partos.chess.models.Piece
import kotlin.math.abs

class PiecesHelper {

    private lateinit var movesList: Array<Array<Boolean>>
    private lateinit var board: Array<Array<ImageView>>
    private lateinit var piecesList: ArrayList<Piece>
    private var checkMate = false
    private var hasMoves = false

    fun initPieces(): ArrayList<Piece> {
        val piecesList = ArrayList<Piece>()
        //white pawns
        piecesList.add(Piece(0, 0, 0, 6, true))
        piecesList.add(Piece(0, 0, 1, 6, true))
        piecesList.add(Piece(0, 0, 2, 6, true))
        piecesList.add(Piece(0, 0, 3, 6, true))
        piecesList.add(Piece(0, 0, 4, 6, true))
        piecesList.add(Piece(0, 0, 5, 6, true))
        piecesList.add(Piece(0, 0, 6, 6, true))
        piecesList.add(Piece(0, 0, 7, 6, true))

        //black pawns
        piecesList.add(Piece(0, 1, 0, 1, true))
        piecesList.add(Piece(0, 1, 1, 1, true))
        piecesList.add(Piece(0, 1, 2, 1, true))
        piecesList.add(Piece(0, 1, 3, 1, true))
        piecesList.add(Piece(0, 1, 4, 1, true))
        piecesList.add(Piece(0, 1, 5, 1, true))
        piecesList.add(Piece(0, 1, 6, 1, true))
        piecesList.add(Piece(0, 1, 7, 1, true))

        //white rest
        piecesList.add(Piece(3, 0, 0, 7, true))
        piecesList.add(Piece(3, 0, 7, 7, true))
        piecesList.add(Piece(2, 0, 1, 7, true))
        piecesList.add(Piece(2, 0, 6, 7, true))
        piecesList.add(Piece(1, 0, 2, 7, true))
        piecesList.add(Piece(1, 0, 5, 7, true))
        piecesList.add(Piece(4, 0, 3, 7, true))
        piecesList.add(Piece(5, 0, 4, 7, true))

        //black rest
        piecesList.add(Piece(3, 1, 0, 0, true))
        piecesList.add(Piece(3, 1, 7, 0, true))
        piecesList.add(Piece(2, 1, 1, 0, true))
        piecesList.add(Piece(2, 1, 6, 0, true))
        piecesList.add(Piece(1, 1, 2, 0, true))
        piecesList.add(Piece(1, 1, 5, 0, true))
        piecesList.add(Piece(4, 1, 3, 0, true))
        piecesList.add(Piece(5, 1, 4, 0, true))

        // STALEMATE
//        piecesList.add(Piece(5, 1, 2, 1, true))
//        piecesList.add(Piece(0, 0, 3, 1, true))
//        piecesList.add(Piece(5, 0, 4, 2, true))


//        piecesList.add(Piece(0, 1, 0, 1, true))

        // STALEMATE


        return piecesList
    }

    fun getPieceImage(type: Int, color: Int, context: Context): Drawable {
        var drawable = (context.getDrawable(R.drawable.pawn_white)) as Drawable
        when (type) {
            0 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.pawn_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.pawn_black)) as Drawable
                }
            }
            1 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.bishop_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.bishop_black)) as Drawable
                }
            }

            2 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.knight_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.knight_black)) as Drawable
                }
            }
            3 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.rook_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.rook_black)) as Drawable
                }
            }
            4 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.queen_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.queen_black)) as Drawable
                }
            }
            5 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.king_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.king_black)) as Drawable
                }
            }
        }
        return drawable
    }

    private fun createMovesList(): Array<Array<Boolean>> {
        var array1 = arrayOf(false, false, false, false, false, false, false, false)
        var array2 = arrayOf(false, false, false, false, false, false, false, false)
        var array3 = arrayOf(false, false, false, false, false, false, false, false)
        var array4 = arrayOf(false, false, false, false, false, false, false, false)
        var array5 = arrayOf(false, false, false, false, false, false, false, false)
        var array6 = arrayOf(false, false, false, false, false, false, false, false)
        var array7 = arrayOf(false, false, false, false, false, false, false, false)
        var array8 = arrayOf(false, false, false, false, false, false, false, false)
        return arrayOf(array1, array2, array3, array4, array5, array6, array7, array8)
    }

    fun isCheck(
        pieces: ArrayList<Piece>,
        color: Int,
        boardTaken: Array<Array<ImageView>>
    ): Boolean {
        board = boardTaken.copyOf()
        movesList = createMovesList()
        piecesList = pieces
        if (color == 0) {
            for (piece in pieces) {
                if (piece.color == 1 && piece.isActive) {
                    showMoves(piece)
                }
            }
            var king = findKing(0)
            return movesList[king.positionY][king.positionX]
        } else {
            for (piece in pieces) {
                if (piece.color == 0 && piece.isActive) {
                    showMoves(piece)
                }
            }
            var king = findKing(1)
            return movesList[king.positionY][king.positionX]
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

    private fun findPiece(piecesList: ArrayList<Piece>, i: Int, j: Int): Piece {
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
            if (piece.type == 5 && piece.color == color) {
                return piece
            }
        }
        return Piece(0, 0, 0, 0, false)
    }

    private fun isPiece(image: ImageView): Boolean {
        if (image.drawable != null) {
            return true
        }
        return false
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

    fun isCheckMate(
        pieces: ArrayList<Piece>,
        color: Int,
        boardTaken: Array<Array<ImageView>>,
        context: Context
    ): Boolean {
        board = boardTaken.copyOf()
        movesList = createMovesList()
        piecesList = pieces
        if (color == 0) {
            checkMate = true
            for (piece in pieces) {
                if (piece.color == 0 && piece.isActive) {
                    checkMoves(piece, context)
                    board = boardTaken.copyOf()
                    movesList = createMovesList()
                    piecesList = pieces
                }
                if (!checkMate) {
                    return false
                }
            }
            return true
        } else {
            checkMate = true
            for (piece in pieces) {
                if (piece.color == 1 && piece.isActive) {
                    checkMoves(piece, context)
                    board = boardTaken.copyOf()
                    movesList = createMovesList()
                    piecesList = pieces
                }
                if (!checkMate) {
                    return false
                }
            }
            return true
        }
    }

    private fun checkMoves(pieceFocused: Piece, context: Context) {
        when (pieceFocused.type) {
            0 -> {
                if (pieceFocused.color == 0) {
                    checkWhitePawnMoves(pieceFocused, context)
                } else {
                    checkBlackPawnMoves(pieceFocused, context)
                }
            }
            1 -> {
                if (pieceFocused.color == 0) {
                    checkBishopMoves(pieceFocused, 1, context)
                } else {
                    checkBishopMoves(pieceFocused, 0, context)
                }
            }
            2 -> {
                if (pieceFocused.color == 0) {
                    checkKnightMoves(pieceFocused, 1, context)
                } else {
                    checkKnightMoves(pieceFocused, 0, context)
                }
            }
            3 -> {
                if (pieceFocused.color == 0) {
                    checkRookMoves(pieceFocused, 1, context)
                } else {
                    checkRookMoves(pieceFocused, 0, context)
                }
            }
            4 -> {
                if (pieceFocused.color == 0) {
                    checkQueenMoves(pieceFocused, 1, context)
                } else {
                    checkQueenMoves(pieceFocused, 0, context)
                }
            }
            5 -> {
                if (pieceFocused.color == 0) {
                    checkKingMoves(pieceFocused, 1, context)
                } else {
                    checkKingMoves(pieceFocused, 0, context)
                }
            }
        }
    }

    private fun checkQueenMoves(pieceFocused: Piece, color: Int, context: Context) {
        checkBishopMoves(pieceFocused, color, context)
        checkRookMoves(pieceFocused, color, context)
    }

    private fun checkKnightMoves(pieceFocused: Piece, color: Int, context: Context) {
        if (pieceFocused.positionX + 1 <= 7 && pieceFocused.positionY - 2 >= 0) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX + 1, 0, pieceFocused, context)
            } else {
                canPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX + 1, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX + 2 <= 7 && pieceFocused.positionY - 1 >= 0) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX + 2, 0, pieceFocused, context)
            } else {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX + 2, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX + 2 <= 7 && pieceFocused.positionY + 1 <= 7) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX + 2, 0, pieceFocused, context)
            } else {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX + 2, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX + 1 <= 7 && pieceFocused.positionY + 2 <= 7) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX + 1, 0, pieceFocused, context)
            } else {
                canPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX + 1, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX - 1 >= 0 && pieceFocused.positionY + 2 <= 7) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX - 1, 0, pieceFocused, context)
            } else {
                canPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX - 1, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX - 2 >= 0 && pieceFocused.positionY + 1 <= 7) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX - 2, 0, pieceFocused, context)
            } else {
                canPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX - 2, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX - 2 >= 0 && pieceFocused.positionY - 1 >= 0) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX - 2, 0, pieceFocused, context)
            } else {
                canPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX - 2, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX - 1 >= 0 && pieceFocused.positionY - 2 >= 0) {
            if (color == 0) {
                canPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX - 1, 0, pieceFocused, context)
            } else {
                canPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX - 1, 1, pieceFocused, context)
            }
        }
    }

    private fun checkRookMoves(pieceFocused: Piece, color: Int, context: Context) {
        for (i in 1..8) {
            if ((pieceFocused.positionX + i) <= 7) {
                if (color == 0) {
                    if (!canPieceMove(pieceFocused.positionY, pieceFocused.positionX + i, 0, pieceFocused, context)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY, pieceFocused.positionX + i, 1, pieceFocused, context)) {
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
                    if (!canPieceMove(pieceFocused.positionY, pieceFocused.positionX - i, 0, pieceFocused, context)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY, pieceFocused.positionX - i, 1, pieceFocused, context)) {
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
                    if (!canPieceMove(pieceFocused.positionY - i, pieceFocused.positionX, 0, pieceFocused, context)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY - i, pieceFocused.positionX, 1, pieceFocused, context)) {
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
                    if (!canPieceMove(pieceFocused.positionY + i, pieceFocused.positionX, 0, pieceFocused, context)) {
                        break
                    }
                } else {
                    if (!canPieceMove(pieceFocused.positionY + i, pieceFocused.positionX, 1, pieceFocused, context)) {
                        break
                    }
                }
            } else {
                break
            }
        }
    }

    private fun checkBishopMoves(pieceFocused: Piece, color: Int, context: Context) {
        for (i in 1..8) {
            if (pieceFocused.positionX - i >= 0 && pieceFocused.positionY - i >= 0) {
                if (color == 0) {
                    if (!canPieceMove(
                            pieceFocused.positionY - i,
                            pieceFocused.positionX - i,
                            0,
                            pieceFocused,
                            context
                        )
                    ) {
                        break
                    }
                } else {
                    if (!canPieceMove(
                            pieceFocused.positionY - i,
                            pieceFocused.positionX - i,
                            1,
                            pieceFocused,
                            context
                        )
                    ) {
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
                    if (!canPieceMove(
                            pieceFocused.positionY - i,
                            pieceFocused.positionX + i,
                            0,
                            pieceFocused,
                            context
                        )
                    ) {
                        break
                    }
                } else {
                    if (!canPieceMove(
                            pieceFocused.positionY - i,
                            pieceFocused.positionX + i,
                            1,
                            pieceFocused,
                            context
                        )
                    ) {
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
                    if (!canPieceMove(
                            pieceFocused.positionY + i,
                            pieceFocused.positionX + i,
                            0,
                            pieceFocused,
                            context
                        )
                    ) {
                        break
                    }
                } else {
                    if (!canPieceMove(
                            pieceFocused.positionY + i,
                            pieceFocused.positionX + i,
                            1,
                            pieceFocused,
                            context
                        )
                    ) {
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
                    if (!canPieceMove(
                            pieceFocused.positionY + i,
                            pieceFocused.positionX - i,
                            0,
                            pieceFocused,
                            context
                        )
                    ) {
                        break
                    }
                } else {
                    if (!canPieceMove(
                            pieceFocused.positionY + i,
                            pieceFocused.positionX - i,
                            1,
                            pieceFocused,
                            context
                        )
                    ) {
                        break
                    }
                }
            } else {
                break
            }
        }
    }

    private fun checkBlackPawnMoves(pieceFocused: Piece, context: Context) {
        if (pieceFocused.positionY == 1) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                    canPieceMove(
                        pieceFocused.positionY + 1,
                        pieceFocused.positionX - 1,
                        0,
                        pieceFocused,
                        context
                    )
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    canPieceMove(
                        pieceFocused.positionY + 1,
                        pieceFocused.positionX + 1,
                        0,
                        pieceFocused,
                        context
                    )
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                canPieceMove(
                    pieceFocused.positionY + 1,
                    pieceFocused.positionX,
                    0,
                    pieceFocused,
                    context
                )
            }
            if (!isPiece(board[pieceFocused.positionY + 2][pieceFocused.positionX]) && !isPiece(
                    board[pieceFocused.positionY + 1][pieceFocused.positionX]
                )
            ) {
                canPieceMove(
                    pieceFocused.positionY + 2,
                    pieceFocused.positionX,
                    0,
                    pieceFocused,
                    context
                )
            }
        } else if (pieceFocused.positionY < 7) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                    canPieceMove(
                        pieceFocused.positionY + 1,
                        pieceFocused.positionX - 1,
                        0,
                        pieceFocused,
                        context
                    )
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    canPieceMove(
                        pieceFocused.positionY + 1,
                        pieceFocused.positionX + 1,
                        0,
                        pieceFocused,
                        context
                    )
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                canPieceMove(
                    pieceFocused.positionY + 1,
                    pieceFocused.positionX,
                    0,
                    pieceFocused,
                    context
                )
            }
        }
    }

    private fun checkWhitePawnMoves(pieceFocused: Piece, context: Context) {
        if (pieceFocused.positionY == 6) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    canPieceMove(
                        pieceFocused.positionY - 1,
                        pieceFocused.positionX - 1,
                        1,
                        pieceFocused,
                        context
                    )
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    canPieceMove(
                        pieceFocused.positionY - 1,
                        pieceFocused.positionX + 1,
                        1,
                        pieceFocused,
                        context
                    )
                }
            }
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                canPieceMove(
                    pieceFocused.positionY - 1,
                    pieceFocused.positionX,
                    1,
                    pieceFocused,
                    context
                )
            }
            if (!isPiece(board[pieceFocused.positionY - 2][pieceFocused.positionX]) && !isPiece(
                    board[pieceFocused.positionY - 1][pieceFocused.positionX]
                )
            ) {
                canPieceMove(
                    pieceFocused.positionY - 2,
                    pieceFocused.positionX,
                    1,
                    pieceFocused,
                    context
                )
            }
        } else if (pieceFocused.positionY > 0) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    canPieceMove(
                        pieceFocused.positionY - 1,
                        pieceFocused.positionX - 1,
                        1,
                        pieceFocused,
                        context
                    )
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    canPieceMove(
                        pieceFocused.positionY - 1,
                        pieceFocused.positionX + 1,
                        1,
                        pieceFocused,
                        context
                    )
                }
            }
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                canPieceMove(
                    pieceFocused.positionY - 1,
                    pieceFocused.positionX,
                    1,
                    pieceFocused,
                    context
                )
            }
        }
    }

    private fun checkKingMoves(pieceFocused: Piece, color: Int, context: Context) {
        if (pieceFocused.positionY >= 1) {
            canKingMove(
                color,
                pieceFocused.positionY - 1,
                pieceFocused.positionX,
                pieceFocused,
                context
            )
        }
        if (pieceFocused.positionY >= 1 && pieceFocused.positionX <= 6) {
            canKingMove(
                color,
                pieceFocused.positionY - 1,
                pieceFocused.positionX + 1,
                pieceFocused,
                context
            )
        }
        if (pieceFocused.positionX <= 6) {
            canKingMove(
                color,
                pieceFocused.positionY,
                pieceFocused.positionX + 1,
                pieceFocused,
                context
            )
        }
        if (pieceFocused.positionY <= 6 && pieceFocused.positionX <= 6) {
            canKingMove(
                color,
                pieceFocused.positionY + 1,
                pieceFocused.positionX + 1,
                pieceFocused,
                context
            )
        }
        if (pieceFocused.positionY <= 6) {
            canKingMove(
                color,
                pieceFocused.positionY + 1,
                pieceFocused.positionX,
                pieceFocused,
                context
            )
        }
        if (pieceFocused.positionY <= 6 && pieceFocused.positionX >= 1) {
            canKingMove(
                color,
                pieceFocused.positionY + 1,
                pieceFocused.positionX - 1,
                pieceFocused,
                context
            )
        }
        if (pieceFocused.positionX >= 1) {
            canKingMove(
                color,
                pieceFocused.positionY,
                pieceFocused.positionX - 1,
                pieceFocused,
                context
            )
        }
        if (pieceFocused.positionY >= 1 && pieceFocused.positionX >= 1) {
            canKingMove(
                color,
                pieceFocused.positionY - 1,
                pieceFocused.positionX - 1,
                pieceFocused,
                context
            )
        }
    }

    private fun canKingMove(
        color: Int,
        positionY: Int,
        positionX: Int,
        pieceFocused: Piece,
        context: Context
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
                        checkMate = false
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
                    val pieceFocusedCopy = pieceFocused.copy()
                    pieceFocusedCopy.positionY = positionY
                    pieceFocusedCopy.positionX = positionX
                    val piecesListCopy2 = piecesList.toMutableList() as ArrayList<Piece>
                    piecesListCopy2.set(
                        piecesListCopy2.indexOf(pieceFocused),
                        Piece(
                            pieceFocused.type,
                            pieceFocused.color,
                            pieceFocusedCopy.positionX,
                            pieceFocusedCopy.positionY,
                            pieceFocused.isActive
                        )
                    )
                    val boardCopy2 = board.clone()
                    resetBoard(piecesListCopy2, boardCopy2, context)
                    if (!PiecesHelper().isCheck(piecesListCopy2, 1, boardCopy2)) {
                        checkMate = false
                    }
                    resetBoard(piecesList, board, context)
                }
            }
        }
        return false
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

    private fun canPieceMove(
        positionY: Int,
        positionX: Int,
        color: Int,
        pieceFocused: Piece,
        context: Context
    ): Boolean {
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
                checkMate = false
            }
            resetBoard(piecesList, board, context)
            return true
        } else {
            if (findPiece((positionY), (positionX)).color == color) {
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
                    piecesListCopy.indexOf(findPiece(positionY, positionX)),
                    Piece(0, 0, 0, 0, false)
                )
                val boardCopy = board.clone()
                resetBoard(piecesListCopy, boardCopy, context)
                if (!PiecesHelper().isCheck(piecesListCopy,  colorOpposite, boardCopy)) {
                    checkMate = false
                }
                resetBoard(piecesList, board, context)
                return false
            }
            return false
        }
    }

    fun isAnyMovePossible(
        pieces: ArrayList<Piece>,
        color: Int,
        boardTaken: Array<Array<ImageView>>,
        context: Context
    ): Boolean {
        board = boardTaken.copyOf()
        movesList = createMovesList()
        piecesList = pieces
        if (color == 0) {
            hasMoves = false
            for (piece in pieces) {
                if (piece.color == 0 && piece.isActive && piece.type != 5) {
                    checkIfPieceHasMoves(piece, context)
                    board = boardTaken.copyOf()
                    movesList = createMovesList()
                    piecesList = pieces
                }
                if (hasMoves) {
                    return true
                }
            }
            return false
        } else {
            hasMoves = false
            for (piece in pieces) {
                if (piece.color == 1 && piece.isActive && piece.type != 5) {
                    checkIfPieceHasMoves(piece, context)
                    board = boardTaken.copyOf()
                    movesList = createMovesList()
                    piecesList = pieces
                }
                if (hasMoves) {
                    return true
                }
            }
            return false
        }
    }

    private fun checkIfPieceHasMoves(pieceFocused: Piece, context: Context) {
        when (pieceFocused.type) {
            0 -> {
                if (pieceFocused.color == 0) {
                    checkIfWhitePawnHasMoves(pieceFocused, context)
                } else {
                    checkIfBlackPawnHasMoves(pieceFocused, context)
                }
            }
            1 -> {
                if (pieceFocused.color == 0) {
                    checkIfBishopHasMoves(pieceFocused, 1, context)
                } else {
                    checkIfBishopHasMoves(pieceFocused, 0, context)
                }
            }
            2 -> {
                if (pieceFocused.color == 0) {
                    checkIfKnightHasMoves(pieceFocused, 1, context)
                } else {
                    checkIfKnightHasMoves(pieceFocused, 0, context)
                }
            }
            3 -> {
                if (pieceFocused.color == 0) {
                    checkIfRookHasMoves(pieceFocused, 1, context)
                } else {
                    checkIfRookHasMoves(pieceFocused, 0, context)
                }
            }
            4 -> {
                if (pieceFocused.color == 0) {
                    checkIfQueenHasMoves(pieceFocused, 1, context)
                } else {
                    checkIfQueenHasMoves(pieceFocused, 0, context)
                }
            }
        }
    }

    private fun checkIfQueenHasMoves(pieceFocused: Piece, color: Int, context: Context) {
        checkIfBishopHasMoves(pieceFocused, color, context)
        checkIfRookHasMoves(pieceFocused, color, context)
    }

    private fun checkIfKnightHasMoves(pieceFocused: Piece, color: Int, context: Context) {
        if (pieceFocused.positionX + 1 <= 7 && pieceFocused.positionY - 2 >= 0) {
            if (color == 0) {
                hasPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX + 1, 0, pieceFocused, context)
            } else {
                hasPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX + 1, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX + 2 <= 7 && pieceFocused.positionY - 1 >= 0) {
            if (color == 0) {
                hasPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX + 2, 0, pieceFocused, context)
            } else {
                hasPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX + 2, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX + 2 <= 7 && pieceFocused.positionY + 1 <= 7) {
            if (color == 0) {
                hasPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX + 2, 0, pieceFocused, context)
            } else {
                hasPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX + 2, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX + 1 <= 7 && pieceFocused.positionY + 2 <= 7) {
            if (color == 0) {
                hasPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX + 1, 0, pieceFocused, context)
            } else {
                hasPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX + 1, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX - 1 >= 0 && pieceFocused.positionY + 2 <= 7) {
            if (color == 0) {
                hasPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX - 1, 0, pieceFocused, context)
            } else {
                hasPieceMove(pieceFocused.positionY + 2, pieceFocused.positionX - 1, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX - 2 >= 0 && pieceFocused.positionY + 1 <= 7) {
            if (color == 0) {
                hasPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX - 2, 0, pieceFocused, context)
            } else {
                hasPieceMove(pieceFocused.positionY + 1, pieceFocused.positionX - 2, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX - 2 >= 0 && pieceFocused.positionY - 1 >= 0) {
            if (color == 0) {
                hasPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX - 2, 0, pieceFocused, context)
            } else {
                hasPieceMove(pieceFocused.positionY - 1, pieceFocused.positionX - 2, 1, pieceFocused, context)
            }
        }
        if (pieceFocused.positionX - 1 >= 0 && pieceFocused.positionY - 2 >= 0) {
            if (color == 0) {
                hasPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX - 1, 0, pieceFocused, context)
            } else {
                hasPieceMove(pieceFocused.positionY - 2, pieceFocused.positionX - 1, 1, pieceFocused, context)
            }
        }
    }

    private fun checkIfRookHasMoves(pieceFocused: Piece, color: Int, context: Context) {
        for (i in 1..8) {
            if ((pieceFocused.positionX + i) <= 7) {
                if (color == 0) {
                    if (!hasPieceMove(pieceFocused.positionY, pieceFocused.positionX + i, 0, pieceFocused, context)) {
                        break
                    }
                } else {
                    if (!hasPieceMove(pieceFocused.positionY, pieceFocused.positionX + i, 1, pieceFocused, context)) {
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
                    if (!hasPieceMove(pieceFocused.positionY, pieceFocused.positionX - i, 0, pieceFocused, context)) {
                        break
                    }
                } else {
                    if (!hasPieceMove(pieceFocused.positionY, pieceFocused.positionX - i, 1, pieceFocused, context)) {
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
                    if (!hasPieceMove(pieceFocused.positionY - i, pieceFocused.positionX, 0, pieceFocused, context)) {
                        break
                    }
                } else {
                    if (!hasPieceMove(pieceFocused.positionY - i, pieceFocused.positionX, 1, pieceFocused, context)) {
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
                    if (!hasPieceMove(pieceFocused.positionY + i, pieceFocused.positionX, 0, pieceFocused, context)) {
                        break
                    }
                } else {
                    if (!hasPieceMove(pieceFocused.positionY + i, pieceFocused.positionX, 1, pieceFocused, context)) {
                        break
                    }
                }
            } else {
                break
            }
        }
    }

    private fun checkIfBishopHasMoves(pieceFocused: Piece, color: Int, context: Context) {
        for (i in 1..8) {
            if (pieceFocused.positionX - i >= 0 && pieceFocused.positionY - i >= 0) {
                if (color == 0) {
                    if (!hasPieceMove(
                            pieceFocused.positionY - i,
                            pieceFocused.positionX - i,
                            0,
                            pieceFocused,
                            context
                        )
                    ) {
                        break
                    }
                } else {
                    if (!hasPieceMove(
                            pieceFocused.positionY - i,
                            pieceFocused.positionX - i,
                            1,
                            pieceFocused,
                            context
                        )
                    ) {
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
                    if (!hasPieceMove(
                            pieceFocused.positionY - i,
                            pieceFocused.positionX + i,
                            0,
                            pieceFocused,
                            context
                        )
                    ) {
                        break
                    }
                } else {
                    if (!hasPieceMove(
                            pieceFocused.positionY - i,
                            pieceFocused.positionX + i,
                            1,
                            pieceFocused,
                            context
                        )
                    ) {
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
                    if (!hasPieceMove(
                            pieceFocused.positionY + i,
                            pieceFocused.positionX + i,
                            0,
                            pieceFocused,
                            context
                        )
                    ) {
                        break
                    }
                } else {
                    if (!hasPieceMove(
                            pieceFocused.positionY + i,
                            pieceFocused.positionX + i,
                            1,
                            pieceFocused,
                            context
                        )
                    ) {
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
                    if (!hasPieceMove(
                            pieceFocused.positionY + i,
                            pieceFocused.positionX - i,
                            0,
                            pieceFocused,
                            context
                        )
                    ) {
                        break
                    }
                } else {
                    if (!hasPieceMove(
                            pieceFocused.positionY + i,
                            pieceFocused.positionX - i,
                            1,
                            pieceFocused,
                            context
                        )
                    ) {
                        break
                    }
                }
            } else {
                break
            }
        }
    }

    private fun checkIfBlackPawnHasMoves(pieceFocused: Piece, context: Context) {
        if (pieceFocused.positionY == 1) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                    hasPieceMove(
                        pieceFocused.positionY + 1,
                        pieceFocused.positionX - 1,
                        0,
                        pieceFocused,
                        context
                    )
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    hasPieceMove(
                        pieceFocused.positionY + 1,
                        pieceFocused.positionX + 1,
                        0,
                        pieceFocused,
                        context
                    )
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                hasPieceMove(
                    pieceFocused.positionY + 1,
                    pieceFocused.positionX,
                    0,
                    pieceFocused,
                    context
                )
            }
            if (!isPiece(board[pieceFocused.positionY + 2][pieceFocused.positionX]) && !isPiece(
                    board[pieceFocused.positionY + 1][pieceFocused.positionX]
                )
            ) {
                hasPieceMove(
                    pieceFocused.positionY + 2,
                    pieceFocused.positionX,
                    0,
                    pieceFocused,
                    context
                )
            }
        } else if (pieceFocused.positionY < 7) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX - 1])) {
                    hasPieceMove(
                        pieceFocused.positionY + 1,
                        pieceFocused.positionX - 1,
                        0,
                        pieceFocused,
                        context
                    )
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX + 1])) {
                    hasPieceMove(
                        pieceFocused.positionY + 1,
                        pieceFocused.positionX + 1,
                        0,
                        pieceFocused,
                        context
                    )
                }
            }
            if (!isPiece(board[pieceFocused.positionY + 1][pieceFocused.positionX])) {
                hasPieceMove(
                    pieceFocused.positionY + 1,
                    pieceFocused.positionX,
                    0,
                    pieceFocused,
                    context
                )
            }
        }
    }

    private fun checkIfWhitePawnHasMoves(pieceFocused: Piece, context: Context) {
        if (pieceFocused.positionY == 6) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    hasPieceMove(
                        pieceFocused.positionY - 1,
                        pieceFocused.positionX - 1,
                        1,
                        pieceFocused,
                        context
                    )
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    hasPieceMove(
                        pieceFocused.positionY - 1,
                        pieceFocused.positionX + 1,
                        1,
                        pieceFocused,
                        context
                    )
                }
            }
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                hasPieceMove(
                    pieceFocused.positionY - 1,
                    pieceFocused.positionX,
                    1,
                    pieceFocused,
                    context
                )
            }
            if (!isPiece(board[pieceFocused.positionY - 2][pieceFocused.positionX]) && !isPiece(
                    board[pieceFocused.positionY - 1][pieceFocused.positionX]
                )
            ) {
                hasPieceMove(
                    pieceFocused.positionY - 2,
                    pieceFocused.positionX,
                    1,
                    pieceFocused,
                    context
                )
            }
        } else if (pieceFocused.positionY > 0) {
            if (pieceFocused.positionX > 0) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX - 1])) {
                    hasPieceMove(
                        pieceFocused.positionY - 1,
                        pieceFocused.positionX - 1,
                        1,
                        pieceFocused,
                        context
                    )
                }
            }
            if (pieceFocused.positionX < 7) {
                if (isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX + 1])) {
                    hasPieceMove(
                        pieceFocused.positionY - 1,
                        pieceFocused.positionX + 1,
                        1,
                        pieceFocused,
                        context
                    )
                }
            }
            if (!isPiece(board[pieceFocused.positionY - 1][pieceFocused.positionX])) {
                hasPieceMove(
                    pieceFocused.positionY - 1,
                    pieceFocused.positionX,
                    1,
                    pieceFocused,
                    context
                )
            }
        }
    }

    private fun hasPieceMove(
        positionY: Int,
        positionX: Int,
        color: Int,
        pieceFocused: Piece,
        context: Context
    ): Boolean {
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
                hasMoves = true
            }
            resetBoard(piecesList, board, context)
            return true
        } else {
            if (findPiece((positionY), (positionX)).color == color) {
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
                    piecesListCopy.indexOf(findPiece(positionY, positionX)),
                    Piece(0, 0, 0, 0, false)
                )
                val boardCopy = board.clone()
                resetBoard(piecesListCopy, boardCopy, context)
                if (!PiecesHelper().isCheck(piecesListCopy,  colorOpposite, boardCopy)) {
                    hasMoves = true
                }
                resetBoard(piecesList, board, context)
                return false
            }
            return false
        }
    }
}