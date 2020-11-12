package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.parameters.PawnBeforeMoveParameters
import com.partos.chess.models.parameters.PieceAfterMoveParameters
import com.partos.chess.models.parameters.PieceParameters

class PawnHelper {

    fun showWhitePawnMoves(
        pieceParams: PieceParameters,
        pawnBeforeMoveParameters: PawnBeforeMoveParameters
    ): PieceAfterMoveParameters {
        val returnParams = PieceAfterMoveParameters(
            MovesHelper().createMovesList(),
            isChoose = false,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false
        )
        if (pieceParams.piece.positionY == 6) {
            checkBasicWhitePawnMoves(pieceParams, returnParams)
            if (!BoardHelper().isPiece(pieceParams.board[pieceParams.piece.positionY - 2][pieceParams.piece.positionX]) && !BoardHelper().isPiece(
                    pieceParams.board[pieceParams.piece.positionY - 1][pieceParams.piece.positionX]
                )
            ) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY - 2,
                        pieceParams.piece.positionX,
                        pieceParams
                    )
                ) {
                    returnParams.moves[pieceParams.piece.positionY - 2][pieceParams.piece.positionX] =
                        true
                }
            }
        } else if (pieceParams.piece.positionY == 3) {
            if (pawnBeforeMoveParameters.pawnSpecialBlack) {
                if (pawnBeforeMoveParameters.pawnSpecialY == pieceParams.piece.positionY && (pawnBeforeMoveParameters.pawnSpecialX == (pieceParams.piece.positionX + 1) || pawnBeforeMoveParameters.pawnSpecialX == (pieceParams.piece.positionX - 1))) {
                    if (PiecesHelper().canPieceMove(
                            pawnBeforeMoveParameters.pawnSpecialY - 1,
                            pawnBeforeMoveParameters.pawnSpecialX,
                            pieceParams
                        )
                    ) {
                        returnParams.moves[pawnBeforeMoveParameters.pawnSpecialY - 1][pawnBeforeMoveParameters.pawnSpecialX] =
                            true
                    }
                }
            }
            checkBasicWhitePawnMoves(pieceParams, returnParams)
        } else if (pieceParams.piece.positionY > 1) {
            checkBasicWhitePawnMoves(pieceParams, returnParams)
        } else if (pieceParams.piece.positionY == 1) {
            checkBasicWhitePawnMoves(pieceParams, returnParams)
            returnParams.isChoose = true
        }
        return returnParams
    }

    fun showBlackPawnMoves(
        pieceParams: PieceParameters,
        pawnBeforeMoveParameters: PawnBeforeMoveParameters
    ): PieceAfterMoveParameters {
        val returnParams = PieceAfterMoveParameters(
            MovesHelper().createMovesList(),
            isChoose = false,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false
        )
        if (pieceParams.piece.positionY == 1) {
            checkBasicBlackPawnMoves(pieceParams, returnParams)
            if (!BoardHelper().isPiece(pieceParams.board[pieceParams.piece.positionY + 2][pieceParams.piece.positionX]) && !BoardHelper().isPiece(
                    pieceParams.board[pieceParams.piece.positionY + 1][pieceParams.piece.positionX]
                )
            ) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY + 2,
                        pieceParams.piece.positionX,
                        pieceParams
                    )
                ) {
                    returnParams.moves[pieceParams.piece.positionY + 2][pieceParams.piece.positionX] =
                        true
                }
            }
        } else if (pieceParams.piece.positionY == 4) {
            if (pawnBeforeMoveParameters.pawnSpecialWhite) {
                if (pawnBeforeMoveParameters.pawnSpecialY == pieceParams.piece.positionY && (pawnBeforeMoveParameters.pawnSpecialX == (pieceParams.piece.positionX + 1) || pawnBeforeMoveParameters.pawnSpecialX == (pieceParams.piece.positionX - 1))) {
                    if (PiecesHelper().canPieceMove(
                            pawnBeforeMoveParameters.pawnSpecialY + 1,
                            pawnBeforeMoveParameters.pawnSpecialX,
                            pieceParams
                        )
                    ) {
                        returnParams.moves[pawnBeforeMoveParameters.pawnSpecialY + 1][pawnBeforeMoveParameters.pawnSpecialX] =
                            true
                    }
                }
            }
            checkBasicBlackPawnMoves(pieceParams, returnParams)
        } else if (pieceParams.piece.positionY < 6) {
            checkBasicBlackPawnMoves(pieceParams, returnParams)
        } else if (pieceParams.piece.positionY == 6) {
            checkBasicBlackPawnMoves(pieceParams, returnParams)
            returnParams.isChoose = true
        }
        return returnParams
    }

    private fun checkBasicWhitePawnMoves(
        pieceParams: PieceParameters,
        returnParams: PieceAfterMoveParameters
    ) {
        if (pieceParams.piece.positionX > 0) {
            if (BoardHelper().isPiece(pieceParams.board[pieceParams.piece.positionY - 1][pieceParams.piece.positionX - 1])) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY - 1,
                        pieceParams.piece.positionX - 1,
                        pieceParams
                    )
                ) {
                    returnParams.moves[pieceParams.piece.positionY - 1][pieceParams.piece.positionX - 1] =
                        true
                }
            }
        }
        if (pieceParams.piece.positionX < 7) {
            if (BoardHelper().isPiece(pieceParams.board[pieceParams.piece.positionY - 1][pieceParams.piece.positionX + 1])) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY - 1,
                        pieceParams.piece.positionX + 1,
                        pieceParams
                    )
                ) {
                    returnParams.moves[pieceParams.piece.positionY - 1][pieceParams.piece.positionX + 1] =
                        true
                }
            }
        }
        if (!BoardHelper().isPiece(pieceParams.board[pieceParams.piece.positionY - 1][pieceParams.piece.positionX])) {
            if (PiecesHelper().canPieceMove(
                    pieceParams.piece.positionY - 1,
                    pieceParams.piece.positionX,
                    pieceParams
                )
            ) {
                returnParams.moves[pieceParams.piece.positionY - 1][pieceParams.piece.positionX] =
                    true
            }
        }
    }

    private fun checkBasicBlackPawnMoves(
        pieceParams: PieceParameters,
        returnParams: PieceAfterMoveParameters
    ) {
        if (pieceParams.piece.positionX > 0) {
            if (BoardHelper().isPiece(pieceParams.board[pieceParams.piece.positionY + 1][pieceParams.piece.positionX - 1])) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY + 1,
                        pieceParams.piece.positionX - 1,
                        pieceParams
                    )
                ) {
                    returnParams.moves[pieceParams.piece.positionY + 1][pieceParams.piece.positionX - 1] =
                        true
                }
            }
        }
        if (pieceParams.piece.positionX < 7) {
            if (BoardHelper().isPiece(pieceParams.board[pieceParams.piece.positionY + 1][pieceParams.piece.positionX + 1])) {
                if (PiecesHelper().canPieceMove(
                        pieceParams.piece.positionY + 1,
                        pieceParams.piece.positionX + 1,
                        pieceParams
                    )
                ) {
                    returnParams.moves[pieceParams.piece.positionY + 1][pieceParams.piece.positionX + 1] =
                        true
                }
            }
        }
        if (!BoardHelper().isPiece(pieceParams.board[pieceParams.piece.positionY + 1][pieceParams.piece.positionX])) {
            if (PiecesHelper().canPieceMove(
                    pieceParams.piece.positionY + 1,
                    pieceParams.piece.positionX,
                    pieceParams
                )
            ) {
                returnParams.moves[pieceParams.piece.positionY + 1][pieceParams.piece.positionX] =
                    true
            }
        }
    }
}