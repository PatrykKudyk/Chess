package com.partos.chess.logic

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.partos.chess.R
import com.partos.chess.models.Piece

class PiecesHelper {

    private lateinit var movesList: Array<Array<Boolean>>
    private lateinit var board: Array<Array<ImageView>>
    private lateinit var piecesList: ArrayList<Piece>

    fun initPieces(): ArrayList<Piece> {
        val piecesList = ArrayList<Piece>()
        // white pawns
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

    fun isCheckMate(piecesList: ArrayList<Piece>, color: Int, board: Array<Array<ImageView>>): Boolean {
        
    }
}