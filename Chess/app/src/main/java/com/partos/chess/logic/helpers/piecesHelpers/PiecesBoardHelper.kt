package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.enums.PieceType
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.Coordinates
import com.partos.chess.models.GameDescription
import com.partos.chess.models.parameters.MovesAndFlags
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class PiecesBoardHelper {

    fun getPieceMoves(
        gameDescription: GameDescription,
        pieceY: Int,
        pieceX: Int
    ): MovesAndFlags {
        lateinit var movesAndFlags: MovesAndFlags
        when (gameDescription.board[pieceY][pieceX]) {
            PieceType.WhitePawn -> {
                movesAndFlags = PawnHelper().getWhitePawnMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackPawn -> {
                movesAndFlags = PawnHelper().getBlackPawnMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.WhiteBishop -> {
                movesAndFlags = BishopHelper().showWhiteBishopMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackBishop -> {
                movesAndFlags = BishopHelper().showBlackBishopMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.WhiteKnight -> {
                movesAndFlags = KnightHelper().showWhiteKnightMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackKnight -> {
                movesAndFlags = KnightHelper().showBlackKnightMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.WhiteRook -> {
                movesAndFlags = RookHelper().showWhiteRookMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackRook -> {
                movesAndFlags = RookHelper().showBlackRookMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.WhiteQueen -> {
                movesAndFlags = QueenHelper().showWhiteQueenMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackQueen -> {
                movesAndFlags = QueenHelper().showBlackQueenMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.WhiteKing -> {
                movesAndFlags = KingHelper().showWhiteKingMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackKing -> {
                movesAndFlags = KingHelper().showBlackKingMoves(pieceY, pieceX, gameDescription)
            }
        }
        return movesAndFlags
    }

    private fun checkPieceMoves(
        gameDescription: GameDescription,
        pieceY: Int,
        pieceX: Int
    ): Array<Array<Boolean>> {
        lateinit var moves: Array<Array<Boolean>>
        when (gameDescription.board[pieceY][pieceX]) {
            PieceType.WhitePawn -> {
                moves = PawnHelper().checkWhitePawnMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackPawn -> {
                moves = PawnHelper().checkBlackPawnMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.WhiteBishop -> {
                moves = BishopHelper().checkWhiteBishopMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackBishop -> {
                moves = BishopHelper().checkBlackBishopMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.WhiteKnight -> {
                moves = KnightHelper().checkWhiteKnightMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackKnight -> {
                moves = KnightHelper().checkBlackKnightMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.WhiteRook -> {
                moves = RookHelper().checkWhiteRookMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackRook -> {
                moves = RookHelper().checkBlackRookMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.WhiteQueen -> {
                moves = QueenHelper().checkWhiteQueenMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackQueen -> {
                moves = QueenHelper().checkBlackQueenMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.WhiteKing -> {
                moves = KingHelper().checkWhiteKingMoves(pieceY, pieceX, gameDescription)
            }
            PieceType.BlackKing -> {
                moves = KingHelper().checkBlackKingMoves(pieceY, pieceX, gameDescription)
            }
        }
        return moves
    }

    fun canPieceMove(
        pieceY: Int,
        pieceX: Int,
        moveY: Int,
        moveX: Int,
        gameDescription: GameDescription,
        color: Int
    ): Boolean {
        if (color == 0 && PiecesEnumHelper().isWhite(gameDescription.board[moveY][moveX])) {
            return false
        } else if (color == 1 && PiecesEnumHelper().isBlack(gameDescription.board[moveY][moveX])) {
            return false
        }
        val boardAfterMove =
            getBoardAfterMakingMove(gameDescription.board, pieceY, pieceX, moveY, moveX)
        val gameDescriptionCopy = gameDescription.copy()
        gameDescriptionCopy.board = boardAfterMove
        return !isCheck(boardAfterMove, color, gameDescriptionCopy)
    }

    fun getBoardAfterMakingMove(
        board: Array<Array<PieceType>>,
        pieceY: Int,
        pieceX: Int,
        moveY: Int,
        moveX: Int
    ): Array<Array<PieceType>> {
        val boardToReturn = Array(8) { Array(8) { PieceType.Empty } }
        for (i in 0..7) {
            for (j in 0..7) {
                boardToReturn[i][j] = PiecesEnumHelper().getPieceType(board[i][j])
            }
        }
        boardToReturn[moveY][moveX] = PiecesEnumHelper().getPieceType(boardToReturn[pieceY][pieceX])
        boardToReturn[pieceY][pieceX] = PieceType.Empty
        return boardToReturn
    }

    private fun isCheck(
        board: Array<Array<PieceType>>,
        color: Int,
        gameDescription: GameDescription
    ): Boolean  {
        var moves = MovesHelper().createMovesList()
        var movesArraysList = ArrayList<Array<Array<Boolean>>>()
        lateinit var king: Coordinates
        for (i in 0..7) {
            for (j in 0..7) {
                if (color == 0 && board[i][j] == PieceType.WhiteKing)
                    king = Coordinates(j, i)
                else if (color == 1 && board[i][j] == PieceType.BlackKing)
                    king = Coordinates(j, i)
                if (color == 0 && PiecesEnumHelper().isBlack(board[i][j])) {
                    movesArraysList.add(checkPieceMoves(gameDescription, i, j))
                } else if (color == 1 && PiecesEnumHelper().isWhite(board[i][j])) {
                    movesArraysList.add(checkPieceMoves(gameDescription, i, j))
                }
            }
        }
        moves = MovesHelper().mergeMovesLists(moves, movesArraysList)
        return moves[king.y][king.x]
    }
}