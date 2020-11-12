package com.partos.chess.models.parameters

import android.content.Context
import android.view.View
import com.partos.chess.models.GameFlags

data class TakenEndGameParameters(
    val pieceParameters: PieceParameters,
    val gameFlags: GameFlags,
    val rootView: View,
    val movesWithNoCaptureBlack: Int,
    val movesWithNoCaptureWhite: Int,
    val turn: Int
)