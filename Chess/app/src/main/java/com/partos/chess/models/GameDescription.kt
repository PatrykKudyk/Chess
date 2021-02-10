package com.partos.chess.models

import com.partos.chess.enums.PieceType

data class GameDescription (
    var gameFlags: GameFlags,
    var board: Array<Array<PieceType>>,
    var pawnSpecialMove: Coordinates
)

