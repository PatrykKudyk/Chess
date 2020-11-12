package com.partos.chess.logic.logic

import android.view.View
import androidx.fragment.app.FragmentManager
import com.partos.chess.logic.listeners.ComputerChoiceListeners

class ComputerChoiceLogic {

    fun initFragment(rootView: View, fragmentManager: FragmentManager, gameType: Int) {
        ComputerChoiceListeners().initListeners(rootView, fragmentManager, gameType)
    }
}