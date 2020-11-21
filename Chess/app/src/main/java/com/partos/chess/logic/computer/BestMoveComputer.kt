package com.partos.chess.logic.computer

import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.models.AIMove
import com.partos.chess.models.Move
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.ComputerMoveParameters
import com.partos.chess.models.parameters.PieceAfterMoveParameters
import com.partos.chess.models.parameters.PieceParameters
import kotlin.random.Random

class BestMoveComputer {

    fun makeBestMove(
        baseParametersGroup: BaseParametersGroup,
        turn: Int,
        deep: Int
    ): ComputerMoveParameters {
        val availablePieces = getAvailablePieces(baseParametersGroup, turn)
        val aIMoves = generateBestMoves(availablePieces, baseParametersGroup, deep)
        val bestMoveValue = getBestMoveValue(aIMoves)
        val bestMoves = getBestMoves(bestMoveValue, aIMoves)
        val move = getRandomBestMove(bestMoves)
        return move
    }

    private fun getRandomBestMove(bestMoves: ArrayList<AIMove>): ComputerMoveParameters {
        val random = Random.nextInt(0, bestMoves.size)
        return ComputerMoveParameters(
            bestMoves[random].pieceAfterMoveParameters,
            Move(
                bestMoves[random].piece,
                bestMoves[random].positionY,
                bestMoves[random].positionX
            )
        )
    }

    private fun generateBestMoves(
        availablePieces: ArrayList<Piece>,
        baseParametersGroup: BaseParametersGroup,
        deep: Int
    ): ArrayList<AIMove> {
        val aiMoves = ArrayList<AIMove>()
        for (piece in availablePieces) {
            baseParametersGroup.pieceParameters.piece = piece
            val moves = PiecesHelper().showPieceMoves(baseParametersGroup)
            val movesWithValues = generateMovesValues(moves, baseParametersGroup.pieceParameters)
            aiMoves.addAll(movesWithValues)
        }

        return aiMoves
    }

    private fun generateMovesValues(params: PieceAfterMoveParameters, pieceParameters: PieceParameters): ArrayList<AIMove> {
        val aiMoves = ArrayList<AIMove>()
        for (i in 0..7){
            for (j in 0..7){
                if (params.moves[i][j]) {
                    if (BoardHelper().isPiece(pieceParameters.board[i][j])) {
                        aiMoves.add(AIMove(
                            pieceParameters.piece,
                            i,
                            j,
                            getPieceValue(PiecesHelper().findPiece(i, j, pieceParameters.piecesList)),
                            params
                        ))
                    } else {
                        aiMoves.add(AIMove(
                            pieceParameters.piece,
                            i,
                            j,
                            0,
                            params
                        ))
                    }
                }
            }
        }
        return aiMoves
    }

    private fun getPieceValue(piece: Piece): Int {
        when (piece.type) {
            0 -> return 10
            1,2 -> return 30
            3 -> return 50
            4 -> return 90
            5 -> return 900
        }
        return 0
    }

    private fun getBestMoveValue(aIMoves: java.util.ArrayList<AIMove>): Int {
        var bestValue = 0
        for (move in aIMoves) {
            if (move.gain > bestValue) {
                bestValue = move.gain
            }
        }
        return bestValue
    }

    private fun getBestMoves(bestMoveValue: Int, aIMoves: ArrayList<AIMove>): ArrayList<AIMove> {
        val aiMoves = ArrayList<AIMove>()
        for (move in aIMoves) {
            if (move.gain == bestMoveValue) {
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