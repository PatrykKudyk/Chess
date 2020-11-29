package com.partos.chess.logic.computer

import com.partos.chess.logic.UserInteractionLogic
import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.models.AIMove
import com.partos.chess.models.Move
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import kotlin.random.Random

class BestMoveComputer {

    fun makeBestMove(
        baseParametersGroup: BaseParametersGroup,
        turn: Int,
        userInteractionLogic: UserInteractionLogic
    ): Move {
        val availablePieces = getAvailablePieces(baseParametersGroup, turn)
        val availableMoves = getAvailableMoves(availablePieces, baseParametersGroup)
        val aIMoves = generateBestMoves(availableMoves, baseParametersGroup, userInteractionLogic, turn)
//        BoardHelper().resetBoard(baseParametersGroup.pieceParameters.piecesList, baseParametersGroup.pieceParameters.board, baseParametersGroup.pieceParameters.context)
        val bestMoveValue = getBestMoveValue(aIMoves)
        val bestMoves = getBestMoves(bestMoveValue, aIMoves)
//        BoardHelper().resetBoard(baseParametersGroup.pieceParameters.piecesList, baseParametersGroup.pieceParameters.board, baseParametersGroup.pieceParameters.context)
        return getRandomBestMove(bestMoves)
    }

    private fun getAvailableMoves(
        availablePieces: ArrayList<Piece>,
        baseParametersGroup: BaseParametersGroup
    ): ArrayList<Move> {
        val moves = ArrayList<Move>()
        for (piece in availablePieces) {
            baseParametersGroup.pieceParameters.piece = piece
            val params = PiecesHelper().showPieceMoves(baseParametersGroup)
            for (i in 0..7) {
                for (j in 0..7) {
                    if (params.moves[i][j]) {
                        moves.add(
                            Move(
                                piece,
                                i,
                                j
                            )
                        )
                    }
                }
            }
        }
        return moves
    }

    private fun getRandomBestMove(bestMoves: ArrayList<AIMove>): Move {
        val random = Random.nextInt(0, bestMoves.size)
        return Move(
            bestMoves[random].piece,
            bestMoves[random].positionY,
            bestMoves[random].positionX
        )
    }

    private fun generateBestMoves(
        availableMoves: ArrayList<Move>,
        baseParametersGroup: BaseParametersGroup,
        userInteractionLogic: UserInteractionLogic,
        turn: Int
    ): ArrayList<AIMove> {
        val aiMoves = ArrayList<AIMove>()
        for (move in availableMoves) {
            baseParametersGroup.pieceParameters.piece = move.piece
            aiMoves.add(generateMoveValue(move, baseParametersGroup, userInteractionLogic, turn))
        }

        return aiMoves
    }

    private fun generateMoveValue(
        move: Move,
        baseParametersGroup: BaseParametersGroup,
        userInteractionLogic: UserInteractionLogic,
        turn: Int
    ): AIMove {
        val userIL = UserInteractionLogic().makeCopy(userInteractionLogic)
        val baseParams = userIL.simulateMove(move)
        return AIMove(
            move.piece,
            move.positionY,
            move.positionX,
            MoveValueCalculator().calculateMoveValue(baseParams, turn)
        )
    }

    private fun getBestMoveValue(aIMoves: java.util.ArrayList<AIMove>): Int {
        var bestValue = -1*Int.MAX_VALUE
        for (move in aIMoves) {
            if (move.value > bestValue) {
                bestValue = move.value
            }
        }
        return bestValue
    }

    private fun getBestMoves(bestMoveValue: Int, aIMoves: ArrayList<AIMove>): ArrayList<AIMove> {
        val aiMoves = ArrayList<AIMove>()
        for (move in aIMoves) {
            if (move.value == bestMoveValue) {
                aiMoves.add(move)
            }
        }
        return aiMoves
    }

    private fun getAvailablePieces(
        baseParametersGroup: BaseParametersGroup,
        turn: Int
    ): ArrayList<Piece> {
        val availablePieces = ArrayList<Piece>()
        for (piece in baseParametersGroup.pieceParameters.piecesList) {
            if (piece.isActive && piece.color == turn) {
                availablePieces.add(piece.copy())
            }
        }
        return availablePieces
    }
}