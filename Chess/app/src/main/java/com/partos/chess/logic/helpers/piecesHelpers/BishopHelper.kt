package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup

class BishopHelper {

    fun showBishopMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        showTopLeftMoves(baseParametersGroup, moves)
        showTopRightMoves(baseParametersGroup, moves)
        showBottomRightMoves(baseParametersGroup, moves)
        showBottomLeftMoves(baseParametersGroup, moves)

        return moves
    }

    fun checkBishopMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        checkTopLeftMoves(baseParametersGroup, moves)
        checkTopRightMoves(baseParametersGroup, moves)
        checkBottomRightMoves(baseParametersGroup, moves)
        checkBottomLeftMoves(baseParametersGroup, moves)

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

    private fun showBottomLeftMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (baseParametersGroup.pieceParameters.piece.positionX - i >= 0 && baseParametersGroup.pieceParameters.piece.positionY + i <= 7) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY + i,
                        baseParametersGroup.pieceParameters.piece.positionX - i,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX - i] =
                        true
                    if (isTake(
                            baseParametersGroup,
                            baseParametersGroup.pieceParameters.piece.positionY + i,
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

    private fun showBottomRightMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (baseParametersGroup.pieceParameters.piece.positionX + i <= 7 && baseParametersGroup.pieceParameters.piece.positionY + i <= 7) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY + i,
                        baseParametersGroup.pieceParameters.piece.positionX + i,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX + i] =
                        true
                    if (isTake(
                            baseParametersGroup,
                            baseParametersGroup.pieceParameters.piece.positionY + i,
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

    private fun showTopRightMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (baseParametersGroup.pieceParameters.piece.positionX + i <= 7 && baseParametersGroup.pieceParameters.piece.positionY - i >= 0) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY - i,
                        baseParametersGroup.pieceParameters.piece.positionX + i,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX + i] =
                        true
                    if (isTake(
                            baseParametersGroup,
                            baseParametersGroup.pieceParameters.piece.positionY - i,
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

    private fun showTopLeftMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (baseParametersGroup.pieceParameters.piece.positionX - i >= 0 && baseParametersGroup.pieceParameters.piece.positionY - i >= 0) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY - i,
                        baseParametersGroup.pieceParameters.piece.positionX - i,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX - i] =
                        true
                    if (isTake(
                            baseParametersGroup,
                            baseParametersGroup.pieceParameters.piece.positionY - i,
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

    private fun checkBottomLeftMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (baseParametersGroup.pieceParameters.piece.positionX - i >= 0 && baseParametersGroup.pieceParameters.piece.positionY + i <= 7) {
                moves[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX - i] =
                    true
                if (isTake(
                        baseParametersGroup,
                        baseParametersGroup.pieceParameters.piece.positionY + i,
                        baseParametersGroup.pieceParameters.piece.positionX - i
                    )
                ) {
                    return

                }
            } else {
                return
            }
        }
    }

    private fun checkBottomRightMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (baseParametersGroup.pieceParameters.piece.positionX + i <= 7 && baseParametersGroup.pieceParameters.piece.positionY + i <= 7) {
                moves[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX + i] =
                    true
                if (isTake(
                        baseParametersGroup,
                        baseParametersGroup.pieceParameters.piece.positionY + i,
                        baseParametersGroup.pieceParameters.piece.positionX + i
                    )
                ) {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkTopRightMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (baseParametersGroup.pieceParameters.piece.positionX + i <= 7 && baseParametersGroup.pieceParameters.piece.positionY - i >= 0) {
                moves[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX + i] =
                    true
                if (isTake(
                        baseParametersGroup,
                        baseParametersGroup.pieceParameters.piece.positionY - i,
                        baseParametersGroup.pieceParameters.piece.positionX + i
                    )
                ) {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkTopLeftMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (baseParametersGroup.pieceParameters.piece.positionX - i >= 0 && baseParametersGroup.pieceParameters.piece.positionY - i >= 0) {
                moves[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX - i] =
                    true
                if (isTake(
                        baseParametersGroup,
                        baseParametersGroup.pieceParameters.piece.positionY - i,
                        baseParametersGroup.pieceParameters.piece.positionX - i
                    )
                ) {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun isTake(
        baseParametersGroup: BaseParametersGroup,
        positionY: Int,
        positionX: Int
    ): Boolean {
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
