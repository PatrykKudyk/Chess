package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.PieceParameters

class KnightHelper {
    fun showKnightMoves(pieceParams: PieceParameters): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        if (pieceParams.piece.positionX + 1 <= 7 && pieceParams.piece.positionY - 2 >= 0) {
            if (PiecesHelper().canPieceMove(
                    pieceParams.piece.positionY - 2,
                    pieceParams.piece.positionX + 1,
                    pieceParams
                )
            ) {
                moves[pieceParams.piece.positionY - 2][pieceParams.piece.positionX + 1] = true
            }
        }
        if (pieceParams.piece.positionX + 2 <= 7 && pieceParams.piece.positionY - 1 >= 0) {
            if (PiecesHelper().canPieceMove(
                    pieceParams.piece.positionY - 1,
                    pieceParams.piece.positionX + 2,
                    pieceParams
                )
            ) {
                moves[pieceParams.piece.positionY - 1][pieceParams.piece.positionX + 2] = true
            }
        }
        if (pieceParams.piece.positionX + 2 <= 7 && pieceParams.piece.positionY + 1 <= 7) {
            if (PiecesHelper().canPieceMove(
                    pieceParams.piece.positionY + 1,
                    pieceParams.piece.positionX + 2,
                    pieceParams
                )
            ) {
                moves[pieceParams.piece.positionY + 1][pieceParams.piece.positionX + 2] = true
            }
        }
        if (pieceParams.piece.positionX + 1 <= 7 && pieceParams.piece.positionY + 2 <= 7) {
            if (PiecesHelper().canPieceMove(
                    pieceParams.piece.positionY + 2,
                    pieceParams.piece.positionX + 1,
                    pieceParams
                )
            ) {
                moves[pieceParams.piece.positionY + 2][pieceParams.piece.positionX + 1] = true
            }
        }
        if (pieceParams.piece.positionX - 1 >= 0 && pieceParams.piece.positionY + 2 <= 7) {
            if (PiecesHelper().canPieceMove(
                    pieceParams.piece.positionY + 2,
                    pieceParams.piece.positionX - 1,
                    pieceParams
                )
            ) {
                moves[pieceParams.piece.positionY + 2][pieceParams.piece.positionX - 1] = true
            }
        }
        if (pieceParams.piece.positionX - 2 >= 0 && pieceParams.piece.positionY + 1 <= 7) {
            if (PiecesHelper().canPieceMove(
                    pieceParams.piece.positionY + 1,
                    pieceParams.piece.positionX - 2,
                    pieceParams
                )
            ) {
                moves[pieceParams.piece.positionY + 1][pieceParams.piece.positionX - 2] = true
            }
        }
        if (pieceParams.piece.positionX - 2 >= 0 && pieceParams.piece.positionY - 1 >= 0) {
            if (PiecesHelper().canPieceMove(
                    pieceParams.piece.positionY - 1,
                    pieceParams.piece.positionX - 2,
                    pieceParams
                )
            ) {
                moves[pieceParams.piece.positionY - 1][pieceParams.piece.positionX - 2] = true
            }
        }
        if (pieceParams.piece.positionX - 1 >= 0 && pieceParams.piece.positionY - 2 >= 0) {
            if (PiecesHelper().canPieceMove(
                    pieceParams.piece.positionY - 2,
                    pieceParams.piece.positionX - 1,
                    pieceParams
                )
            ) {
                moves[pieceParams.piece.positionY - 2][pieceParams.piece.positionX - 1] = true
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