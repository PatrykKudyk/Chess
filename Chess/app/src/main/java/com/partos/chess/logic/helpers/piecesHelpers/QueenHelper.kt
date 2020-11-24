package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.PieceParameters

class QueenHelper {

    fun showQueenMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        var moves = MovesHelper().mergeMovesLists(
            MovesHelper().createMovesList(),
            BishopHelper().showBishopMoves(baseParametersGroup)
        )
        moves = MovesHelper().mergeMovesLists(
            moves,
            RookHelper().showRookMoves(baseParametersGroup)
        )
        return moves
    }

    fun checkQueenMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        var moves = MovesHelper().mergeMovesLists(
            MovesHelper().createMovesList(),
            BishopHelper().checkBishopMoves(baseParametersGroup)
        )
        moves = MovesHelper().mergeMovesLists(
            moves,
            RookHelper().checkRookMoves(baseParametersGroup)
        )
        return moves
    }

    fun findQueen(piecesList: ArrayList<Piece>, color: Int): Piece {
        for (piece in piecesList) {
            if (piece.type == 4 && piece.color == color) {
                return piece
            }
        }
        return Piece(0, 0, 0, 0, false)
    }
}