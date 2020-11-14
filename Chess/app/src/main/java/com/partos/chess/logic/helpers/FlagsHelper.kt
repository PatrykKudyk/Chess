package com.partos.chess.logic.helpers

import com.partos.chess.models.GameFlags

class FlagsHelper {

    fun createFlags(): GameFlags {
        return GameFlags(
            canCastleLongBlack = false,
            canCastleLongWhite = false,
            canCastleShortBlack = false,
            canCastleShortWhite = false,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false,
            playerTurn = false,
            didPlayerMoved = false,
            isChoose = false,
            pawnSpecialWhite = false,
            pawnSpecialBlack = false,
            checkBlack = false,
            checkWhite = false
        )
    }
}