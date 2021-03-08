package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.enums.PieceType
import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.GameDescription
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.MovesAndFlags
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

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
    ): MovesAndFlags{
        val moves = MovesHelper().createMovesList()

        showWhiteTopLeftMoves(pieceY, pieceX, gameDescription, moves)
        showWhiteTopRightMoves(pieceY, pieceX, gameDescription, moves)
        showWhiteBottomRightMoves(pieceY, pieceX, gameDescription, moves)
        showWhiteBottomLeftMoves(pieceY, pieceX, gameDescription, moves)

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

        showBlackTopLeftMoves(pieceY, pieceX, gameDescription, moves)
        showBlackTopRightMoves(pieceY, pieceX, gameDescription, moves)
        showBlackBottomRightMoves(pieceY, pieceX, gameDescription, moves)
        showBlackBottomLeftMoves(pieceY, pieceX, gameDescription, moves)

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

        checkWhiteTopLeftMoves(pieceY, pieceX, gameDescription, moves)
        checkWhiteTopRightMoves(pieceY, pieceX, gameDescription, moves)
        checkWhiteBottomRightMoves(pieceY, pieceX, gameDescription, moves)
        checkWhiteBottomLeftMoves(pieceY, pieceX, gameDescription, moves)

        return moves
    }

    fun checkBlackBishopMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        checkBlackTopLeftMoves(pieceY, pieceX, gameDescription, moves)
        checkBlackTopRightMoves(pieceY, pieceX, gameDescription, moves)
        checkBlackBottomRightMoves(pieceY, pieceX, gameDescription, moves)
        checkBlackBottomLeftMoves(pieceY, pieceX, gameDescription, moves)

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

    private fun showWhiteBottomLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX - i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX - i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY + i][pieceX - i] = true
                    }
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX - i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX - i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY + i][pieceX - i] = true
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
        return
    }

    private fun showWhiteBottomRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX + i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX + i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY + i][pieceX + i] = true
                    }
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX + i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX + i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY + i][pieceX + i] = true
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
        return
    }

    private fun showWhiteTopRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX + i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX + i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY - i][pieceX + i] = true
                    }
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX + i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX + i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY - i][pieceX + i] = true
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
        return
    }

    private fun showWhiteTopLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ){
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX - i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX - i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY - i][pieceX - i] = true
                    }
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX - i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX - i,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY - i][pieceX - i] = true
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
        return
    }

    private fun showBlackBottomLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX - i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX - i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY + i][pieceX - i] = true
                    }
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX - i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX - i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY + i][pieceX - i] = true
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
        return
    }

    private fun showBlackBottomRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX + i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX + i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY + i][pieceX + i] = true
                    }
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX + i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + i,
                            pieceX + i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY + i][pieceX + i] = true
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

    private fun showBlackTopRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX + i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX + i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY - i][pieceX + i] = true
                    }
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX + i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX + i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY - i][pieceX + i] = true
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

    private fun showBlackTopLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX - i] == PieceType.Empty) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX - i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY - i][pieceX - i] = true
                    }
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX - i])) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - i,
                            pieceX - i,
                            gameDescription,
                            1
                        )
                    ) {
                        moves[pieceY - i][pieceX - i] = true
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

    private fun checkWhiteBottomLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX - i] == PieceType.Empty) {
                    moves[pieceY + i][pieceX - i] = true
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX - i])) {
                    moves[pieceY + i][pieceX - i] = true
                    return
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkWhiteBottomRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX + i] == PieceType.Empty) {
                    moves[pieceY + i][pieceX + i] = true
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY + i][pieceX + i])) {
                    moves[pieceY + i][pieceX + i] = true
                    return
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkWhiteTopRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX + i] == PieceType.Empty) {
                    moves[pieceY - i][pieceX + i] = true
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX + i])) {
                    moves[pieceY - i][pieceX + i] = true
                    return
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkWhiteTopLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX - i] == PieceType.Empty) {
                    moves[pieceY - i][pieceX - i] = true
                } else if (PiecesEnumHelper().isBlack(gameDescription.board[pieceY - i][pieceX - i])) {
                    moves[pieceY - i][pieceX - i] = true
                    return
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkBlackBottomLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX - i] == PieceType.Empty) {
                    moves[pieceY + i][pieceX - i] = true
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX - i])) {
                    moves[pieceY + i][pieceX - i] = true
                    return
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkBlackBottomRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY + i <= 7) {
                if (gameDescription.board[pieceY + i][pieceX + i] == PieceType.Empty) {
                    moves[pieceY + i][pieceX + i] = true
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY + i][pieceX + i])) {
                    moves[pieceY + i][pieceX + i] = true
                    return
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkBlackTopRightMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX + i <= 7 && pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX + i] == PieceType.Empty) {
                    moves[pieceY - i][pieceX + i] = true
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX + i])) {
                    moves[pieceY - i][pieceX + i] = true
                    return
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun checkBlackTopLeftMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ) {
        for (i in 1..8) {
            if (pieceX - i >= 0 && pieceY - i >= 0) {
                if (gameDescription.board[pieceY - i][pieceX - i] == PieceType.Empty) {
                    moves[pieceY - i][pieceX - i] = true
                } else if (PiecesEnumHelper().isWhite(gameDescription.board[pieceY - i][pieceX - i])) {
                    moves[pieceY - i][pieceX - i] = true
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
