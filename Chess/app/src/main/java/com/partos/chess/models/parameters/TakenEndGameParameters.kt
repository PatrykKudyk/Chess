package com.partos.chess.models.parameters

import android.content.Context
import android.view.View
import com.partos.chess.models.GameFlags

data class TakenEndGameParameters(
    val baseParametersGroup: BaseParametersGroup,
    val rootView: View,
    val movesWithNoCaptureBlack: Int,
    val movesWithNoCaptureWhite: Int,
    val turn: Int,
)