package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.PieceParameters

class BishopHelper {

    fun showBishopMoves(pieceParams: PieceParameters): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        showTopLeftMoves(pieceParams, moves)
        showTopRightMoves(pieceParams, moves)
        showBottomRightMoves(pieceParams, moves)
        showBottomLeftMoves(pieceParams, moves)

        return moves
    }

    fun isAnyBishopActive(color: Int, piecesList: ArrayList<Piece>): Boolean {
        for (piece in piecesList) {
            if (piece.isActive && piece.color == color && piece.type == 1) {
                return true
            }
        }
        return false
    }

    fun colorOfBishopSquare(color: Int, piecesList: ArrayList<Piece>): Int {
        for (piece in piecesList) {
            if (piece.isActive && piece.color == color && piece.type == 1) {
                return BoardHelper().getSquareColor(piece.positionY, piece.positionX)
            }
        }
        return 3
    }

    private fun showBottomLeftMoves(pieceParams: PieceParameters, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if (pieceParams.piece.positionX - i >= 0 && pieceParams.piece.positionY + i <= 7) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY + i,
                        pieceParams.piece.positionX - i,
                        pieceParams
                    )
                ) {
                    moves[pieceParams.piece.positionY + i][pieceParams.piece.positionX - i] = true
                    if (isTake(
                            pieceParams,
                            pieceParams.piece.positionY + i,
                            pieceParams.piece.positionX - i
                        )
                    ) {
                        return
                    }
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun showBottomRightMoves(pieceParams: PieceParameters, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if (pieceParams.piece.positionX + i <= 7 && pieceParams.piece.positionY + i <= 7) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY + i,
                        pieceParams.piece.positionX + i,
                        pieceParams
                    )
                ) {
                    moves[pieceParams.piece.positionY + i][pieceParams.piece.positionX + i] = true
                    if (isTake(
                            pieceParams,
                            pieceParams.piece.positionY + i,
                            pieceParams.piece.positionX + i
                        )
                    ) {
                        return
                    }
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun showTopRightMoves(pieceParams: PieceParameters, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if (pieceParams.piece.positionX + i <= 7 && pieceParams.piece.positionY - i >= 0) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY - i,
                        pieceParams.piece.positionX + i,
                        pieceParams
                    )
                ) {
                    moves[pieceParams.piece.positionY - i][pieceParams.piece.positionX + i] = true
                    if (isTake(
                            pieceParams,
                            pieceParams.piece.positionY - i,
                            pieceParams.piece.positionX + i
                        )
                    ) {
                        return
                    }
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun showTopLeftMoves(pieceParams: PieceParameters, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if (pieceParams.piece.positionX - i >= 0 && pieceParams.piece.positionY - i >= 0) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY - i,
                        pieceParams.piece.positionX - i,
                        pieceParams
                    )
                ) {
                    moves[pieceParams.piece.positionY - i][pieceParams.piece.positionX - i] = true
                    if (isTake(
                            pieceParams,
                            pieceParams.piece.positionY - i,
                            pieceParams.piece.positionX - i
                        )
                    ) {
                        return
                    }
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun isTake(pieceParams: PieceParameters, positionY: Int, positionX: Int): Boolean {
        if (BoardHelper().isPiece(pieceParams.board[positionY][positionX])) {
            if (PiecesHelper().isOppositeColorPiece(
                    pieceParams,
                    positionY,
                    positionX
                )
            ) {
                return true
            }
            return false
        }
        return false
    }
}
