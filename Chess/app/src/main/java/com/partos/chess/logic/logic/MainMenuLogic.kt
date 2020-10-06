package com.partos.chess.logic.logic

import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.chess.logic.listeners.MainMenuListeners

class MainMenuLogic {

    private lateinit var gameButton: Button

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        MainMenuListeners().initListeners(rootView, fragmentManager)
    }

    private fun attachViews(rootView: View) {

    }
}