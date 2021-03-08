package com.partos.chess.models

import com.partos.chess.MyApp
import com.partos.chess.logic.computer.BoardMoveValueCalculator
import com.partos.chess.logic.helpers.piecesHelpers.PiecesBoardHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesEnumHelper
import com.partos.chess.models.parameters.MovesAndFlags
import kotlinx.coroutines.*

class MinMaxNode(
    val value: Int,
    val boardMove: BoardMove,
    var moves: ArrayList<MinMaxNode>,
    val turn: Int,
    val depth: Int
) {

    fun leaveBestMoves() {

    }

    fun leaveWorstMoves() {

    }

    fun createMovesTree() {
        if (depth <= 0)
            return
        val oppositeTurn = if (turn == 0) {
            1
        } else {
            0
        }
        moves = createBaseMoves(boardMove.gameDescription, oppositeTurn, depth - 1)
        for (move in moves) {
//            if (!MyApp.searchedNodes.contains(move.boardMove.gameDescription.toString()))
            move.createMovesTree()
        }
    }

    private fun createBaseMoves(
        gameDescription: GameDescription,
        givenTurn: Int,
        givenDepth: Int
    ): ArrayList<MinMaxNode>{
        var nodesList: ArrayList<MinMaxNode>
        if (turn == 0) {
            nodesList =
                createWhiteBaseMoves(
                    gameDescription,
                    givenTurn,
                    givenDepth
                )

        } else {
            nodesList =
                createBlackBaseMoves(
                    gameDescription,
                    givenTurn,
                    givenDepth
                )

        }
        return nodesList
    }

    private fun createWhiteBaseMoves(
        gameDescription: GameDescription,
        givenTurn: Int,
        givenDepth: Int
    ): ArrayList<MinMaxNode> = runBlocking {
        val nodesList = ArrayList<MinMaxNode>()
        val givenBoard = gameDescription.board

        val requests = ArrayList<Deferred<ArrayList<MinMaxNode>>>()

        for (i in 0..7) {
            for (j in 0..7) {
                if (!PiecesEnumHelper().isWhite(givenBoard[i][j]))
                    continue

                val result =
                    async (Dispatchers.Default) { createMinMaxNode(gameDescription, givenTurn, givenDepth, i, j) }
                requests.add(result)
            }
        }
        requests.awaitAll()
        for (request in requests) {
            nodesList.addAll(request.getCompleted())
        }


        return@runBlocking nodesList
    }

    private fun createBlackBaseMoves(
        gameDescription: GameDescription,
        givenTurn: Int,
        givenDepth: Int
    ): ArrayList<MinMaxNode> = runBlocking {
        val nodesList = ArrayList<MinMaxNode>()
        val givenBoard = gameDescription.board

        val requests = ArrayList<Deferred<ArrayList<MinMaxNode>>>()

        for (i in 0..7) {
            for (j in 0..7) {
                if (!PiecesEnumHelper().isBlack(givenBoard[i][j]))
                    continue

                val result =
                    async (Dispatchers.Default) { createMinMaxNode(gameDescription, givenTurn, givenDepth, i, j) }
                requests.add(result)
            }
        }
        requests.awaitAll()
        for (request in requests) {
            nodesList.addAll(request.getCompleted())
        }


        return@runBlocking nodesList
    }

    private fun createMinMaxNode(
        gameDescription: GameDescription,
        givenTurn: Int,
        givenDepth: Int,
        y: Int,
        x: Int
    ): ArrayList<MinMaxNode> {
        val nodesList = ArrayList<MinMaxNode>()
        val moves = PiecesBoardHelper().getPieceMoves(gameDescription, y, x)
        val pieceMoves = getPieceMoves(moves, y, x, gameDescription)
        for (move in pieceMoves) {
            nodesList.add(
                MinMaxNode(
                    BoardMoveValueCalculator().calculateMoveValue(
                        move.gameDescription,
                        givenTurn
                    ),
                    move,
                    ArrayList(),
                    givenTurn,
                    givenDepth
                )
            )
        }
        return nodesList
    }


    private fun getPieceMoves(
        givenMoves: MovesAndFlags,
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): ArrayList<BoardMove> {
        val boardMoves = ArrayList<BoardMove>()
        for (i in 0..7) {
            for (j in 0..7) {
                if (givenMoves.moves[i][j]) {
                    val givenBoard = PiecesBoardHelper().getBoardAfterMakingMove(
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
                                givenBoard,
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