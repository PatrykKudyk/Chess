package com.partos.chess.models

import com.partos.chess.models.parameters.PieceAfterMoveParameters

data class AIMove(
    val piece: Piece,
    val positionY: Int,
    val positionX: Int,
    val gain: Int
)