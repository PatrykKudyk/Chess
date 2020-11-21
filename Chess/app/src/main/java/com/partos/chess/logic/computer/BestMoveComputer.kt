package com.partos.chess.logic.computer

import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.models.AIMove
import com.partos.chess.models.Move
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.ComputerMoveParameters
import kotlin.random.Random

class BestMoveComputer {

    fun makeBestMove(
        baseParametersGroup: BaseParametersGroup,
        turn: Int,
        deep: Int
    ): Move {
        val availablePieces = getAvailablePieces(baseParametersGroup, turn)
        val availableMoves = getAvailableMoves(availablePieces, baseParametersGroup)
        val aIMoves = generateBestMoves(availableMoves, baseParametersGroup, deep)
        val bestMoveValue = getBestMoveValue(aIMoves)
        val bestMoves = getBestMoves(bestMoveValue, aIMoves)
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
        deep: Int
    ): ArrayList<AIMove> {
        val aiMoves = ArrayList<AIMove>()
        for (move in availableMoves) {
            baseParametersGroup.pieceParameters.piece = move.piece
            aiMoves.add(generateMoveValue(move, baseParametersGroup))
        }

        return aiMoves
    }

    private fun generateMoveValue(move: Move, baseParametersGroup: BaseParametersGroup): AIMove {
        if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[move.positionY][move.positionX])) {
            return AIMove(
                move.piece,
                move.positionY,
                move.positionX,
                getPieceValue(
                    PiecesHelper().findPiece(
                        move.positionY,
                        move.positionX,
                        baseParametersGroup.pieceParameters.piecesList
                    )
                )
            )
        }
        return AIMove(
            move.piece,
            move.positionY,
            move.positionX,
            0
        )
    }

    private fun getPieceValue(piece: Piece): Int {
        when (piece.type) {
            0 -> return 10
            1, 2 -> return 30
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