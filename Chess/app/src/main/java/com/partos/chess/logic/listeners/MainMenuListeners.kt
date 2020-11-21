package com.partos.chess.logic.listeners

import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.chess.R
import com.partos.chess.fragments.BoardFragment
import com.partos.chess.fragments.GameChoiceFragment

class MainMenuListeners {

    private lateinit var playButton: Button

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager)
    }

    private fun attachListeners(fragmentManager: FragmentManager) {
        playButton.setOnClickListener {
            val fragment = GameChoiceFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(GameChoiceFragment.toString())
                .commit()
        }
    }

    private fun attachViews(rootView: View) {
        playButton = rootView.findViewById(R.id.main_menu_button_play)
    }
}