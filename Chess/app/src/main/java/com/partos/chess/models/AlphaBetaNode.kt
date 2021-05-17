package com.partos.chess.models

import com.partos.chess.enums.PieceType
import com.partos.chess.logic.computer.AdvancedMoveValueCalculator
import com.partos.chess.logic.computer.BoardMoveValueCalculator
import com.partos.chess.logic.helpers.piecesHelpers.PiecesBoardHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesEnumHelper
import com.partos.chess.models.parameters.MovesAndFlags

class AlphaBetaNode(
    private val depth: Int
) {
    var boardMoves = HashMap<BoardMove, Int>()

    fun createNodesList(givenDepth: Int, alpha: Int, beta: Int, valueGiven: Int, turn: Int, isMaximizer: Boolean, gameDescription: GameDescription): Int?{
        var value = valueGiven
        val nextTurn = if (turn == 0){
            1
        } else {
            0
        }

        if (givenDepth == 0){
            val leafValues = calculateLeafValues(nextTurn, gameDescription)
            if (!isMaximizer)
                return leafValues.maxOrNull()
            else
                return leafValues.minOrNull()
        }

        val board = gameDescription.board
        if (turn == 0) {
            for (i in 0..7) {
                for (j in 0..7) {
                    if (PiecesEnumHelper().isWhite(board[i][j])) {
                        val moves = PiecesBoardHelper().getPieceMoves(gameDescription, i, j)
                        val pieceMoves = getPieceMoves(moves, i, j, gameDescription)
                        for (move in pieceMoves) {
                            if (isMaximizer){
                                if (value > beta)
                                    break
                            } else {
                                if (value < alpha)
                                    break
                            }

                            val calculatedValue = if (isMaximizer){
                                createNodesList(givenDepth - 1, value, beta, Int.MAX_VALUE, nextTurn, !isMaximizer, move.gameDescription)
                            } else {
                                createNodesList(givenDepth - 1, alpha, value, -Int.MAX_VALUE, nextTurn, !isMaximizer, move.gameDescription)
                            }

                            if (calculatedValue != null){
                                if (isMaximizer){
                                    if (calculatedValue > value){
                                        value = calculatedValue
                                    }
                                    if (value > beta)
                                        break
                                } else {
                                    if (calculatedValue < value){
                                        value = calculatedValue
                                        if (givenDepth == depth){
                                            boardMoves[move] = value
                                        }
                                    }
                                    if (value < alpha)
                                        break
                                }
                                if (givenDepth == depth){
                                    boardMoves[move] = calculatedValue
                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (i in 0..7) {
                for (j in 0..7) {
                    if (PiecesEnumHelper().isBlack(board[i][j])) {
                        val moves = PiecesBoardHelper().getPieceMoves(gameDescription, i, j)
                        val pieceMoves = getPieceMoves(moves, i, j, gameDescription)
                        for (move in pieceMoves) {
                            if (isMaximizer){
                                if (value > beta)
                                    break
                            } else {
                                if (value < alpha)
                                    break
                            }

                            val calculatedValue = if (isMaximizer){
                                createNodesList(givenDepth - 1, value, beta, Int.MAX_VALUE, nextTurn, !isMaximizer, move.gameDescription)
                            } else {
                                createNodesList(givenDepth - 1, alpha, value, -Int.MAX_VALUE, nextTurn, !isMaximizer, move.gameDescription)
                            }

                            if (calculatedValue != null){
                                if (isMaximizer){
                                    if (calculatedValue > value){
                                        value = calculatedValue
                                    }
                                    if (value > beta)
                                        break
                                } else {
                                    if (calculatedValue < value){
                                        value = calculatedValue
                                        if (givenDepth == depth){
                                            boardMoves[move] = value
                                        }
                                    }
                                    if (value < alpha)
                                        break
                                }
                                if (givenDepth == depth){
                                    boardMoves[move] = calculatedValue
                                }
                            }
                        }
                    }
                }
            }
        }
        return value
    }

    private fun calculateLeafValues(turn: Int, gameDescription: GameDescription): ArrayList<Int> {
        val leafValues = ArrayList<Int>()
        val board = gameDescription.board
        if (turn == 0) {
            for (i in 0..7) {
                for (j in 0..7) {
                    if (PiecesEnumHelper().isWhite(board[i][j])) {
                        val moves = PiecesBoardHelper().getPieceMoves(gameDescription, i, j)
                        val pieceMoves = getPieceMoves(moves, i, j, gameDescription)
                        for (move in pieceMoves) {
                            leafValues.add(BoardMoveValueCalculator().calculateMoveValue(gameDescription, turn))
                        }
                    }
                }
            }
        } else {
            for (i in 0..7) {
                for (j in 0..7) {
                    if (PiecesEnumHelper().isBlack(board[i][j])) {
                        val moves = PiecesBoardHelper().getPieceMoves(gameDescription, i, j)
                        val pieceMoves = getPieceMoves(moves, i, j, gameDescription)
                        for (move in pieceMoves) {
                            leafValues.add(AdvancedMoveValueCalculator().calculateMoveValue(gameDescription, turn))
                        }
                    }
                }
            }
        }
        return leafValues
    }


    // to możnaby jakoś skrócić, w sensie przyspieszyć, bo niepotrzebnie kopiuję za każdym razem koordynaty
    // i tworzę nowe obiekty, a to jest czas
    private fun getPieceMoves(
        moves: MovesAndFlags,
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): ArrayList<BoardMove> {
        val boardMoves = ArrayList<BoardMove>()
        for (i in 0..7) {
            for (j in 0..7) {
                if (moves.moves[i][j]) {
                    val board = PiecesBoardHelper().getBoardAfterMakingMove(
                        gameDescription.board,
                        pieceY,
                        pieceX,
                        i,
                        j
                    )
                    boardMoves.add(
                        BoardMove(
                            GameDescription(
                                gameDescription.gameFlags,
                                board,
                                gameDescription.pawnSpecialMove
                            ),
                            Coordinates(pieceX, pieceY),
                            Coordinates(j, i)
                        )
                    )
                }
            }
        }
        return boardMoves
    }
}