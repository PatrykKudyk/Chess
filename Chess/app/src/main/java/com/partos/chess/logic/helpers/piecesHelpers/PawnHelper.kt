package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.parameters.BaseParametersGroup
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
}