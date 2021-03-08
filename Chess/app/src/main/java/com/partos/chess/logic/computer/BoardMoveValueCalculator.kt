package com.partos.chess.logic.computer

import com.partos.chess.enums.PieceType
import com.partos.chess.models.GameDescription

class BoardMoveValueCalculator {

    fun calculateMoveValue(gameDescription: GameDescription, turn: Int): Int {
        val materialAdvantage = calculateMaterialAdvantage(gameDescription, turn)

        return materialAdvantage
    }

    private fun calculateMaterialAdvantage(gameDescription: GameDescription, turn: Int): Int {
        var advantage = 0
        val board = gameDescription.board
        if (turn == 0){
            for (i in 0..7){
                for (j in 0..7){
                    advantage += getPieceValueForWhite(board[i][j])
                }
            }
        } else {
            for (i in 0..7){
                for (j in 0..7){
                    advantage += getPieceValueForBlack(board[i][j])
                }
            }
        }
        return advantage
    }

    private fun getPieceValueForWhite(piece: PieceType): Int {
        return when (piece) {
            PieceType.Empty -> 0
            PieceType.WhitePawn -> 128
            PieceType.BlackPawn -> -128
            PieceType.BlackBishop -> -448
            PieceType.WhiteBishop -> 448
            PieceType.WhiteKnight -> 416
            PieceType.BlackKnight -> -416
            PieceType.BlackRook -> -640
            PieceType.WhiteRook -> 640
            PieceType.WhiteQueen -> 1248
            PieceType.BlackQueen -> -1248
            PieceType.WhiteKing -> 1536
            PieceType.BlackKing -> -1536
        }
    }

    private fun getPieceValueForBlack(piece: PieceType): Int {
        return when (piece) {
            PieceType.Empty -> 0
            PieceType.WhitePawn -> -128
            PieceType.BlackPawn -> 128
            PieceType.BlackBishop -> 448
            PieceType.WhiteBishop -> -448
            PieceType.WhiteKnight -> -416
            PieceType.BlackKnight -> 416
            PieceType.BlackRook -> 640
            PieceType.WhiteRook -> -640
            PieceType.WhiteQueen -> -1248
            PieceType.BlackQueen -> 1248
            PieceType.WhiteKing -> -1536
            PieceType.BlackKing -> 1536
        }
    }
}
