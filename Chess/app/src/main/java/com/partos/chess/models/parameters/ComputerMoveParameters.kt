package com.partos.chess.models.parameters

import com.partos.chess.models.Move

data class ComputerMoveParameters(
    val pieceAfterMoveParameters: PieceAfterMoveParameters,
    val move: Move
)