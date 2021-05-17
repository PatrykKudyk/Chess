package com.partos.chess.logic.computer

import com.partos.chess.enums.PieceType
import com.partos.chess.logic.helpers.piecesHelpers.PiecesEnumHelper
import com.partos.chess.models.GameDescription

class AdvancedMoveValueCalculator {

    fun calculateMoveValue(gameDescription: GameDescription, turn: Int): Int {
        val materialAdvantage = calculateMaterialAdvantage(gameDescription, turn)


        return materialAdvantage
    }

    private fun calculateMaterialAdvantage(gameDescription: GameDescription, turn: Int): Int {
        var advantage = 0
        if (turn == 0) {
            for (i in 0..7) {
                for (j in 0..7) {
                    if (PiecesEnumHelper().isWhite(gameDescription.board[i][j])) {
                        advantage += getPieceValue(gameDescription.board[i][j])
                    } else if (PiecesEnumHelper().isBlack(gameDescription.board[i][j])) {
                        advantage -= getPieceValue(gameDescription.board[i][j])
                    }
                }
            }
        } else {
            for (i in 0..7) {
                for (j in 0..7) {
                    if (PiecesEnumHelper().isWhite(gameDescription.board[i][j])) {
                        advantage -= getPieceValue(gameDescription.board[i][j])
                    } else if (PiecesEnumHelper().isBlack(gameDescription.board[i][j])) {
                        advantage += getPieceValue(gameDescription.board[i][j])
                    }
                }
            }
        }
        return advantage
    }

    private fun getPieceValue(pieceType: PieceType): Int {
        return when (pieceType) {
            PieceType.Empty -> 0
            PieceType.WhitePawn, PieceType.BlackPawn -> 128
            PieceType.BlackBishop, PieceType.WhiteBishop -> 448
            PieceType.WhiteKnight, PieceType.BlackKnight -> 416
            PieceType.WhiteRook, PieceType.BlackRook -> 640
            PieceType.WhiteQueen, PieceType.BlackQueen -> 1248
            PieceType.WhiteKing, PieceType.BlackKing -> 1536
        }
    }
}