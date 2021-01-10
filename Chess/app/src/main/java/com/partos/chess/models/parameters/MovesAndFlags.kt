package com.partos.chess.models.parameters

import com.partos.chess.models.GameFlags

data class MovesAndFlags (
    var moves: Array<Array<Boolean>>,
    var gameFlags: GameFlags
    )