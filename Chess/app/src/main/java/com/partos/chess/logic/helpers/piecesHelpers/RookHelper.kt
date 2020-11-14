package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.PieceParameters

class RookHelper {

    fun showRookMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        showTopMoves(baseParametersGroup, moves)
        showRightMoves(baseParametersGroup, moves)
        showBottomMoves(baseParametersGroup, moves)
        showLeftMoves(baseParametersGroup, moves)

        return moves
    }

    private fun showLeftMoves(baseParametersGroup: BaseParametersGroup, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionX - i) >= 0) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY,
                        baseParametersGroup.pieceParameters.piece.positionX - i,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX - i] = true
                    if (isTake(
                            baseParametersGroup,
                            baseParametersGroup.pieceParameters.piece.positionY,
                            baseParametersGroup.pieceParameters.piece.positionX - i
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

    private fun showTopMoves(baseParametersGroup: BaseParametersGroup, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionY - i) >= 0) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY - i,
                        baseParametersGroup.pieceParameters.piece.positionX,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX] = true
                    if (isTake(
                            baseParametersGroup,
                            baseParametersGroup.pieceParameters.piece.positionY - i,
                            baseParametersGroup.pieceParameters.piece.positionX
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

    private fun showRightMoves(baseParametersGroup: BaseParametersGroup, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionX + i) <= 7) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY,
                        baseParametersGroup.pieceParameters.piece.positionX + i,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX + i] = true
                    if (isTake(
                            baseParametersGroup,
                            baseParametersGroup.pieceParameters.piece.positionY,
                            baseParametersGroup.pieceParameters.piece.positionX + i
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

    private fun showBottomMoves(baseParametersGroup: BaseParametersGroup, moves: Array<Array<Boolean>>) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionY + i) <= 7) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY + i,
                        baseParametersGroup.pieceParameters.piece.positionX,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX] = true
                    if (isTake(
                            baseParametersGroup,
                            baseParametersGroup.pieceParameters.piece.positionY + i,
                            baseParametersGroup.pieceParameters.piece.positionX
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

    private fun isTake(baseParametersGroup: BaseParametersGroup, positionY: Int, positionX: Int): Boolean {
        if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[positionY][positionX])) {
            if (PiecesHelper().isOppositeColorPiece(
                    baseParametersGroup.pieceParameters,
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