package com.partos.chess.logic

import android.content.Context
import android.graphics.drawable.Drawable
import com.partos.chess.R
import com.partos.chess.models.Piece

class PiecesHelper {

    fun initPieces(): ArrayList<Piece> {
        val piecesList = ArrayList<Piece>()
        // white pawns
        piecesList.add(Piece(0, 0, 0, 6, true))
        piecesList.add(Piece(0, 0, 1, 6, true))
        piecesList.add(Piece(0, 0, 2, 6, true))
        piecesList.add(Piece(0, 0, 3, 6, true))
        piecesList.add(Piece(0, 0, 4, 6, true))
        piecesList.add(Piece(0, 0, 5, 6, true))
        piecesList.add(Piece(0, 0, 6, 6, true))
        piecesList.add(Piece(0, 0, 7, 6, true))

        //black pawns
        piecesList.add(Piece(0, 1, 0, 1, true))
        piecesList.add(Piece(0, 1, 1, 1, true))
        piecesList.add(Piece(0, 1, 2, 1, true))
        piecesList.add(Piece(0, 1, 3, 1, true))
        piecesList.add(Piece(0, 1, 4, 1, true))
        piecesList.add(Piece(0, 1, 5, 1, true))
        piecesList.add(Piece(0, 1, 6, 1, true))
        piecesList.add(Piece(0, 1, 7, 1, true))

        //white rest
        piecesList.add(Piece(3, 0, 0, 7, true))
        piecesList.add(Piece(3, 0, 7, 7, true))
        piecesList.add(Piece(2, 0, 1, 7, true))
        piecesList.add(Piece(2, 0, 6, 7, true))
        piecesList.add(Piece(1, 0, 2, 7, true))
        piecesList.add(Piece(1, 0, 5, 7, true))
        piecesList.add(Piece(4, 0, 3, 7, true))
        piecesList.add(Piece(5, 0, 4, 7, true))

        //black rest
        piecesList.add(Piece(3, 1, 0, 0, true))
        piecesList.add(Piece(3, 1, 7, 0, true))
        piecesList.add(Piece(2, 1, 1, 0, true))
        piecesList.add(Piece(2, 1, 6, 0, true))
        piecesList.add(Piece(1, 1, 2, 0, true))
        piecesList.add(Piece(1, 1, 5, 0, true))
        piecesList.add(Piece(4, 1, 3, 0, true))
        piecesList.add(Piece(5, 1, 4, 0, true))

        return piecesList
    }

    fun getPieceImage(type: Int, color: Int, context: Context): Drawable {
        var drawable = (context.getDrawable(R.drawable.pawn_white)) as Drawable
        when (type) {
            0 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.pawn_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.pawn_black)) as Drawable
                }
            }
            1 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.bishop_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.bishop_black)) as Drawable
                }
            }

            2 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.knight_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.knight_black)) as Drawable
                }
            }
            3 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.rook_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.rook_black)) as Drawable
                }
            }
            4 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.queen_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.queen_black)) as Drawable
                }
            }
            5 -> {
                if (color == 0) {
                    drawable = (context.getDrawable(R.drawable.king_white)) as Drawable
                } else {
                    drawable = (context.getDrawable(R.drawable.king_black)) as Drawable
                }
            }
        }
        return drawable
    }
}