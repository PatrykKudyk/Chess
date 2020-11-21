package com.partos.chess.logic.logic

import android.view.View
import android.widget.TextView
import com.partos.chess.R

class GameLogic {

    private lateinit var checkWhiteTextView: TextView
    private lateinit var checkBlackTextView: TextView
    private lateinit var winTextView: TextView

    fun showEndGameMessage(message: String, rootView: View) {
        attachViews(rootView)
        checkWhiteTextView.visibility = View.GONE
        checkBlackTextView.visibility = View.GONE
        winTextView.text = message
        winTextView.visibility = View.VISIBLE
    }

    fun showWhiteCheck(rootView: View) {
        attachViews(rootView)
        checkWhiteTextView.visibility = View.VISIBLE
    }

    fun hideWhiteCheck(rootView: View) {
        attachViews(rootView)
        checkWhiteTextView.visibility = View.GONE
    }

    fun showBlackCheck(rootView: View) {
        attachViews(rootView)
        checkBlackTextView.visibility = View.VISIBLE
    }

    fun hideBlackCheck(rootView: View) {
        attachViews(rootView)
        checkBlackTextView.visibility = View.GONE
    }

    private fun attachViews(rootView: View) {
        checkBlackTextView = rootView.findViewById(R.id.blackCheckTextView)
        checkWhiteTextView = rootView.findViewById(R.id.whiteCheckTextView)
        winTextView = rootView.findViewById(R.id.winTextView)
    }
}