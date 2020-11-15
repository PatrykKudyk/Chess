package com.partos.chess.models.parameters

import com.partos.chess.models.Move

data class ComputerMoveParameters(
    val baseParametersGroup: BaseParametersGroup,
    val move: Move
)