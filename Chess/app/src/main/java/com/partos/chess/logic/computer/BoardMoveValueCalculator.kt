package com.partos.chess.logic.computer

import com.partos.chess.enums.PieceType
import com.partos.chess.models.GameDescription

class BoardMoveValueCalculator {

    fun calculateMoveValue(gameDescription: GameDescription, turn: Int): Int {
        val materialAdvantage = calculateMaterialAdvantage(gameDescription, turn)
        val centerOccupation = calculateCenterOccupation(gameDescription.board, turn)


        return materialAdvantage + centerOccupation
    }

    private fun calculateCenterOccupation(board: Array<Array<PieceType>>, turn: Int): Int {
        var advantage = 0
        if (turn == 0) {
            if (board[3][3] == PieceType.WhitePawn) {
                advantage += 32
            } else if (board[3][3] == PieceType.BlackPawn) {
                advantage -= 31
            }

            if (board[3][4] == PieceType.WhitePawn) {
                advantage += 32
            } else if (board[3][4] == PieceType.BlackPawn) {
                advantage -= 31
            }

            if (board[4][3] == PieceType.WhitePawn) {
                advantage += 32
            } else if (board[4][3] == PieceType.BlackPawn) {
                advantage -= 31
            }

            if (board[4][4] == PieceType.WhitePawn) {
                advantage += 32
            } else if (board[4][4] == PieceType.BlackPawn) {
                advantage -= 31
            }
        } else {
            if (board[3][3] == PieceType.WhitePawn) {
                advantage -= 31
            } else if (board[3][3] == PieceType.BlackPawn) {
                advantage += 32
            }

            if (board[3][4] == PieceType.WhitePawn) {
                advantage -= 31
            } else if (board[3][4] == PieceType.BlackPawn) {
                advantage += 32
            }

            if (board[4][3] == PieceType.WhitePawn) {
                advantage -= 31
            } else if (board[4][3] == PieceType.BlackPawn) {
                advantage += 32
            }

            if (board[4][4] == PieceType.WhitePawn) {
                advantage -= 31
            } else if (board[4][4] == PieceType.BlackPawn) {
                advantage += 32
            }
        }


        return advantage
    }

    private fun calculateMaterialAdvantage(gameDescription: GameDescription, turn: Int): Int {
        var advantage = 0
        val board = gameDescription.board
        if (turn == 0) {
            for (i in 0..7) {
                for (j in 0..7) {
                    advantage += getPieceValueForWhite(board[i][j])
                }
            }
        } else {
            for (i in 0..7) {
                for (j in 0..7) {
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
