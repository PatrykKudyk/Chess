package com.partos.chess.logic.computer

import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.models.*
import kotlin.random.Random

class AlphaBetaComputer {

    fun calculateBestMove(
        gameDescription: GameDescription,
        depth: Int,
        turn: Int,
        piecesList: ArrayList<Piece>
    ): Move {
        val alphaBetaRoot = AlphaBetaNode(
            depth
        )

        alphaBetaRoot.createNodesList(
            depth,
            -Int.MAX_VALUE,
            Int.MAX_VALUE,
            -Int.MAX_VALUE,
            turn,
            true,
            gameDescription
        )
        val moves = alphaBetaRoot.boardMoves
        val bestMoves = ArrayList<BoardMove>()
        var bestValue = -Int.MAX_VALUE

        for (move in moves){
            if (move.value > bestValue){
                bestMoves.clear()
                bestMoves.add(move.key)
                bestValue = move.value
            } else if (move.value == bestValue){
                bestMoves.add(move.key)
            }
        }
        val random = Random.nextInt(0, bestMoves.size)
        val move = bestMoves[random]

        val piece = PiecesHelper().findPiece(
            move.pieceCoordinates.y,
            move.pieceCoordinates.x,
            piecesList
        )
        return Move(
            piece,
            move.moveCoordinates.y,
            move.moveCoordinates.x
        )
    }

}