package com.partos.chess.logic.logic

import android.view.View
import androidx.fragment.app.FragmentManager
import com.partos.chess.logic.listeners.GameChoiceListeners

class GameChoiceLogic {

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        GameChoiceListeners().initListeners(rootView, fragmentManager)
    }
}