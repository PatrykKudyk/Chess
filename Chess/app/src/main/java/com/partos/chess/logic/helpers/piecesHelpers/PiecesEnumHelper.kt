package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.enums.PieceType

class PiecesEnumHelper {

   fun isWhite(pieceType: PieceType): Boolean {
       when (pieceType) {
           PieceType.WhitePawn, PieceType.WhiteBishop, PieceType.WhiteKnight, PieceType.WhiteRook,
           PieceType.WhiteQueen, PieceType.WhiteKing -> return true
           else -> return false
       }
   }

    fun isBlack(pieceType: PieceType): Boolean {
        when (pieceType) {
            PieceType.BlackPawn, PieceType.BlackBishop, PieceType.BlackKnight, PieceType.BlackRook,
            PieceType.BlackQueen, PieceType.BlackKing -> return true
            else -> return false
        }
    }
}