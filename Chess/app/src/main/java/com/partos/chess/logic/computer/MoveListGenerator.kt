package com.partos.chess.logic.computer

import com.partos.chess.logic.UserInteractionLogic
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.models.AIMove
import com.partos.chess.models.AdvancedMove
import com.partos.chess.models.Move
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup

class MoveListGenerator {

    fun generateMovesList(
        baseParametersGroup: BaseParametersGroup,
        turn: Int,
        userInteractionLogic: UserInteractionLogic,
        depth: Int
    ): ArrayList<AdvancedMove> {
        var actualTurn = turn
        val firstLayerMoves = getFirstLayerMoves(baseParametersGroup, turn, userInteractionLogic)
        actualTurn = changeTurn(actualTurn)
        val moves = ArrayList<AdvancedMove>()
        moves.addAll(firstLayerMoves)
        for (i in 0 until depth) {
            val tempMoves = ArrayList<AdvancedMove>()
            tempMoves.addAll(moves)
            for (move in tempMoves) {
                val newMoves = getDepthLayerMoves(
                    move.baseParameters,
                    actualTurn,
                    turn,
                    move.userInteractionLogic,
                    move.movesList
                )
                if (newMoves.size != 0) {
                    moves.remove(move)
                    moves.addAll(newMoves)
                }
                actualTurn = changeTurn(actualTurn)
            }
        }
        return moves
    }

    private fun getDepthLayerMoves(
        baseParametersGroup: BaseParametersGroup,
        actualTurn: Int,
        turn: Int,
        userInteractionLogic: UserInteractionLogic,
        movesList: ArrayList<AIMove>
    ): ArrayList<AdvancedMove> {
        val availablePieces = getAvailablePieces(baseParametersGroup, actualTurn)
        val availableMoves = getAvailableMoves(availablePieces, baseParametersGroup)
        val aIMoves =
            generateDepthLayerMoves(availableMoves, baseParametersGroup, userInteractionLogic, actualTurn, turn, movesList)
        return aIMoves
    }

    private fun generateDepthLayerMoves(
        availableMoves: ArrayList<Move>,
        baseParametersGroup: BaseParametersGroup,
        userInteractionLogic: UserInteractionLogic,
        actualTurn: Int,
        turn: Int,
        movesList: ArrayList<AIMove>
    ): ArrayList<AdvancedMove> {
        val aiMoves = ArrayList<AdvancedMove>()
        for (move in availableMoves) {
            baseParametersGroup.pieceParameters.piece = move.piece
            aiMoves.add(generateDepthLayerMoveValue(move, userInteractionLogic, actualTurn, turn, movesList, baseParametersGroup))
        }

        return aiMoves
    }

    private fun generateDepthLayerMoveValue(
        move: Move,
        userInteractionLogic: UserInteractionLogic,
        actualTurn: Int,
        turn: Int,
        movesList: ArrayList<AIMove>,
        baseParameters: BaseParametersGroup
    ): AdvancedMove {
        val userIL = UserInteractionLogic().makeCopy(userInteractionLogic)
        userIL.setBaseParameters(baseParameters)
        val baseParams = userIL.simulateMove(move)
        val listOfMoves = movesList.clone() as ArrayList<AIMove>
        val moveValue = if (actualTurn == turn) {
            MoveValueCalculator().calculateMoveValue(baseParams, turn)
        } else {
            -1 * MoveValueCalculator().calculateMoveValue(baseParams, turn)
        }
        listOfMoves.add(
            AIMove(
                move.piece,
                move.positionY,
                move.positionX,
                moveValue
            )
        )
        return AdvancedMove(
            listOfMoves,
            userIL,
            userIL.createBaseParametersGroup()
        )
    }


    private fun getFirstLayerMoves(
        baseParametersGroup: BaseParametersGroup,
        turn: Int,
        userInteractionLogic: UserInteractionLogic
    ): ArrayList<AdvancedMove> {
        val availablePieces = getAvailablePieces(baseParametersGroup, turn)
        val availableMoves = getAvailableMoves(availablePieces, baseParametersGroup)
        val aIMoves =
            generateFirstLayerMoves(availableMoves, baseParametersGroup, userInteractionLogic, turn)
        return aIMoves
    }

    private fun generateFirstLayerMoves(
        availableMoves: ArrayList<Move>,
        baseParametersGroup: BaseParametersGroup,
        userInteractionLogic: UserInteractionLogic,
        turn: Int
    ): ArrayList<AdvancedMove> {
        val aiMoves = ArrayList<AdvancedMove>()
        for (move in availableMoves) {
            baseParametersGroup.pieceParameters.piece = move.piece
            aiMoves.add(generateFirstLayerMoveValue(move, userInteractionLogic, turn))
        }

        return aiMoves
    }

    private fun generateFirstLayerMoveValue(
        move: Move,
        userInteractionLogic: UserInteractionLogic,
        turn: Int
    ): AdvancedMove {
        val userIL = UserInteractionLogic().makeCopy(userInteractionLogic)
        val baseParams = userIL.simulateMove(move)
        val movesArray = ArrayList<AIMove>()
        movesArray.add(
            AIMove(
                move.piece,
                move.positionY,
                move.positionX,
                MoveValueCalculator().calculateMoveValue(baseParams, turn)
            )
        )
        return AdvancedMove(
            movesArray,
            userIL,
            userIL.createBaseParametersGroup()
        )
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

    private fun changeTurn(turn: Int): Int {
        return if (turn == 0) {
            1
        } else {
            0
        }
    }
}