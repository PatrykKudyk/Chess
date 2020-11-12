package com.partos.chess.models

data class GameFlags(
    var canCastleLongBlack: Boolean,
    var canCastleLongWhite: Boolean,
    var canCastleShortBlack: Boolean,
    var canCastleShortWhite: Boolean,
    var longWhiteCastleAvailable: Boolean,
    var longBlackCastleAvailable: Boolean,
    var shortWhiteCastleAvailable: Boolean,
    var shortBlackCastleAvailable: Boolean,
    var playerTurn: Boolean,
    var didPlayerMoved: Boolean,
    var isChoose: Boolean,
    var pawnSpecialWhite: Boolean,
    var pawnSpecialBlack: Boolean,
    var checkBlack: Boolean,
    var checkWhite: Boolean,
)