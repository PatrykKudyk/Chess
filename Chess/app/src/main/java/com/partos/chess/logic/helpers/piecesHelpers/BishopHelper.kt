package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.enums.PieceType
import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.GameDescription
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.MovesAndFlags

class BishopHelper {

    fun showBishopMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        showTopLeftMoves(baseParametersGroup, moves)
        showTopRightMoves(baseParametersGroup, moves)
        showBottomRightMoves(baseParametersGroup, moves)
        showBottomLeftMoves(baseParametersGroup, moves)

        return moves
    }

    fun showWhiteBishopMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        val moves = MovesHelper().createMovesList()

        showTopLeftMoves(pieceY, pieceX, gameDescription, moves, 0)
        showTopRightMoves(pieceY, pieceX, gameDescription, moves, 0)
        showBottomRightMoves(pieceY, pieceX, gameDescription, moves, 0)
        showBottomLeftMoves(pieceY, pieceX, gameDescription, moves, 0)

        return MovesAndFlags(
            moves,
            gameDescription.gameFlags
        )
    }

    fun showBlackBishopMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        val moves = MovesHelper().createMovesList()

        showTopLeftMoves(pieceY, pieceX, gameDescription, moves, 1)
        showTopRightMoves(pieceY, pieceX, gameDescription, moves, 1)
        showBottomRightMoves(pieceY, pieceX, gameDescription, moves, 1)
        showBottomLeftMoves(pieceY, pieceX, gameDescription, moves, 1)

        return MovesAndFlags(
            moves,
            gameDescription.gameFlags
        )
    }

    fun checkWhiteBishopMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        checkTopLeftMoves(pieceY, pieceX, gameDescription, moves, 0)
        checkTopRightMoves(pieceY, pieceX, gameDescription, moves, 0)
        checkBottomRightMoves(pieceY, pieceX, gameDescription, moves, 0)
        checkBottomLeftMoves(pieceY, pieceX, gameDescription, moves, 0)

        return moves
    }

    fun checkBlackBishopMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        checkTopLeftMoves(pieceY, pieceX, gameDescription, moves, 1)
        checkTopRightMoves(pieceY, pieceX, gameDescription, moves, 1)
        checkBottomRightMoves(pieceY, pieceX, gameDescription, moves, 1)
        checkBottomLeftMoves(pieceY, pieceX, gameDescription, moves, 1)

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
                    if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX - i])) {
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
                    if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX + i])) {
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
                    if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX + i])) {
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
                    if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX - i])) {
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

    private fun showBottomLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY + i <= 7) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + i,
                        pieceX - i,
                        gameDescription,
                        color
                    )
                ) {
                    if (gameDescription.board[pieceY + i][pieceX - i] == PieceType.Empty) {
                        moves[pieceY + i][pieceX - i] = true
                    } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX - i])) {
                        moves[pieceY + i][pieceX - i] = true
                        return
                    } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX - i])) {
                        moves[pieceY + i][pieceX - i] = true
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
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY + i <= 7) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + i,
                        pieceX + i,
                        gameDescription,
                        color
                    )
                ) {
                    if (gameDescription.board[pieceY + i][pieceX + i] == PieceType.Empty) {
                        moves[pieceY + i][pieceX + i] = true
                    } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX + i])) {
                        moves[pieceY + i][pieceX + i] = true
                        return
                    } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX + i])) {
                        moves[pieceY + i][pieceX + i] = true
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
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY - i >= 0) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - i,
                        pieceX + i,
                        gameDescription,
                        color
                    )
                ) {
                    if (gameDescription.board[pieceY - i][pieceX + i] == PieceType.Empty) {
                        moves[pieceY - i][pieceX + i] = true
                    } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX + i])) {
                        moves[pieceY - i][pieceX + i] = true
                        return
                    } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX + i])) {
                        moves[pieceY - i][pieceX + i] = true
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
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY - i >= 0) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - i,
                        pieceX - i,
                        gameDescription,
                        color
                    )
                ) {
                    if (gameDescription.board[pieceY - i][pieceX - i] == PieceType.Empty) {
                        moves[pieceY - i][pieceX - i] = true
                    } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX - i])) {
                        moves[pieceY - i][pieceX - i] = true
                        return
                    } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX - i])) {
                        moves[pieceY - i][pieceX - i] = true
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
                if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX - i])) {
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
                if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + i][baseParametersGroup.pieceParameters.piece.positionX + i])) {
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
                if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX + i])) {
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
                if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - i][baseParametersGroup.pieceParameters.piece.positionX - i])) {
                    return
                }

            } else {
                return
            }
        }
    }

    private fun checkBottomLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX - i] == PieceType.Empty) {
                    moves[pieceY + i][pieceX - i] = true
                } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX - i])) {
                    moves[pieceY + i][pieceX - i] = true
                    return
                } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX - i])) {
                    moves[pieceY + i][pieceX - i] = true
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkBottomRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY + i <= 7) {

                if (gameDescription.board[pieceY + i][pieceX + i] == PieceType.Empty) {
                    moves[pieceY + i][pieceX + i] = true
                } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX + i])) {
                    moves[pieceY + i][pieceX + i] = true
                    return
                } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX + i])) {
                    moves[pieceY + i][pieceX + i] = true
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkTopRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY - i >= 0) {

                if (gameDescription.board[pieceY - i][pieceX + i] == PieceType.Empty) {
                    moves[pieceY - i][pieceX + i] = true
                } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX + i])) {
                    moves[pieceY - i][pieceX + i] = true
                    return
                } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX + i])) {
                    moves[pieceY - i][pieceX + i] = true
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkTopLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>,
        color: Int
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX - i] == PieceType.Empty) {
                    moves[pieceY - i][pieceX - i] = true
                } else if (color == 0 && PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX - i])) {
                    moves[pieceY - i][pieceX - i] = true
                    return
                } else if (color == 1 && PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX - i])) {
                    moves[pieceY - i][pieceX - i] = true
                    return
                }
            } else {
                return
            }
        }
    }
}
