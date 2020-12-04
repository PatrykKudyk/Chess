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
//        val kingSafety = calculateKingSafety(baseParametersGroup, turn)
        val pawnMiddleOccupation =
            calculatePawnMiddleOccupation(baseParametersGroup.pieceParameters.piecesList, turn)
//        val middleMoveOccupation = calculateMiddleMoveOccupation(baseParametersGroup, turn)
        val checkAdvantage = calculateCheckAdvantage(baseParametersGroup, turn)
        val pawnStructureRatio =
            calculatePawnStructureRatio(baseParametersGroup.pieceParameters.piecesList, turn)

        return materialAdvantage + pawnMiddleOccupation + checkAdvantage + pawnStructureRatio//+ middleMoveOccupation //+ kingSafety
    }

    private fun calculatePawnStructureRatio(piecesList: ArrayList<Piece>, turn: Int): Int {
        val pawnTable = PiecesHelper().createPawnTable(piecesList)
        val pointsForTriplingPawns = calculatePointsForTriplingPawns(pawnTable, turn)
        val pointsForDoublingPawns = calculatePointsForDoublingPawns(pawnTable, turn)
        val pointsForIsolatingPawns = calculatePointsForIsolatedPawns(pawnTable, turn)
        return pointsForTriplingPawns + pointsForDoublingPawns + pointsForIsolatingPawns
    }

    private fun calculatePointsForIsolatedPawns(pawnTable: Array<Array<Int>>, turn: Int): Int {
        var points = 0
        val oppositeColor = if (turn == 0) {
            1
        } else {
            0
        }
        for (i in 0..7) {
            for (j in 0..7) {
                if (pawnTable[i][j] == oppositeColor) {
                    if (isPawnIsolated(pawnTable, i, j, oppositeColor)) {
                        points += 6
                    }
                } else if (pawnTable[i][j] == turn) {
                    if (isPawnIsolated(pawnTable, i, j, turn)) {
                        points -= 6
                    }
                }
            }
        }

        return points
    }

    private fun isPawnIsolated(pawnTable: Array<Array<Int>>, positionY: Int, positionX: Int, color: Int): Boolean {
        return when (positionX) {
            0 -> isPawnIsolatedLeft(pawnTable, positionY, positionX, color)
            7 -> isPawnIsolatedRight(pawnTable, positionY, positionX, color)
            else -> isPawnIsolatedCenter(pawnTable, positionY, positionX, color)
        }
    }

    private fun isPawnIsolatedRight(
        pawnTable: Array<Array<Int>>,
        positionY: Int,
        positionX: Int,
        color: Int): Boolean {
        if (pawnTable[positionY - 1][positionX] == color)
            return false
        if (pawnTable[positionY - 1][positionX - 1] == color)
            return false
        if (pawnTable[positionY][positionX - 1] == color)
            return false
        if (pawnTable[positionY + 1][positionX - 1] == color)
            return false
        if (pawnTable[positionY - 1][positionX] == color)
            return false
        return true
    }

    private fun isPawnIsolatedLeft(
        pawnTable: Array<Array<Int>>,
        positionY: Int,
        positionX: Int,
        color: Int): Boolean {
        if (pawnTable[positionY - 1][positionX] == color)
            return false
        if (pawnTable[positionY - 1][positionX + 1] == color)
            return false
        if (pawnTable[positionY][positionX + 1] == color)
            return false
        if (pawnTable[positionY + 1][positionX + 1] == color)
            return false
        if (pawnTable[positionY - 1][positionX] == color)
            return false
        return true
    }

    private fun isPawnIsolatedCenter(
        pawnTable: Array<Array<Int>>,
        positionY: Int,
        positionX: Int,
        color: Int
    ): Boolean {
        if (pawnTable[positionY - 1][positionX] == color)
            return false
        if (pawnTable[positionY - 1][positionX - 1] == color)
            return false
        if (pawnTable[positionY][positionX - 1] == color)
            return false
        if (pawnTable[positionY + 1][positionX - 1] == color)
            return false
        if (pawnTable[positionY - 1][positionX + 1] == color)
            return false
        if (pawnTable[positionY][positionX + 1] == color)
            return false
        if (pawnTable[positionY + 1][positionX + 1] == color)
            return false
        if (pawnTable[positionY - 1][positionX] == color)
            return false
        return true
    }

    private fun calculatePointsForDoublingPawns(pawnTable: Array<Array<Int>>, turn: Int): Int {
        var points = 0
        val oppositeColor = if (turn == 0) {
            1
        } else {
            0
        }
        for (i in 0..7) {
            var enemyPawns = 0
            var enemyPawn1x = -1
            var enemyPawn2x = -1
            var enemyPawn1y = -1
            var enemyPawn2y = -1
            var allyPawns = 0
            var allyPawn1x = -1
            var allyPawn2x = -1
            var allyPawn1y = -1
            var allyPawn2y = -1

            for (j in 0..7) {
                if (pawnTable[j][i] == oppositeColor) {
                    enemyPawns++
                    if (enemyPawn1x != -1) {
                        enemyPawn2x = i
                        enemyPawn2y = j
                    } else {
                        enemyPawn1x = i
                        enemyPawn1y = j
                    }
                }
                if (pawnTable[j][i] == turn) {
                    allyPawns++
                    if (allyPawn1x != -1) {
                        allyPawn2x = i
                        allyPawn2y = j
                    } else {
                        allyPawn1x = i
                        allyPawn1y = j
                    }
                }
            }
            if (enemyPawns == 2) {
                if (checkIfTwoPawnsAreIsolated(
                        pawnTable,
                        enemyPawn1x,
                        enemyPawn1y,
                        enemyPawn2x,
                        enemyPawn2y,
                        oppositeColor
                    )
                ) {
                    points += 9
                }
            }
            if (allyPawns == 2) {
                if (checkIfTwoPawnsAreIsolated(
                        pawnTable,
                        allyPawn1x,
                        allyPawn1y,
                        allyPawn2x,
                        allyPawn2y,
                        oppositeColor
                    )
                ) {
                    points -= 9
                }
            }
        }
        return points
    }

    private fun checkIfTwoPawnsAreIsolated(
        pawnTable: Array<Array<Int>>,
        pawn1x: Int,
        pawn1y: Int,
        pawn2x: Int,
        pawn2y: Int,
        color: Int
    ): Boolean {
        if (pawn1x == 0) {
            return checkIfTwoPawnsAreIsolatedForLeftColumn(pawnTable, pawn1x, pawn1y, pawn2x, pawn2y, color)
        } else if (pawn1x == 7) {
            return checkIfTwoPawnsAreIsolatedForRightColumn(pawnTable, pawn1x, pawn1y, pawn2x, pawn2y, color)
        } else {
            return checkIfTwoPawnsAreIsolatedForCenterColumn(pawnTable, pawn1x, pawn1y, pawn2x, pawn2y, color)
        }
    }

    private fun checkIfTwoPawnsAreIsolatedForCenterColumn(
        pawnTable: Array<Array<Int>>,
        pawn1x: Int,
        pawn1y: Int,
        pawn2x: Int,
        pawn2y: Int,
        color: Int
    ): Boolean {
        if (pawnTable[pawn1y - 1][pawn1x] == color)
            return false
        if (pawnTable[pawn1y - 1][pawn1x - 1] == color)
            return false
        if (pawnTable[pawn1y][pawn1x - 1] == color)
            return false
        if (pawnTable[pawn1y + 1][pawn1x - 1] == color)
            return false
        if (pawnTable[pawn1y - 1][pawn1x + 1] == color)
            return false
        if (pawnTable[pawn1y][pawn1x + 1] == color)
            return false
        if (pawnTable[pawn1y + 1][pawn1x + 1] == color)
            return false
        if (pawn1y + 1 != pawn2y) {
            if (pawnTable[pawn1y - 1][pawn1x] == color)
                return false
        }
        if (pawn2y - 1 != pawn1y) {
            if (pawnTable[pawn2y - 1][pawn2x] == color)
                return false
        }
        if (pawnTable[pawn2y - 1][pawn2x - 1] == color)
            return false
        if (pawnTable[pawn2y][pawn2x - 1] == color)
            return false
        if (pawnTable[pawn2y + 1][pawn2x - 1] == color)
            return false
        if (pawnTable[pawn2y - 1][pawn2x + 1] == color)
            return false
        if (pawnTable[pawn2y][pawn2x + 1] == color)
            return false
        if (pawnTable[pawn2y + 1][pawn2x + 1] == color)
            return false
        if (pawnTable[pawn2y - 1][pawn2x] == color)
            return false

        return true
    }

    private fun checkIfTwoPawnsAreIsolatedForRightColumn(
        pawnTable: Array<Array<Int>>,
        pawn1x: Int,
        pawn1y: Int,
        pawn2x: Int,
        pawn2y: Int,
        color: Int
    ): Boolean {
        if (pawnTable[pawn1y - 1][pawn1x] == color)
            return false
        if (pawnTable[pawn1y - 1][pawn1x - 1] == color)
            return false
        if (pawnTable[pawn1y][pawn1x - 1] == color)
            return false
        if (pawnTable[pawn1y + 1][pawn1x - 1] == color)
            return false
        if (pawn1y + 1 != pawn2y) {
            if (pawnTable[pawn1y - 1][pawn1x] == color)
                return false
        }
        if (pawn2y - 1 != pawn1y) {
            if (pawnTable[pawn2y - 1][pawn2x] == color)
                return false
        }
        if (pawnTable[pawn2y - 1][pawn2x - 1] == color)
            return false
        if (pawnTable[pawn2y][pawn2x - 1] == color)
            return false
        if (pawnTable[pawn2y + 1][pawn2x - 1] == color)
            return false
        if (pawnTable[pawn2y - 1][pawn2x] == color)
            return false

        return true
    }

    private fun checkIfTwoPawnsAreIsolatedForLeftColumn(
        pawnTable: Array<Array<Int>>,
        pawn1x: Int,
        pawn1y: Int,
        pawn2x: Int,
        pawn2y: Int,
        color: Int
    ): Boolean {
        if (pawnTable[pawn1y - 1][pawn1x] == color)
            return false
        if (pawnTable[pawn1y - 1][pawn1x + 1] == color)
            return false
        if (pawnTable[pawn1y][pawn1x + 1] == color)
            return false
        if (pawnTable[pawn1y + 1][pawn1x + 1] == color)
            return false
        if (pawn1y + 1 != pawn2y) {
            if (pawnTable[pawn1y - 1][pawn1x] == color)
                return false
        }
        if (pawn2y - 1 != pawn1y) {
            if (pawnTable[pawn2y - 1][pawn2x] == color)
                return false
        }
        if (pawnTable[pawn2y - 1][pawn2x + 1] == color)
            return false
        if (pawnTable[pawn2y][pawn2x + 1] == color)
            return false
        if (pawnTable[pawn2y + 1][pawn2x + 1] == color)
            return false
        if (pawnTable[pawn2y - 1][pawn2x] == color)
            return false

        return true
    }

    private fun calculatePointsForTriplingPawns(pawnTable: Array<Array<Int>>, turn: Int): Int {
        var points = 0
        val oppositeColor = if (turn == 0) {
            1
        } else {
            0
        }
        for (i in 0..7) {
            var enemyPawns = 0
            var ownPawns = 0
            for (j in 0..7) {
                if (pawnTable[j][i] == oppositeColor) {
                    enemyPawns++
                } else if (pawnTable[j][i] == turn) {
                    ownPawns++
                }
            }
            if (enemyPawns >= 3) {
                points += 15
            }
            if (ownPawns >= 3) {
                points -= 15
            }
        }
        return points
    }


    private fun calculateCheckAdvantage(baseParametersGroup: BaseParametersGroup, turn: Int): Int {
        var advantage = 0
        val oppositeColor = if (turn == 0) {
            1
        } else {
            0
        }
        if (PiecesHelper().isCheck(baseParametersGroup, oppositeColor)) {
            if (PiecesHelper().isCheckMate(baseParametersGroup, oppositeColor)) {
                advantage = 100000
            } else {
                advantage = 40
            }
        }
        return advantage
    }

    private fun calculateMiddleMoveOccupation(
        baseParametersGroup: BaseParametersGroup,
        turn: Int
    ): Int {
        var occupation = 0
        if (PiecesHelper().getActivePiecesAmount(baseParametersGroup.pieceParameters.piecesList) > 22) {
            for (piece in baseParametersGroup.pieceParameters.piecesList) {
                baseParametersGroup.pieceParameters.piece = piece
                val moves = PiecesHelper().showPieceMoves(baseParametersGroup).moves
                if (moves[3][3]) {
                    occupation += 5
                }
                if (moves[3][4]) {
                    occupation += 5
                }
                if (moves[4][3]) {
                    occupation += 5
                }
                if (moves[4][4]) {
                    occupation += 5
                }
            }
        }
        return occupation
    }

    private fun calculatePawnMiddleOccupation(
        piecesList: java.util.ArrayList<Piece>,
        turn: Int
    ): Int {
        var value = 0
        if (PiecesHelper().getActivePiecesAmount(piecesList) > 24) {
            for (piece in piecesList) {
                if ((piece.positionY == 3 || piece.positionY == 4) && (piece.positionX == 3 || piece.positionX == 4) && piece.color == turn) {
                    value += 30
                }
            }
        }
        return value
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
                ) {
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
                ) {
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
                ) {
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
                ) {
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
                ) {
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
                ) {
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
                ) {
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
                ) {
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
            if (piece.color == turn && piece.isActive) {
                ownMaterial += getPieceValue(piece.type)
            }
        }
        var enemyMaterial = 0
        for (piece in piecesList) {
            if (piece.color != turn && piece.isActive) {
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