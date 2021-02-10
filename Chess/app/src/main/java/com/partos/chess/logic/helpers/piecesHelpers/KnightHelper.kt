package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.GameDescription
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.MovesAndFlags

class KnightHelper {
    fun showKnightMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        if (baseParametersGroup.pieceParameters.piece.positionX + 1 <= 7 && baseParametersGroup.pieceParameters.piece.positionY - 2 >= 0) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY - 2,
                    baseParametersGroup.pieceParameters.piece.positionX + 1,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY - 2][baseParametersGroup.pieceParameters.piece.positionX + 1] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX + 2 <= 7 && baseParametersGroup.pieceParameters.piece.positionY - 1 >= 0) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY - 1,
                    baseParametersGroup.pieceParameters.piece.positionX + 2,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX + 2] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX + 2 <= 7 && baseParametersGroup.pieceParameters.piece.positionY + 1 <= 7) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY + 1,
                    baseParametersGroup.pieceParameters.piece.positionX + 2,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX + 2] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX + 1 <= 7 && baseParametersGroup.pieceParameters.piece.positionY + 2 <= 7) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY + 2,
                    baseParametersGroup.pieceParameters.piece.positionX + 1,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY + 2][baseParametersGroup.pieceParameters.piece.positionX + 1] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 1 >= 0 && baseParametersGroup.pieceParameters.piece.positionY + 2 <= 7) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY + 2,
                    baseParametersGroup.pieceParameters.piece.positionX - 1,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY + 2][baseParametersGroup.pieceParameters.piece.positionX - 1] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 2 >= 0 && baseParametersGroup.pieceParameters.piece.positionY + 1 <= 7) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY + 1,
                    baseParametersGroup.pieceParameters.piece.positionX - 2,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX - 2] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 2 >= 0 && baseParametersGroup.pieceParameters.piece.positionY - 1 >= 0) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY - 1,
                    baseParametersGroup.pieceParameters.piece.positionX - 2,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX - 2] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 1 >= 0 && baseParametersGroup.pieceParameters.piece.positionY - 2 >= 0) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY - 2,
                    baseParametersGroup.pieceParameters.piece.positionX - 1,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY - 2][baseParametersGroup.pieceParameters.piece.positionX - 1] =
                    true
            }
        }
        return moves
    }

    fun showWhiteKnightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        val moves = MovesHelper().createMovesList()

        if (pieceX + 1 <= 7 && pieceY - 2 >= 0) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY - 2][pieceX + 1]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - 2,
                        pieceX + 1,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY - 2][pieceX + 1] = true
                }
        }
        if (pieceX + 2 <= 7 && pieceY - 1 >= 0) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY - 1][pieceX + 2]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - 1,
                        pieceX + 2,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY - 1][pieceX + 2] = true
                }
        }
        if (pieceX + 2 <= 7 && pieceY + 1 <= 7) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY + 1][pieceX + 2]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + 1,
                        pieceX + 2,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY + 1][pieceX + 2] = true
                }
        }
        if (pieceX + 1 <= 7 && pieceY + 2 <= 7) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY + 2][pieceX + 1]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + 2,
                        pieceX + 1,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY + 2][pieceX + 1] = true
                }
        }
        if (pieceX - 1 >= 0 && pieceY + 2 <= 7) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY + 2][pieceX - 1]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + 2,
                        pieceX - 1,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY + 2][pieceX - 1] = true
                }
        }
        if (pieceX - 2 >= 0 && pieceY + 1 <= 7) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY + 1][pieceX - 2]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + 1,
                        pieceX - 2,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY + 1][pieceX - 2] = true
                }
        }
        if (pieceX - 2 >= 0 && pieceY - 1 >= 0) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY - 1][pieceX - 2]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - 1,
                        pieceX - 2,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY - 1][pieceX - 2] = true
                }
        }
        if (pieceX - 1 >= 0 && pieceY - 2 >= 0) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY - 2][pieceX - 1]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - 2,
                        pieceX - 1,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY - 2][pieceX - 1] = true
                }
        }
        return MovesAndFlags(
            moves,
            gameDescription.gameFlags
        )
    }

    fun showBlackKnightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        val moves = MovesHelper().createMovesList()

        if (pieceX + 1 <= 7 && pieceY - 2 >= 0) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY - 2][pieceX + 1]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - 2,
                        pieceX + 1,
                        gameDescription,
                        1
                    )
                ) {
                    moves[pieceY - 2][pieceX + 1] = true
                }
        }
        if (pieceX + 2 <= 7 && pieceY - 1 >= 0) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY - 1][pieceX + 2]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - 1,
                        pieceX + 2,
                        gameDescription,
                        1
                    )
                ) {
                    moves[pieceY - 1][pieceX + 2] = true
                }
        }
        if (pieceX + 2 <= 7 && pieceY + 1 <= 7) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY + 1][pieceX + 2]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + 1,
                        pieceX + 2,
                        gameDescription,
                        1
                    )
                ) {
                    moves[pieceY + 1][pieceX + 2] = true
                }
        }
        if (pieceX + 1 <= 7 && pieceY + 2 <= 7) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY + 2][pieceX + 1]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + 2,
                        pieceX + 1,
                        gameDescription,
                        1
                    )
                ) {
                    moves[pieceY + 2][pieceX + 1] = true
                }
        }
        if (pieceX - 1 >= 0 && pieceY + 2 <= 7) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY + 2][pieceX - 1]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + 2,
                        pieceX - 1,
                        gameDescription,
                        1
                    )
                ) {
                    moves[pieceY + 2][pieceX - 1] = true
                }
        }
        if (pieceX - 2 >= 0 && pieceY + 1 <= 7) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY + 1][pieceX - 2]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + 1,
                        pieceX - 2,
                        gameDescription,
                        1
                    )
                ) {
                    moves[pieceY + 1][pieceX - 2] = true
                }
        }
        if (pieceX - 2 >= 0 && pieceY - 1 >= 0) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY - 1][pieceX - 2]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - 1,
                        pieceX - 2,
                        gameDescription,
                        1
                    )
                ) {
                    moves[pieceY - 1][pieceX - 2] = true
                }
        }
        if (pieceX - 1 >= 0 && pieceY - 2 >= 0) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY - 2][pieceX - 1]))
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - 2,
                        pieceX - 1,
                        gameDescription,
                        1
                    )
                ) {
                    moves[pieceY - 2][pieceX - 1] = true
                }
        }
        return MovesAndFlags(
            moves,
            gameDescription.gameFlags
        )
    }

    fun checkKnightMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        if (baseParametersGroup.pieceParameters.piece.positionX + 1 <= 7 && baseParametersGroup.pieceParameters.piece.positionY - 2 >= 0) {
            moves[baseParametersGroup.pieceParameters.piece.positionY - 2][baseParametersGroup.pieceParameters.piece.positionX + 1] =
                true
        }
        if (baseParametersGroup.pieceParameters.piece.positionX + 2 <= 7 && baseParametersGroup.pieceParameters.piece.positionY - 1 >= 0) {
            moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX + 2] =
                true
        }
        if (baseParametersGroup.pieceParameters.piece.positionX + 2 <= 7 && baseParametersGroup.pieceParameters.piece.positionY + 1 <= 7) {
            moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX + 2] =
                true

        }
        if (baseParametersGroup.pieceParameters.piece.positionX + 1 <= 7 && baseParametersGroup.pieceParameters.piece.positionY + 2 <= 7) {
            moves[baseParametersGroup.pieceParameters.piece.positionY + 2][baseParametersGroup.pieceParameters.piece.positionX + 1] =
                true

        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 1 >= 0 && baseParametersGroup.pieceParameters.piece.positionY + 2 <= 7) {
            moves[baseParametersGroup.pieceParameters.piece.positionY + 2][baseParametersGroup.pieceParameters.piece.positionX - 1] =
                true
        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 2 >= 0 && baseParametersGroup.pieceParameters.piece.positionY + 1 <= 7) {
            moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX - 2] =
                true
        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 2 >= 0 && baseParametersGroup.pieceParameters.piece.positionY - 1 >= 0) {
            moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX - 2] =
                true
        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 1 >= 0 && baseParametersGroup.pieceParameters.piece.positionY - 2 >= 0) {
            moves[baseParametersGroup.pieceParameters.piece.positionY - 2][baseParametersGroup.pieceParameters.piece.positionX - 1] =
                true
        }
        return moves
    }

    fun checkWhiteKnightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        if (pieceX + 1 <= 7 && pieceY - 2 >= 0) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY - 2][pieceX + 1]))
                moves[pieceY - 2][pieceX + 1] = true
        }
        if (pieceX + 2 <= 7 && pieceY - 1 >= 0) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY - 1][pieceX + 2]))
                moves[pieceY - 1][pieceX + 2] = true
        }
        if (pieceX + 2 <= 7 && pieceY + 1 <= 7) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY + 1][pieceX + 2]))
                moves[pieceY + 1][pieceX + 2] = true
        }
        if (pieceX + 1 <= 7 && pieceY + 2 <= 7) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY + 2][pieceX + 1]))
                moves[pieceY + 2][pieceX + 1] = true
        }
        if (pieceX - 1 >= 0 && pieceY + 2 <= 7) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY + 2][pieceX - 1]))
                moves[pieceY + 2][pieceX - 1] = true
        }
        if (pieceX - 2 >= 0 && pieceY + 1 <= 7) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY + 1][pieceX - 2]))
                moves[pieceY + 1][pieceX - 2] = true
        }
        if (pieceX - 2 >= 0 && pieceY - 1 >= 0) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY - 1][pieceX - 2]))
                moves[pieceY - 1][pieceX - 2] = true
        }
        if (pieceX - 1 >= 0 && pieceY - 2 >= 0) {
            if (PiecesEnumHelper().isBlackOrEmpty(gameDescription.board[pieceY - 2][pieceX - 1]))
                moves[pieceY - 2][pieceX - 1] = true
        }
        return moves
    }

    fun checkBlackKnightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        if (pieceX + 1 <= 7 && pieceY - 2 >= 0) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY - 2][pieceX + 1]))
                moves[pieceY - 2][pieceX + 1] = true
        }
        if (pieceX + 2 <= 7 && pieceY - 1 >= 0) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY - 1][pieceX + 2]))
                moves[pieceY - 1][pieceX + 2] = true
        }
        if (pieceX + 2 <= 7 && pieceY + 1 <= 7) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY + 1][pieceX + 2]))
                moves[pieceY + 1][pieceX + 2] = true
        }
        if (pieceX + 1 <= 7 && pieceY + 2 <= 7) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY + 2][pieceX + 1]))
                moves[pieceY + 2][pieceX + 1] = true
        }
        if (pieceX - 1 >= 0 && pieceY + 2 <= 7) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY + 2][pieceX - 1]))
                moves[pieceY + 2][pieceX - 1] = true
        }
        if (pieceX - 2 >= 0 && pieceY + 1 <= 7) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY + 1][pieceX - 2]))
                moves[pieceY + 1][pieceX - 2] = true
        }
        if (pieceX - 2 >= 0 && pieceY - 1 >= 0) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY - 1][pieceX - 2]))
                moves[pieceY - 1][pieceX - 2] = true
        }
        if (pieceX - 1 >= 0 && pieceY - 2 >= 0) {
            if (PiecesEnumHelper().isWhiteOrEmpty(gameDescription.board[pieceY - 2][pieceX - 1]))
                moves[pieceY - 2][pieceX - 1] = true
        }
        return moves
    }

    fun isAnyKnightActive(color: Int, piecesList: ArrayList<Piece>): Boolean {
        for (piece in piecesList) {
            if (piece.isActive && piece.color == color && piece.type == 2) {
                return true
            }
        }
        return false
    }

}