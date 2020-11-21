package com.partos.chess.models

data class Piece(
    var type: Int, //0 - pawn, 1 - bishop, 2 - knight, 3 - rook, 4 - queen, 5 - king
    var color: Int, //0 - white, 1 - black
    var positionX: Int,
    var positionY: Int,
    var isActive: Boolean
)