package com.partos.chess.logic.listeners

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.partos.chess.R

class BoardListeners {

    private lateinit var backButton: ImageView

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager)
    }

    private fun attachListeners(fragmentManager: FragmentManager) {
        backButton.setOnClickListener{
            fragmentManager
                .popBackStack()
        }
    }

    private fun attachViews(rootView: View) {
        backButton = rootView.findViewById(R.id.board_image_back)
    }
}