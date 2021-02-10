package com.partos.chess.models

import com.partos.chess.logic.UserInteractionLogic
import com.partos.chess.models.parameters.BaseParametersGroup

data class AdvancedMove(
    val movesList: ArrayList<AIMove>,
    val userInteractionLogic: UserInteractionLogic,
    val baseParameters: BaseParametersGroup
)