package com.partos.chess.logic.computer

import com.partos.chess.logic.UserInteractionLogic
import com.partos.chess.models.AIMove
import com.partos.chess.models.Move
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import kotlin.random.Random

class MiniMaxComputer {

    fun getBestMove(
        baseParametersGroup: BaseParametersGroup,
        turn: Int,
        userInteractionLogic: UserInteractionLogic,
        depth: Int
    ): Move {
        val moves = MoveListGenerator().generateMovesList(
            baseParametersGroup,
            turn,
            userInteractionLogic,
            depth
        )
        val aiMoves = ArrayList<AIMove>()
        for (move in moves){
            aiMoves.add(AIMove(
                move.movesList.first().piece,
                move.movesList.first().positionY,
                move.movesList.first().positionX,
                generateFinalMoveValue(move.movesList)
            )
            )
        }

        val bestMoveValue = getBestMoveValue(aiMoves)
        val bestMoves = getBestMoves(bestMoveValue, aiMoves)
        return getRandomBestMove(bestMoves)
    }

    private fun generateFinalMoveValue(movesList: ArrayList<AIMove>): Int {
        var sum = 0
        for (move in movesList) {
            sum += move.value
        }
        return sum
    }

    private fun getBestMoveValue(aIMoves: ArrayList<AIMove>): Int {
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

    private fun getRandomBestMove(bestMoves: ArrayList<AIMove>): Move {
        val random = Random.nextInt(0, bestMoves.size)
        return Move(
            bestMoves[random].piece,
            bestMoves[random].positionY,
            bestMoves[random].positionX
        )
    }
}