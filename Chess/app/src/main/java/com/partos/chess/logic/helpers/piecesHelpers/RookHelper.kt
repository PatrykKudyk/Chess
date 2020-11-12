package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.parameters.PieceParameters

class RookHelper {

    fun showRookMoves(pieceParams: PieceParameters): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        showTopMoves(pieceParams, moves)
        showRightMoves(pieceParams, moves)
        showBottomMoves(pieceParams, moves)
        showLeftMoves(pieceParams, moves)

        return moves
    }

    private fun showLeftMoves(pieceParams: PieceParameters, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if ((pieceParams.piece.positionX - i) >= 0) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY,
                        pieceParams.piece.positionX - i,
                        pieceParams
                    )
                ) {
                    moves[pieceParams.piece.positionY][pieceParams.piece.positionX - i] = true
                    if (isTake(
                            pieceParams,
                            pieceParams.piece.positionY,
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

    private fun showTopMoves(pieceParams: PieceParameters, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if ((pieceParams.piece.positionY - i) >= 0) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY - i,
                        pieceParams.piece.positionX,
                        pieceParams
                    )
                ) {
                    moves[pieceParams.piece.positionY - i][pieceParams.piece.positionX] = true
                    if (isTake(
                            pieceParams,
                            pieceParams.piece.positionY - i,
                            pieceParams.piece.positionX
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

    private fun showRightMoves(pieceParams: PieceParameters, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if ((pieceParams.piece.positionX + i) <= 7) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY,
                        pieceParams.piece.positionX + i,
                        pieceParams
                    )
                ) {
                    moves[pieceParams.piece.positionY][pieceParams.piece.positionX + i] = true
                    if (isTake(
                            pieceParams,
                            pieceParams.piece.positionY,
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

    private fun showBottomMoves(pieceParams: PieceParameters, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if ((pieceParams.piece.positionY + i) <= 7) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY + i,
                        pieceParams.piece.positionX,
                        pieceParams
                    )
                ) {
                    moves[pieceParams.piece.positionY + i][pieceParams.piece.positionX] = true
                    if (isTake(
                            pieceParams,
                            pieceParams.piece.positionY + i,
                            pieceParams.piece.positionX
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