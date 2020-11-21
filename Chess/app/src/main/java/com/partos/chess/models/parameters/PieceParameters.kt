package com.partos.chess.models.parameters

import android.content.Context
import android.widget.ImageView
import com.partos.chess.models.Piece

data class PieceParameters (
    var piece: Piece,
    var board: Array<Array<ImageView>>,
    var moves: Array<Array<Boolean>>,
    var piecesList: ArrayList<Piece>,
    val context: Context
)