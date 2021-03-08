package com.partos.chess.logic.computer

import com.partos.chess.logic.helpers.piecesHelpers.PiecesBoardHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesEnumHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.models.*
import com.partos.chess.models.parameters.MovesAndFlags

class MiniMaxComputer {

    fun getBestMove(
        gameDescription: GameDescription,
        depth: Int,
        turn: Int,
        piecesList: ArrayList<Piece>
    ): Move {
//        MyApp.searchedNodes.clear()
        val baseMoves = createBaseMoves(gameDescription, turn, depth)
//        for (move in baseMoves){
//            MyApp.searchedNodes.add(move.boardMove.gameDescription.toString())
//        }

        for (move in baseMoves){
            move.createMovesTree()
        }
        val chosenMove = getBestMoveFromNodes(baseMoves, depth)
//        val random = Random.nextInt(0, baseMoves.size)
//        val chosenMove = baseMoves[random]
        val piece = PiecesHelper().findPiece(
            chosenMove.boardMove.pieceCoordinates.y,
            chosenMove.boardMove.pieceCoordinates.x,
            piecesList
        )
        return Move(
            piece,
            chosenMove.boardMove.moveCoordinates.y,
            chosenMove.boardMove.moveCoordinates.x
        )
    }

    private fun getBestMoveFromNodes(baseMoves: ArrayList<MinMaxNode>, depth: Int): MinMaxNode {

    }

    private fun createBaseMoves(
        gameDescription: GameDescription,
        turn: Int,
        depth: Int
    ): ArrayList<MinMaxNode> {
        val nodesList = ArrayList<MinMaxNode>()
        val board = gameDescription.board
        if (turn == 0) {
            for (i in 0..7) {
                for (j in 0..7) {
                    if (PiecesEnumHelper().isWhite(board[i][j])) {
                        val moves = PiecesBoardHelper().getPieceMoves(gameDescription, i, j)
                        val pieceMoves = getPieceMoves(moves, i, j, gameDescription)
                        for (move in pieceMoves) {
                            nodesList.add(
                                MinMaxNode(
                                    BoardMoveValueCalculator().calculateMoveValue(move.gameDescription, turn),
                                    move,
                                    ArrayList(),
                                    turn,
                                    depth
                                )
                            )
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
                            nodesList.add(
                                MinMaxNode(
                                    BoardMoveValueCalculator().calculateMoveValue(move.gameDescription, turn),
                                    move,
                                    ArrayList(),
                                    turn,
                                    depth
                                )
                            )
                        }
                    }
                }
            }
        }
        return nodesList
    }

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