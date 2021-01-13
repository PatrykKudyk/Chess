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

        showTopMoves(pieceY, pieceX, gameDescription, moves, 0)
        showRightMoves(pieceY, pieceX, gameDescription, moves, 0)
        showBottomMoves(pieceY, pieceX, gameDescription, moves, 0)
        showLeftMoves(pieceY, pieceX, gameDescription, moves, 0)

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

        showTopMoves(pieceY, pieceX, gameDescription, moves, 1)
        showRightMoves(pieceY, pieceX, gameDescription, moves, 1)
        showBottomMoves(pieceY, pieceX, gameDescription, moves, 1)
        showLeftMoves(pieceY, pieceX, gameDescription, moves, 1)

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

        checkTopMoves(pieceY, pieceX, gameDescription, moves, 0)
        checkRightMoves(pieceY, pieceX, gameDescription, moves, 0)
        checkBottomMoves(pieceY, pieceX, gameDescription, moves, 0)
        checkLeftMoves(pieceY, pieceX, gameDescription, moves, 0)

        return moves
    }

    fun checkBlackRookMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        checkTopMoves(pieceY, pieceX, gameDescription, moves, 1)
        checkRightMoves(pieceY, pieceX, gameDescription, moves, 1)
        checkBottomMoves(pieceY, pieceX, gameDescription, moves, 1)
        checkLeftMoves(pieceY, pieceX, gameDescription, moves, 1)

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

    private fun showLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY,
                        pieceX - i,
                        gameDescription,
                        color
                    )
                ) {
                    if (gameDescription.board[pieceY][pieceX - i] == PieceType.Empty) {
                        moves[pieceY][pieceX - i] = true
                    } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY][pieceX - i])) {
                        moves[pieceY][pieceX - i] = true
                        return
                    } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY][pieceX - i])) {
                        moves[pieceY][pieceX - i] = true
                        return
                    } else {
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
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY,
                        pieceX + i,
                        gameDescription,
                        color
                    )
                ) {
                    if (gameDescription.board[pieceY][pieceX + i] == PieceType.Empty) {
                        moves[pieceY][pieceX + i] = true
                    } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY][pieceX + i])) {
                        moves[pieceY][pieceX + i] = true
                        return
                    } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY][pieceX + i])) {
                        moves[pieceY][pieceX + i] = true
                        return
                    } else {
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
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceY - i >= 0) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - i,
                        pieceX,
                        gameDescription,
                        color
                    )
                ) {
                    if (gameDescription.board[pieceY - i][pieceX] == PieceType.Empty) {
                        moves[pieceY - i][pieceX] = true
                    } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX])) {
                        moves[pieceY - i][pieceX] = true
                        return
                    } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX])) {
                        moves[pieceY - i][pieceX] = true
                        return
                    } else {
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
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceY + i <= 7) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + i,
                        pieceX,
                        gameDescription,
                        color
                    )
                ) {
                    if (gameDescription.board[pieceY + i][pieceX] == PieceType.Empty) {
                        moves[pieceY + i][pieceX] = true
                    } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX])) {
                        moves[pieceY + i][pieceX] = true
                        return
                    } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX])) {
                        moves[pieceY + i][pieceX] = true
                        return
                    } else {
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

    private fun checkLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0) {
                if (gameDescription.board[pieceY][pieceX - i] == PieceType.Empty) {
                    moves[pieceY][pieceX - i] = true
                } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY][pieceX - i])) {
                    moves[pieceY][pieceX - i] = true
                    return
                } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY][pieceX - i])) {
                    moves[pieceY][pieceX - i] = true
                    return
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7) {
                if (gameDescription.board[pieceY][pieceX + i] == PieceType.Empty) {
                    moves[pieceY][pieceX + i] = true
                } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY][pieceX + i])) {
                    moves[pieceY][pieceX + i] = true
                    return
                } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY][pieceX + i])) {
                    moves[pieceY][pieceX + i] = true
                    return
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkTopMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX] == PieceType.Empty) {
                    moves[pieceY - i][pieceX] = true
                } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX])) {
                    moves[pieceY - i][pieceX] = true
                    return
                } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX])) {
                    moves[pieceY - i][pieceX] = true
                    return
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkBottomMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX] == PieceType.Empty) {
                    moves[pieceY + i][pieceX] = true
                } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX])) {
                    moves[pieceY + i][pieceX] = true
                    return
                } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX])) {
                    moves[pieceY + i][pieceX] = true
                    return
                } else {
                    return
                }
            } else {
                return
            }
        }
    }
}