package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.enums.PieceType
import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.GameDescription
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.MovesAndFlags

class RookHelper {

    fun showRookMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        showTopMoves(baseParametersGroup, moves)
        showRightMoves(baseParametersGroup, moves)
        showBottomMoves(baseParametersGroup, moves)
        showLeftMoves(baseParametersGroup, moves)

        return moves
    }

    fun showWhiteRookMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        val moves = MovesHelper().createMovesList()

        showWhiteTopMoves(pieceY, pieceX, gameDescription, moves)
        showWhiteRightMoves(pieceY, pieceX, gameDescription, moves)
        showWhiteBottomMoves(pieceY, pieceX, gameDescription, moves)
        showWhiteLeftMoves(pieceY, pieceX, gameDescription, moves)

        return MovesAndFlags(
            moves,
            gameDescription.gameFlags
        )
    }

    fun showBlackRookMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        val moves = MovesHelper().createMovesList()

        showBlackTopMoves(pieceY, pieceX, gameDescription, moves)
        showBlackRightMoves(pieceY, pieceX, gameDescription, moves)
        showBlackBottomMoves(pieceY, pieceX, gameDescription, moves)
        showBlackLeftMoves(pieceY, pieceX, gameDescription, moves)

        return MovesAndFlags(
            moves,
            gameDescription.gameFlags
        )
    }

    fun checkRookMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        checkTopMoves(baseParametersGroup, moves)
        checkRightMoves(baseParametersGroup, moves)
        checkBottomMoves(baseParametersGroup, moves)
        checkLeftMoves(baseParametersGroup, moves)

        return moves
    }

    fun checkWhiteRookMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        checkWhiteTopMoves(pieceY, pieceX, gameDescription, moves)
        checkWhiteRightMoves(pieceY, pieceX, gameDescription, moves)
        checkWhiteBottomMoves(pieceY, pieceX, gameDescription, moves)
        checkWhiteLeftMoves(pieceY, pieceX, gameDescription, moves)

        return moves
    }

    fun checkBlackRookMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        checkBlackTopMoves(pieceY, pieceX, gameDescription, moves)
        checkBlackRightMoves(pieceY, pieceX, gameDescription, moves)
        checkBlackBottomMoves(pieceY, pieceX, gameDescription, moves)
        checkBlackLeftMoves(pieceY, pieceX, gameDescription, moves)

        return moves
    }

    private fun showLeftMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionX - i) >= 0) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY,
                        baseParametersGroup.pieceParameters.piece.positionX - i,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX - i] =
                        true
                    if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX - i])) {
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

    private fun showTopMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionY - i) >= 0) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY - i,
                        baseParametersGroup.pieceParameters.piece.positionX,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX] =
                        true
                    if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX])) {
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

    private fun showRightMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionX + i) <= 7) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY,
                        baseParametersGroup.pieceParameters.piece.positionX + i,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX + i] =
                        true
                    if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX + i])) {
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

    private fun showBottomMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionY + i) <= 7) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY + i,
                        baseParametersGroup.pieceParameters.piece.positionX,
                        baseParametersGroup
                    )
                ) {
                    moves[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX] =
                        true
                    if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX])) {
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

    private fun showWhiteLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0) {
                if (gameDescription.board[pieceY][pieceX - i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY,
                            pieceX - i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY][pieceX - i] = true
                    }
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY][pieceX - i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY,
                            pieceX - i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY][pieceX - i] = true
                        return
                    } else {
                        return
                    }
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun showWhiteRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7) {
                if (gameDescription.board[pieceY][pieceX + i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY,
                            pieceX + i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY][pieceX + i] = true
                    }
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY][pieceX + i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY,
                            pieceX + i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY][pieceX + i] = true
                        return
                    } else {
                        return
                    }
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun showWhiteTopMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY - i][pieceX] = true
                    }
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY - i][pieceX] = true
                        return
                    } else {
                        return
                    }
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun showWhiteBottomMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY + i][pieceX] = true
                    }
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY + i][pieceX] = true
                        return
                    } else {
                        return
                    }
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun showBlackLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0) {
                if (gameDescription.board[pieceY][pieceX - i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY,
                            pieceX - i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY][pieceX - i] = true
                    }
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY][pieceX - i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY,
                            pieceX - i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY][pieceX - i] = true
                        return
                    } else {
                        return
                    }
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun showBlackRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7) {
                if (gameDescription.board[pieceY][pieceX + i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY,
                            pieceX + i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY][pieceX + i] = true
                    }
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY][pieceX + i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY,
                            pieceX + i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY][pieceX + i] = true
                        return
                    } else {
                        return
                    }
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun showBlackTopMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY - i][pieceX] = true
                    }
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY - i][pieceX] = true
                        return
                    } else {
                        return
                    }
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun showBlackBottomMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY + i][pieceX] = true
                    }
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY + i][pieceX] = true
                        return
                    } else {
                        return
                    }
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun checkLeftMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionX - i) >= 0) {
                moves[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX - i] =
                    true
                if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX - i])) {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkTopMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionY - i) >= 0) {
                moves[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX] =
                    true
                if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX])) {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkRightMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionX + i) <= 7) {
                moves[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX + i] =
                    true
                if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX + i])) {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkBottomMoves(
        baseParametersGroup: BaseParametersGroup,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if ((baseParametersGroup.pieceParameters.piece.positionY + i) <= 7) {
                moves[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX] =
                    true
                if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX])) {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkWhiteLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0) {
                if (gameDescription.board[pieceY][pieceX - i] == PieceType.Empty) {
                    moves[pieceY][pieceX - i] = true
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY][pieceX - i])) {
                    moves[pieceY][pieceX - i] = true
                    return
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun checkWhiteRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7) {
                if (gameDescription.board[pieceY][pieceX + i] == PieceType.Empty) {
                    moves[pieceY][pieceX + i] = true
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY][pieceX + i])) {
                    moves[pieceY][pieceX + i] = true
                    return
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun checkWhiteTopMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX] == PieceType.Empty) {
                    moves[pieceY - i][pieceX] = true
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX])) {
                    moves[pieceY - i][pieceX] = true
                    return
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun checkWhiteBottomMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX] == PieceType.Empty) {
                    moves[pieceY + i][pieceX] = true
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX])) {
                    moves[pieceY + i][pieceX] = true
                    return
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun checkBlackLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0) {
                if (gameDescription.board[pieceY][pieceX - i] == PieceType.Empty) {
                    moves[pieceY][pieceX - i] = true
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY][pieceX - i])) {
                    moves[pieceY][pieceX - i] = true
                    return
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun checkBlackRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7) {
                if (gameDescription.board[pieceY][pieceX + i] == PieceType.Empty) {
                    moves[pieceY][pieceX + i] = true
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY][pieceX + i])) {
                    moves[pieceY][pieceX + i] = true
                    return
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun checkBlackTopMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX] == PieceType.Empty) {
                    moves[pieceY - i][pieceX] = true
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX])) {
                    moves[pieceY - i][pieceX] = true
                    return
                } else
                    return
            } else {
                return
            }
        }
    }

    private fun checkBlackBottomMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX] == PieceType.Empty) {
                    moves[pieceY + i][pieceX] = true
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX])) {
                    moves[pieceY + i][pieceX] = true
                    return
                } else
                    return
            } else {
                return
            }
        }
    }

}