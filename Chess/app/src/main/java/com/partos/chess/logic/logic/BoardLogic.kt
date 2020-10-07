package com.partos.chess.logic.logic

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.partos.chess.logic.BoardHelper
import com.partos.chess.logic.listeners.BoardListeners

class BoardLogic {

    private lateinit var board: Array<Array<ImageView>>

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        board = BoardHelper().createBoardArray(rootView)
        initBoard(rootView.context)
        BoardListeners().initListeners(rootView, fragmentManager)
    }

    private fun initBoard(context: Context) {
        BoardHelper().initBoardPieces(board, context)
    }
}