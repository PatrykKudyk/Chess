package com.partos.chess.models

data class Move(
    val piece: Piece,
    val positionY: Int,
    val positionX: Int
)