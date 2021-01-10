package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.enums.PieceType
import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.GameDescription
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.MovesAndFlags
import com.partos.chess.models.parameters.PieceAfterMoveParameters

class PawnHelper {

    fun showWhitePawnMoves(baseParametersGroup: BaseParametersGroup): PieceAfterMoveParameters {
        val returnParams = PieceAfterMoveParameters(
            MovesHelper().createMovesList(),
            isChoose = false,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false
        )
        if (baseParametersGroup.pieceParameters.piece.positionY == 6) {
            showBasicWhitePawnMoves(baseParametersGroup, returnParams)
            if (!BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - 2][baseParametersGroup.pieceParameters.piece.positionX]) && !BoardHelper().isPiece(
                    baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX]
                )
            ) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY - 2,
                        baseParametersGroup.pieceParameters.piece.positionX,
                        baseParametersGroup
                    )
                ) {
                    returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY - 2][baseParametersGroup.pieceParameters.piece.positionX] =
                        true
                }
            }
        } else if (baseParametersGroup.pieceParameters.piece.positionY == 3) {
            if (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialBlack) {
                if (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialY == baseParametersGroup.pieceParameters.piece.positionY && (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX == (baseParametersGroup.pieceParameters.piece.positionX + 1) || baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX == (baseParametersGroup.pieceParameters.piece.positionX - 1))) {
                    if (PiecesHelper().canPieceMove(
                            baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialY - 1,
                            baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX,
                            baseParametersGroup
                        )
                    ) {
                        returnParams.moves[baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialY - 1][baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX] =
                            true
                    }
                }
            }
            showBasicWhitePawnMoves(baseParametersGroup, returnParams)
        } else if (baseParametersGroup.pieceParameters.piece.positionY > 1) {
            showBasicWhitePawnMoves(baseParametersGroup, returnParams)
        } else if (baseParametersGroup.pieceParameters.piece.positionY == 1) {
            showBasicWhitePawnMoves(baseParametersGroup, returnParams)
            returnParams.isChoose = true
        }
        return returnParams
    }

    fun showBlackPawnMoves(baseParametersGroup: BaseParametersGroup): PieceAfterMoveParameters {
        val returnParams = PieceAfterMoveParameters(
            MovesHelper().createMovesList(),
            isChoose = false,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false
        )
        if (baseParametersGroup.pieceParameters.piece.positionY == 1) {
            showBasicBlackPawnMoves(baseParametersGroup, returnParams)
            if (!BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + 2][baseParametersGroup.pieceParameters.piece.positionX]) && !BoardHelper().isPiece(
                    baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX]
                )
            ) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY + 2,
                        baseParametersGroup.pieceParameters.piece.positionX,
                        baseParametersGroup
                    )
                ) {
                    returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY + 2][baseParametersGroup.pieceParameters.piece.positionX] =
                        true
                }
            }
        } else if (baseParametersGroup.pieceParameters.piece.positionY == 4) {
            if (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialWhite) {
                if (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialY == baseParametersGroup.pieceParameters.piece.positionY && (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX == (baseParametersGroup.pieceParameters.piece.positionX + 1) || baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX == (baseParametersGroup.pieceParameters.piece.positionX - 1))) {
                    if (PiecesHelper().canPieceMove(
                            baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialY + 1,
                            baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX,
                            baseParametersGroup
                        )
                    ) {
                        returnParams.moves[baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialY + 1][baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX] =
                            true
                    }
                }
            }
            showBasicBlackPawnMoves(baseParametersGroup, returnParams)
        } else if (baseParametersGroup.pieceParameters.piece.positionY < 6) {
            showBasicBlackPawnMoves(baseParametersGroup, returnParams)
        } else if (baseParametersGroup.pieceParameters.piece.positionY == 6) {
            showBasicBlackPawnMoves(baseParametersGroup, returnParams)
            returnParams.isChoose = true
        }
        return returnParams
    }

    private fun showBasicWhitePawnMoves(
        baseParametersGroup: BaseParametersGroup,
        returnParams: PieceAfterMoveParameters
    ) {
        if (baseParametersGroup.pieceParameters.piece.positionX > 0) {
            if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX - 1])) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY - 1,
                        baseParametersGroup.pieceParameters.piece.positionX - 1,
                        baseParametersGroup
                    )
                ) {
                    returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX - 1] =
                        true
                }
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX < 7) {
            if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX + 1])) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY - 1,
                        baseParametersGroup.pieceParameters.piece.positionX + 1,
                        baseParametersGroup
                    )
                ) {
                    returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX + 1] =
                        true
                }
            }
        }
        if (!BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX])) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY - 1,
                    baseParametersGroup.pieceParameters.piece.positionX,
                    baseParametersGroup
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX] =
                    true
            }
        }
    }

    private fun showBasicBlackPawnMoves(
        baseParametersGroup: BaseParametersGroup,
        returnParams: PieceAfterMoveParameters
    ) {
        if (baseParametersGroup.pieceParameters.piece.positionX > 0) {
            if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX - 1])) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY + 1,
                        baseParametersGroup.pieceParameters.piece.positionX - 1,
                        baseParametersGroup
                    )
                ) {
                    returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX - 1] =
                        true
                }
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX < 7) {
            if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX + 1])) {
                if (PiecesHelper().canPieceMove(
                        baseParametersGroup.pieceParameters.piece.positionY + 1,
                        baseParametersGroup.pieceParameters.piece.positionX + 1,
                        baseParametersGroup
                    )
                ) {
                    returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX + 1] =
                        true
                }
            }
        }
        if (!BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX])) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY + 1,
                    baseParametersGroup.pieceParameters.piece.positionX,
                    baseParametersGroup
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX] =
                    true
            }
        }
    }

    fun checkWhitePawnMoves(baseParametersGroup: BaseParametersGroup): PieceAfterMoveParameters {
        val returnParams = PieceAfterMoveParameters(
            MovesHelper().createMovesList(),
            isChoose = false,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false
        )
        if (baseParametersGroup.pieceParameters.piece.positionY == 6) {
            checkBasicWhitePawnMoves(baseParametersGroup, returnParams)
            if (!BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - 2][baseParametersGroup.pieceParameters.piece.positionX]) && !BoardHelper().isPiece(
                    baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX]
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY - 2][baseParametersGroup.pieceParameters.piece.positionX] =
                    true
            }
        } else if (baseParametersGroup.pieceParameters.piece.positionY == 3) {
            if (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialBlack) {
                if (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialY == baseParametersGroup.pieceParameters.piece.positionY && (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX == (baseParametersGroup.pieceParameters.piece.positionX + 1) || baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX == (baseParametersGroup.pieceParameters.piece.positionX - 1))) {
                    returnParams.moves[baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialY - 1][baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX] =
                        true
                }
            }
            checkBasicWhitePawnMoves(baseParametersGroup, returnParams)
        } else if (baseParametersGroup.pieceParameters.piece.positionY > 1) {
            checkBasicWhitePawnMoves(baseParametersGroup, returnParams)
        } else if (baseParametersGroup.pieceParameters.piece.positionY == 1) {
            checkBasicWhitePawnMoves(baseParametersGroup, returnParams)
            returnParams.isChoose = true
        }
        return returnParams
    }

    fun checkBlackPawnMoves(baseParametersGroup: BaseParametersGroup): PieceAfterMoveParameters {
        val returnParams = PieceAfterMoveParameters(
            MovesHelper().createMovesList(),
            isChoose = false,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false
        )
        if (baseParametersGroup.pieceParameters.piece.positionY == 1) {
            checkBasicBlackPawnMoves(baseParametersGroup, returnParams)
            if (!BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + 2][baseParametersGroup.pieceParameters.piece.positionX]) && !BoardHelper().isPiece(
                    baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX]
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY + 2][baseParametersGroup.pieceParameters.piece.positionX] =
                    true
            }
        } else if (baseParametersGroup.pieceParameters.piece.positionY == 4) {
            if (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialWhite) {
                if (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialY == baseParametersGroup.pieceParameters.piece.positionY && (baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX == (baseParametersGroup.pieceParameters.piece.positionX + 1) || baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX == (baseParametersGroup.pieceParameters.piece.positionX - 1))) {
                    returnParams.moves[baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialY + 1][baseParametersGroup.pawnBeforeMoveParameters.pawnSpecialX] =
                        true
                }
            }
            checkBasicBlackPawnMoves(baseParametersGroup, returnParams)
        } else if (baseParametersGroup.pieceParameters.piece.positionY < 6) {
            checkBasicBlackPawnMoves(baseParametersGroup, returnParams)
        } else if (baseParametersGroup.pieceParameters.piece.positionY == 6) {
            checkBasicBlackPawnMoves(baseParametersGroup, returnParams)
            returnParams.isChoose = true
        }
        return returnParams
    }

    private fun checkBasicWhitePawnMoves(
        baseParametersGroup: BaseParametersGroup,
        returnParams: PieceAfterMoveParameters
    ) {
        if (baseParametersGroup.pieceParameters.piece.positionX > 0) {
            if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX - 1])) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX - 1] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX < 7) {
            if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX + 1])) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX + 1] =
                    true
            }
        }
        if (!BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX])) {
            returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX] =
                true
        }
    }

    private fun checkBasicBlackPawnMoves(
        baseParametersGroup: BaseParametersGroup,
        returnParams: PieceAfterMoveParameters
    ) {
        if (baseParametersGroup.pieceParameters.piece.positionX > 0) {
            if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX - 1])) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX - 1] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX < 7) {
            if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX + 1])) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX + 1] =
                    true
            }
        }
        if (!BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX])) {
            returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX] =
                true
        }
    }

    fun getWhitePawnMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        var flagsToReturn = gameDescription.gameFlags.copy()
        var moves = MovesHelper().createMovesList()
        if (pieceY == 6) {
            moves = getBasicWhitePawnMoves(pieceY, pieceX, gameDescription, moves)
            if (gameDescription.board[pieceY - 2][pieceX] == PieceType.Empty &&
                gameDescription.board[pieceY - 1][pieceX] == PieceType.Empty
            ) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - 2,
                        pieceX,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY - 2][pieceX] = true
                }
            }
        } else if (pieceY == 3) {
            if (gameDescription.gameFlags.pawnSpecialBlack) {
                if (pieceY == gameDescription.pawnSpecialMove.y &&
                    (gameDescription.pawnSpecialMove.x == (pieceX + 1) ||
                            gameDescription.pawnSpecialMove.x == (pieceX - 1))
                ) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY - 1,
                            gameDescription.pawnSpecialMove.x,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY - 1][gameDescription.pawnSpecialMove.x] = true
                    }
                }
            }
            moves = getBasicWhitePawnMoves(pieceY, pieceX, gameDescription, moves)
        } else if (pieceY > 1) {
            moves = getBasicWhitePawnMoves(pieceY, pieceX, gameDescription, moves)
        } else if (pieceY == 1) {
            moves = getBasicWhitePawnMoves(pieceY, pieceX, gameDescription, moves)
            flagsToReturn.isChoose = true
        }
        return MovesAndFlags(
            moves,
            flagsToReturn
        )
    }

    fun checkWhitePawnMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        var moves = MovesHelper().createMovesList()
        if (pieceY == 6) {
            moves = checkBasicWhitePawnMoves(pieceY, pieceX, gameDescription, moves)
            if (gameDescription.board[pieceY - 2][pieceX] == PieceType.Empty &&
                gameDescription.board[pieceY - 1][pieceX] == PieceType.Empty
            ) {
                moves[pieceY - 2][pieceX] = true
            }
        } else if (pieceY == 3) {
            if (gameDescription.gameFlags.pawnSpecialBlack) {
                if (pieceY == gameDescription.pawnSpecialMove.y &&
                    (gameDescription.pawnSpecialMove.x == (pieceX + 1) ||
                            gameDescription.pawnSpecialMove.x == (pieceX - 1))
                ) {
                    moves[pieceY - 1][gameDescription.pawnSpecialMove.x] = true
                }
            }
            moves = checkBasicWhitePawnMoves(pieceY, pieceX, gameDescription, moves)
        } else if (pieceY > 1) {
            moves = checkBasicWhitePawnMoves(pieceY, pieceX, gameDescription, moves)
        } else if (pieceY == 1) {
            moves = checkBasicWhitePawnMoves(pieceY, pieceX, gameDescription, moves)
        }
        return moves
    }

    fun getBasicWhitePawnMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ): Array<Array<Boolean>> {
        if (pieceX > 0) {
            if (canWhitePawnTake(gameDescription.board[pieceY - 1][pieceX - 1])) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - 1,
                        pieceX - 1,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY - 1][pieceX - 1] = true
                }
            }
        }

        if (pieceX < 7) {
            if (canWhitePawnTake(gameDescription.board[pieceY - 1][pieceX + 1])) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY - 1,
                        pieceX + 1,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY - 1][pieceX + 1] = true
                }
            }
        }

        if (gameDescription.board[pieceY - 1][pieceX] == PieceType.Empty) {
            if (PiecesBoardHelper().canPieceMove(
                    pieceY,
                    pieceX,
                    pieceY - 1,
                    pieceX,
                    gameDescription,
                    0
                )
            ) {
                moves[pieceY - 1][pieceX] = true
            }
        }
        return moves
    }

    fun checkBasicWhitePawnMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ): Array<Array<Boolean>> {
        if (pieceX > 0) {
            if (canWhitePawnTake(gameDescription.board[pieceY - 1][pieceX - 1])) {
                moves[pieceY - 1][pieceX - 1] = true
            }
        }

        if (pieceX < 7) {
            if (canWhitePawnTake(gameDescription.board[pieceY - 1][pieceX + 1])) {
                moves[pieceY - 1][pieceX + 1] = true
            }
        }

        if (gameDescription.board[pieceY - 1][pieceX] == PieceType.Empty) {
            moves[pieceY - 1][pieceX] = true
        }
        return moves
    }

    fun getBlackPawnMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        var flagsToReturn = gameDescription.gameFlags.copy()
        var moves = MovesHelper().createMovesList()
        if (pieceY == 1) {
            moves = getBasicBlackPawnMoves(pieceY, pieceX, gameDescription, moves)
            if (gameDescription.board[pieceY + 2][pieceX] == PieceType.Empty &&
                gameDescription.board[pieceY + 1][pieceX] == PieceType.Empty
            ) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + 2,
                        pieceX,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY + 2][pieceX] = true
                }
            }
        } else if (pieceY == 4) {
            if (gameDescription.gameFlags.pawnSpecialBlack) {
                if (pieceY == gameDescription.pawnSpecialMove.y &&
                    (gameDescription.pawnSpecialMove.x == (pieceX + 1) ||
                            gameDescription.pawnSpecialMove.x == (pieceX - 1))
                ) {
                    if (PiecesBoardHelper().canPieceMove(
                            pieceY,
                            pieceX,
                            pieceY + 1,
                            gameDescription.pawnSpecialMove.x,
                            gameDescription,
                            0
                        )
                    ) {
                        moves[pieceY + 1][gameDescription.pawnSpecialMove.x] = true
                    }
                }
            }
            moves = getBasicBlackPawnMoves(pieceY, pieceX, gameDescription, moves)
        } else if (pieceY < 7) {
            moves = getBasicBlackPawnMoves(pieceY, pieceX, gameDescription, moves)
        } else if (pieceY == 7) {
            moves = getBasicBlackPawnMoves(pieceY, pieceX, gameDescription, moves)
            flagsToReturn.isChoose = true
        }
        return MovesAndFlags(
            moves,
            flagsToReturn
        )
    }

    fun checkBlackPawnMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        var moves = MovesHelper().createMovesList()
        if (pieceY == 1) {
            moves = checkBasicBlackPawnMoves(pieceY, pieceX, gameDescription, moves)
            if (gameDescription.board[pieceY + 2][pieceX] == PieceType.Empty &&
                gameDescription.board[pieceY + 1][pieceX] == PieceType.Empty
            ) {
                moves[pieceY + 2][pieceX] = true
            }
        } else if (pieceY == 4) {
            if (gameDescription.gameFlags.pawnSpecialBlack) {
                if (pieceY == gameDescription.pawnSpecialMove.y &&
                    (gameDescription.pawnSpecialMove.x == (pieceX + 1) ||
                            gameDescription.pawnSpecialMove.x == (pieceX - 1))
                ) {
                    moves[pieceY + 1][gameDescription.pawnSpecialMove.x] = true
                }
            }
            moves = checkBasicBlackPawnMoves(pieceY, pieceX, gameDescription, moves)
        } else if (pieceY < 7) {
            moves = checkBasicBlackPawnMoves(pieceY, pieceX, gameDescription, moves)
        } else if (pieceY == 7) {
            moves = checkBasicBlackPawnMoves(pieceY, pieceX, gameDescription, moves)
        }
        return moves
    }

    fun getBasicBlackPawnMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ): Array<Array<Boolean>> {
        if (pieceX > 0) {
            if (canBlackPawnTake(gameDescription.board[pieceY + 1][pieceX - 1])) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + 1,
                        pieceX - 1,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY + 1][pieceX - 1] = true
                }
            }
        }

        if (pieceX < 7) {
            if (canBlackPawnTake(gameDescription.board[pieceY + 1][pieceX + 1])) {
                if (PiecesBoardHelper().canPieceMove(
                        pieceY,
                        pieceX,
                        pieceY + 1,
                        pieceX + 1,
                        gameDescription,
                        0
                    )
                ) {
                    moves[pieceY + 1][pieceX + 1] = true
                }
            }
        }

        if (gameDescription.board[pieceY + 1][pieceX] == PieceType.Empty) {
            if (PiecesBoardHelper().canPieceMove(
                    pieceY,
                    pieceX,
                    pieceY + 1,
                    pieceX,
                    gameDescription,
                    0
                )
            ) {
                moves[pieceY + 1][pieceX] = true
            }
        }
        return moves
    }

    fun checkBasicBlackPawnMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription,
        moves: Array<Array<Boolean>>
    ): Array<Array<Boolean>> {
        if (pieceX > 0) {
            if (canBlackPawnTake(gameDescription.board[pieceY + 1][pieceX - 1])) {
                moves[pieceY + 1][pieceX - 1] = true
            }
        }

        if (pieceX < 7) {
            if (canBlackPawnTake(gameDescription.board[pieceY + 1][pieceX + 1])) {
                moves[pieceY + 1][pieceX + 1] = true
            }
        }

        if (gameDescription.board[pieceY + 1][pieceX] == PieceType.Empty) {
            moves[pieceY + 1][pieceX] = true
        }
        return moves
    }

    private fun canWhitePawnTake(pieceType: PieceType): Boolean {
        when (pieceType) {
            PieceType.BlackPawn, PieceType.BlackBishop, PieceType.BlackKnight, PieceType.BlackRook,
            PieceType.BlackQueen, PieceType.BlackKing -> return true
            else -> return false
        }
    }

    private fun canBlackPawnTake(pieceType: PieceType): Boolean {
        when (pieceType) {
            PieceType.WhitePawn, PieceType.WhiteBishop, PieceType.WhiteKnight, PieceType.WhiteRook,
            PieceType.WhiteQueen, PieceType.WhiteKing -> return true
            else -> return false
        }
    }
}