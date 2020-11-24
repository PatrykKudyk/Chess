package com.partos.chess.logic.computer

import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.logic.helpers.piecesHelpers.KingHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.logic.helpers.piecesHelpers.QueenHelper
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.PieceParameters

class MoveValueCalculator {

    fun calculateMoveValue(baseParametersGroup: BaseParametersGroup, turn: Int): Int {
        val materialAdvantage =
            calculateMaterialAdvantage(baseParametersGroup.pieceParameters.piecesList, turn)
        val kingSafety = calculateKingSafety(baseParametersGroup, turn)

        return materialAdvantage + kingSafety
    }

    private fun calculateKingSafety(baseParametersGroup: BaseParametersGroup, turn: Int): Int {
        val queenWhite = QueenHelper().findQueen(baseParametersGroup.pieceParameters.piecesList, 0)
        val queenBlack = QueenHelper().findQueen(baseParametersGroup.pieceParameters.piecesList, 1)
        if ((turn == 0 && queenBlack.isActive) || (turn == 1 && queenWhite.isActive)) {
            var value = 0
            val king = KingHelper().findKing(turn, baseParametersGroup.pieceParameters.piecesList)
            baseParametersGroup.pieceParameters.piece = king
            val kingMoves = PiecesHelper().showPieceMoves(baseParametersGroup).moves
            if (PiecesHelper().isCheck(baseParametersGroup, turn)) {
                value -= 100
            }
            val kingPossibleMoves = MovesHelper().getMovesNumber(kingMoves)
            val kingMaxMoves = getKingMaxMoves(king)
            val allyPiecesSurrounding =
                getKingAllySurroundingsValue(king, baseParametersGroup.pieceParameters)
            when (kingMaxMoves - kingPossibleMoves - allyPiecesSurrounding) {
                0 -> value += 40
                1 -> value += 30
                2 -> value += 20
                3 -> value += 10
                4 -> value += 0
                5 -> value -= 10
                6 -> value -= 20
                7 -> value -= 30
                8 -> value -= 40
            }
            return value
        } else {
            return 0
        }
    }

    private fun getKingAllySurroundingsValue(king: Piece, pieceParams: PieceParameters): Int {
        var allySurroundings = 0
        if (king.positionY > 0) {
            if (BoardHelper().isPiece(pieceParams.board[king.positionY - 1][king.positionX])) {
                if (PiecesHelper().findPiece(
                        king.positionY - 1,
                        king.positionX,
                        pieceParams.piecesList
                    ).color == king.color
                ){
                    allySurroundings++
                }
            }
        }
        if (king.positionY > 0 && king.positionX < 7) {
            if (BoardHelper().isPiece(pieceParams.board[king.positionY - 1][king.positionX + 1])) {
                if (PiecesHelper().findPiece(
                        king.positionY - 1,
                        king.positionX + 1,
                        pieceParams.piecesList
                    ).color == king.color
                ){
                    allySurroundings++
                }
            }
        }
        if (king.positionX < 7) {
            if (BoardHelper().isPiece(pieceParams.board[king.positionY][king.positionX + 1])) {
                if (PiecesHelper().findPiece(
                        king.positionY,
                        king.positionX + 1,
                        pieceParams.piecesList
                    ).color == king.color
                ){
                    allySurroundings++
                }
            }
        }
        if (king.positionY < 7 && king.positionX < 7) {
            if (BoardHelper().isPiece(pieceParams.board[king.positionY + 1][king.positionX + 1])) {
                if (PiecesHelper().findPiece(
                        king.positionY + 1,
                        king.positionX + 1,
                        pieceParams.piecesList
                    ).color == king.color
                ){
                    allySurroundings++
                }
            }
        }
        if (king.positionY < 7) {
            if (BoardHelper().isPiece(pieceParams.board[king.positionY + 1][king.positionX])) {
                if (PiecesHelper().findPiece(
                        king.positionY + 1,
                        king.positionX,
                        pieceParams.piecesList
                    ).color == king.color
                ){
                    allySurroundings++
                }
            }
        }
        if (king.positionY < 7 && king.positionX > 0) {
            if (BoardHelper().isPiece(pieceParams.board[king.positionY + 1][king.positionX - 1])) {
                if (PiecesHelper().findPiece(
                        king.positionY + 1,
                        king.positionX - 1,
                        pieceParams.piecesList
                    ).color == king.color
                ){
                    allySurroundings++
                }
            }
        }
        if (king.positionX > 0) {
            if (BoardHelper().isPiece(pieceParams.board[king.positionY][king.positionX - 1])) {
                if (PiecesHelper().findPiece(
                        king.positionY,
                        king.positionX - 1,
                        pieceParams.piecesList
                    ).color == king.color
                ){
                    allySurroundings++
                }
            }
        }
        if (king.positionX > 0 && king.positionY > 0) {
            if (BoardHelper().isPiece(pieceParams.board[king.positionY - 1][king.positionX - 1])) {
                if (PiecesHelper().findPiece(
                        king.positionY - 1,
                        king.positionX - 1,
                        pieceParams.piecesList
                    ).color == king.color
                ){
                    allySurroundings++
                }
            }
        }
        return allySurroundings
    }

    private fun getKingMaxMoves(king: Piece): Int {
        if (king.color == 0 && king.positionY == 7) {
            return 5
        } else if (king.color == 1 && king.positionY == 0) {
            return 5
        }
        return 8
    }

    private fun calculateMaterialAdvantage(piecesList: ArrayList<Piece>, turn: Int): Int {
        var ownMaterial = 0
        for (piece in piecesList) {
            if (piece.color == turn) {
                ownMaterial += getPieceValue(piece.type)
            }
        }
        var enemyMaterial = 0
        for (piece in piecesList) {
            if (piece.color != turn) {
                enemyMaterial += getPieceValue(piece.type)
            }
        }
        return ownMaterial - enemyMaterial
    }

    private fun getPieceValue(type: Int): Int {
        when (type) {
            0 -> return 128
            1 -> return 448
            2 -> return 416
            3 -> return 640
            4 -> return 1248
            5 -> return 1536
            else -> return 0
        }
    }


}