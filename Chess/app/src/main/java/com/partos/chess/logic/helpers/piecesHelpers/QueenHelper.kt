package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.GameDescription
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.MovesAndFlags

class QueenHelper {

    fun showQueenMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        var moves = MovesHelper().mergeMovesLists(
            MovesHelper().createMovesList(),
            BishopHelper().showBishopMoves(baseParametersGroup)
        )
        moves = MovesHelper().mergeMovesLists(
            moves,
            RookHelper().showRookMoves(baseParametersGroup)
        )
        return moves
    }

    fun showWhiteQueenMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        var moves = MovesHelper().createMovesList()

        moves = MovesHelper().mergeMovesLists(
            moves,
            BishopHelper().showWhiteBishopMoves(pieceY, pieceX, gameDescription).moves
        )

        moves = MovesHelper().mergeMovesLists(
            moves,
            RookHelper().showWhiteRookMoves(pieceY, pieceX, gameDescription).moves
        )

        return MovesAndFlags(
            moves,
            gameDescription.gameFlags
        )
    }

    fun showBlackQueenMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        var moves = MovesHelper().createMovesList()

        moves = MovesHelper().mergeMovesLists(
            moves,
            BishopHelper().showBlackBishopMoves(pieceY, pieceX, gameDescription).moves
        )

        moves = MovesHelper().mergeMovesLists(
            moves,
            RookHelper().showBlackRookMoves(pieceY, pieceX, gameDescription).moves
        )

        return MovesAndFlags(
            moves,
            gameDescription.gameFlags
        )
    }

    fun checkQueenMoves(baseParametersGroup: BaseParametersGroup): Array<Array<Boolean>> {
        var moves = MovesHelper().mergeMovesLists(
            MovesHelper().createMovesList(),
            BishopHelper().checkBishopMoves(baseParametersGroup)
        )
        moves = MovesHelper().mergeMovesLists(
            moves,
            RookHelper().checkRookMoves(baseParametersGroup)
        )
        return moves
    }

    fun checkWhiteQueenMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        var moves = MovesHelper().createMovesList()

        moves = MovesHelper().mergeMovesLists(
            moves,
            BishopHelper().checkWhiteBishopMoves(pieceY, pieceX, gameDescription)
        )

        moves = MovesHelper().mergeMovesLists(
            moves,
            RookHelper().checkWhiteRookMoves(pieceY, pieceX, gameDescription)
        )

        return moves
    }

    fun checkBlackQueenMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        var moves = MovesHelper().createMovesList()

        moves = MovesHelper().mergeMovesLists(
            moves,
            BishopHelper().checkBlackBishopMoves(pieceY, pieceX, gameDescription)
        )

        moves = MovesHelper().mergeMovesLists(
            moves,
            RookHelper().checkBlackRookMoves(pieceY, pieceX, gameDescription)
        )

        return moves
    }

    fun findQueen(piecesList: ArrayList<Piece>, color: Int): Piece {
        for (piece in piecesList) {
            if (piece.type == 4 && piece.color == color) {
                return piece
            }
        }
        return Piece(0, 0, 0, 0, false)
    }
}