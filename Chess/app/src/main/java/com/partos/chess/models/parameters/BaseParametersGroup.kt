package com.partos.chess.models.parameters

import com.partos.chess.models.GameFlags

data class BaseParametersGroup(
    var pieceParameters: PieceParameters,
    var gameFlags: GameFlags,
    var pawnBeforeMoveParameters: PawnBeforeMoveParameters
)