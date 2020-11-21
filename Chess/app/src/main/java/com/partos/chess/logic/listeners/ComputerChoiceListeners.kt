package com.partos.chess.logic.listeners

import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.chess.R
import com.partos.chess.fragments.BoardFragment

class ComputerChoiceListeners {

    lateinit var randomButton: Button

    fun initListeners(rootView: View, fragmentManager: FragmentManager, gameType: Int) {
        attachViews(rootView)
        attachListeners(fragmentManager, gameType)
    }

    private fun attachListeners(fragmentManager: FragmentManager, gameType: Int) {
        randomButton.setOnClickListener {
            val fragment = BoardFragment.newInstance(gameType, 0, 0)

            fragmentManager
                .popBackStack()
            fragmentManager
                .popBackStack()

            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(BoardFragment.toString())
                .commit()
        }
    }

    private fun attachViews(rootView: View) {
        randomButton = rootView.findViewById(R.id.computer_choice_random)
    }
}