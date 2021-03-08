package com.partos.chess.models

data class BoardMove(
    val gameDescription: GameDescription,
    val pieceCoordinates: Coordinates,
    val moveCoordinates: Coordinates
)