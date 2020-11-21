package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.MovesHelper
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
}