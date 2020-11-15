package com.partos.chess.logic.computer

import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.models.Move
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import kotlin.random.Random

class RandomMoveComputer {

    fun makeRandomMove(
        baseParametersGroup: BaseParametersGroup,
        turn: Int
    ): Move {
        val availablePieces = getAvailablePieces(baseParametersGroup, turn)
        val chosenPiece = chooseRandomPiece(availablePieces, baseParametersGroup)

        baseParametersGroup.pieceParameters.piece = chosenPiece

        val moves = PiecesHelper().showPieceMoves(baseParametersGroup).moves

        return chooseRandomMove(moves, chosenPiece)
    }

    private fun chooseRandomMove(moves: Array<Array<Boolean>>, chosenPiece: Piece): Move {
        var randX: Int
        var randY: Int
        do {
            randX = Random.nextInt(0, 8)
            randY = Random.nextInt(0, 8)
        } while (!moves[randY][randX])
        return Move(chosenPiece, randY, randX)
    }

    private fun chooseRandomPiece(
        availablePieces: ArrayList<Piece>,
        baseParametersGroup: BaseParametersGroup
    ): Piece {
        var piece: Piece
        do {
            piece = availablePieces.get(Random.nextInt(0, availablePieces.size))
        } while (!canPieceMove(piece, baseParametersGroup))
        return piece
    }

    private fun canPieceMove(piece: Piece, baseParametersGroup: BaseParametersGroup): Boolean {
        baseParametersGroup.pieceParameters.piece = piece
        val moves = PiecesHelper().showPieceMoves(baseParametersGroup).moves
        return MovesHelper().checkIsAnyMovePossible(moves)
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