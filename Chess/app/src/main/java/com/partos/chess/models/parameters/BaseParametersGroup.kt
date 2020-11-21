package com.partos.chess.models.parameters

import com.partos.chess.models.GameFlags

data class BaseParametersGroup(
    val pieceParameters: PieceParameters,
    val gameFlags: GameFlags,
    val pawnBeforeMoveParameters: PawnBeforeMoveParameters
)