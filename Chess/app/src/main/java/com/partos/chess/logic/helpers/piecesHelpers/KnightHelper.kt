package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.PieceParameters

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
                moves[baseParametersGroup.pieceParameters.piece.positionY - 2][baseParametersGroup.pieceParameters.piece.positionX + 1] = true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX + 2 <= 7 && baseParametersGroup.pieceParameters.piece.positionY - 1 >= 0) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY - 1,
                    baseParametersGroup.pieceParameters.piece.positionX + 2,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX + 2] = true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX + 2 <= 7 && baseParametersGroup.pieceParameters.piece.positionY + 1 <= 7) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY + 1,
                    baseParametersGroup.pieceParameters.piece.positionX + 2,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX + 2] = true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX + 1 <= 7 && baseParametersGroup.pieceParameters.piece.positionY + 2 <= 7) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY + 2,
                    baseParametersGroup.pieceParameters.piece.positionX + 1,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY + 2][baseParametersGroup.pieceParameters.piece.positionX + 1] = true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 1 >= 0 && baseParametersGroup.pieceParameters.piece.positionY + 2 <= 7) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY + 2,
                    baseParametersGroup.pieceParameters.piece.positionX - 1,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY + 2][baseParametersGroup.pieceParameters.piece.positionX - 1] = true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 2 >= 0 && baseParametersGroup.pieceParameters.piece.positionY + 1 <= 7) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY + 1,
                    baseParametersGroup.pieceParameters.piece.positionX - 2,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX - 2] = true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 2 >= 0 && baseParametersGroup.pieceParameters.piece.positionY - 1 >= 0) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY - 1,
                    baseParametersGroup.pieceParameters.piece.positionX - 2,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX - 2] = true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX - 1 >= 0 && baseParametersGroup.pieceParameters.piece.positionY - 2 >= 0) {
            if (PiecesHelper().canPieceMove(
                    baseParametersGroup.pieceParameters.piece.positionY - 2,
                    baseParametersGroup.pieceParameters.piece.positionX - 1,
                    baseParametersGroup
                )
            ) {
                moves[baseParametersGroup.pieceParameters.piece.positionY - 2][baseParametersGroup.pieceParameters.piece.positionX - 1] = true
            }
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