package com.partos.chess.models.parameters

data class PieceAfterMoveParameters (
    var moves: Array<Array<Boolean>>,
    var isChoose: Boolean,
    var longWhiteCastleAvailable: Boolean,
    var longBlackCastleAvailable: Boolean,
    var shortWhiteCastleAvailable: Boolean,
    var shortBlackCastleAvailable: Boolean
)