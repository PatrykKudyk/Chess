package com.partos.chess.logic.listeners

import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.chess.R
import com.partos.chess.fragments.BoardFragment
import com.partos.chess.fragments.ComputerChoiceFragment

class GameChoiceListeners {

    lateinit var pvspButton: Button
    lateinit var pvscButton: Button
    lateinit var cvscButton: Button


    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager)
    }

    private fun attachListeners(fragmentManager: FragmentManager) {
        pvspButton.setOnClickListener {
            val fragment = BoardFragment.newInstance(0, 0, 0)

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
        pvscButton.setOnClickListener {
            val fragment = ComputerChoiceFragment.newInstance(1)
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(ComputerChoiceFragment.toString())
                .commit()
        }
        cvscButton.setOnClickListener {
            val fragment = BoardFragment.newInstance(2, 0, 1)

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
        pvspButton = rootView.findViewById(R.id.game_choice_pvsp_button)
        pvscButton = rootView.findViewById(R.id.game_choice_pvsc_button)
        cvscButton = rootView.findViewById(R.id.game_choice_cvsc_button)
    }
}