package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.parameters.PieceParameters

class QueenHelper {

    fun showQueenMoves(pieceParams: PieceParameters): Array<Array<Boolean>> {
        var moves = MovesHelper().mergeMovesLists(
            MovesHelper().createMovesList(),
            BishopHelper().showBishopMoves(pieceParams)
        )
        moves = MovesHelper().mergeMovesLists(
            moves,
            RookHelper().showRookMoves(pieceParams)
        )
        return moves
    }
}