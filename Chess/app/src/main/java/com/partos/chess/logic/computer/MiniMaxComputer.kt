package com.partos.chess.logic.computer

import android.os.Handler
import com.partos.chess.logic.helpers.piecesHelpers.PiecesBoardHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesEnumHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.models.*
import com.partos.chess.models.parameters.MovesAndFlags
import kotlin.random.Random

class MiniMaxComputer {

    fun getBestMove(
        gameDescription: GameDescription,
        depth: Int,
        turn: Int,
        piecesList: ArrayList<Piece>
    ): Move {
        val moves = getAllMoves(gameDescription, turn)
        val rand = Random.nextInt(0, moves.size)
        val chosenMove = moves[rand]
        val piece = PiecesHelper().findPiece(
            chosenMove.pieceCoordinates.y,
            chosenMove.pieceCoordinates.x,
            piecesList
        )
        return Move(
            piece,
            chosenMove.moveCoordinates.y,
            chosenMove.moveCoordinates.x
        )
    }

    private fun getAllMoves(gameDescription: GameDescription, turn: Int): ArrayList<BoardMove> {
        val board = gameDescription.board
        val boardMoves = ArrayList<BoardMove>()
        if (turn == 0) {
            for (i in 0..7) {
                for (j in 0..7) {
                    if (PiecesEnumHelper().isWhite(board[i][j])) {
                        val moves = PiecesBoardHelper().getPieceMoves(gameDescription, i, j)
                        val pieceMoves = getPieceMoves(moves, i, j)
                        boardMoves.addAll(pieceMoves)
                    }
                }
            }
        } else {
            for (i in 0..7) {
                for (j in 0..7) {
                    if (PiecesEnumHelper().isBlack(board[i][j])) {
                        val moves = PiecesBoardHelper().getPieceMoves(gameDescription, i, j)
                        val pieceMoves = getPieceMoves(moves, i, j)
                        boardMoves.addAll(pieceMoves)
                    }
                }
            }
        }
        return boardMoves
    }

    private fun getPieceMoves(
        moves: MovesAndFlags,
        pieceY: Int,
        pieceX: Int
    ): ArrayList<BoardMove> {
        val boardMoves = ArrayList<BoardMove>()
        for (i in 0..7) {
            for (j in 0..7) {
                if (moves.moves[i][j]) {
                    boardMoves.add(
                        BoardMove(
                            Coordinates(
                                pieceX,
                                pieceY
                            ),
                            Coordinates(
                                j,
                                i
                            )
                        )
                    )
                }
            }
        }
        return boardMoves
    }


}