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

    fun isWhiteOrEmpty(pieceType: PieceType): Boolean {
        when (pieceType) {
            PieceType.WhitePawn, PieceType.WhiteBishop, PieceType.WhiteKnight, PieceType.WhiteRook,
            PieceType.WhiteQueen, PieceType.WhiteKing, PieceType.Empty -> return true
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

    fun isBlackOrEmpty(pieceType: PieceType): Boolean {
        when (pieceType) {
            PieceType.BlackPawn, PieceType.BlackBishop, PieceType.BlackKnight, PieceType.BlackRook,
            PieceType.BlackQueen, PieceType.BlackKing, PieceType.Empty -> return true
            else -> return false
        }
    }

    fun getPieceType(pieceType: PieceType): PieceType {
        return when (pieceType) {
            PieceType.Empty -> PieceType.Empty
            PieceType.WhitePawn -> PieceType.WhitePawn
            PieceType.BlackPawn -> PieceType.BlackPawn
            PieceType.WhiteBishop -> PieceType.WhiteBishop
            PieceType.BlackBishop -> PieceType.BlackBishop
            PieceType.WhiteKnight -> PieceType.WhiteKnight
            PieceType.BlackKnight -> PieceType.BlackKnight
            PieceType.WhiteRook -> PieceType.WhiteRook
            PieceType.BlackRook -> PieceType.BlackRook
            PieceType.WhiteQueen -> PieceType.WhiteQueen
            PieceType.BlackQueen -> PieceType.BlackQueen
            PieceType.WhiteKing -> PieceType.WhiteKing
            PieceType.BlackKing -> PieceType.BlackKing
        }
    }
}