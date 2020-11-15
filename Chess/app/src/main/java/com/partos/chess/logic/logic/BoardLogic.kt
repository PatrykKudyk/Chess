package com.partos.chess.logic.logic

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.logic.UserInteractionLogic
import com.partos.chess.models.Piece

class BoardLogic {

    private lateinit var board: Array<Array<ImageView>>
    private lateinit var moves: Array<Array<ImageView>>
    private lateinit var piecesList: ArrayList<Piece>

    fun initFragment(rootView: View, fragmentManager: FragmentManager, gameType: Int, computerType: Int) {
        initBoard(rootView)
        initPiecesList()
        UserInteractionLogic().initListeners(rootView, fragmentManager, board, moves, piecesList, gameType, computerType)
    }

    private fun initPiecesList() {
        piecesList = PiecesHelper().initPieces()
    }

    private fun initBoard(rootView: View) {
        board = BoardHelper().createBoardArray(rootView)
        moves = BoardHelper().createMovesArray(rootView)
        BoardHelper().initBoardPieces(board, rootView.context)
    }
}