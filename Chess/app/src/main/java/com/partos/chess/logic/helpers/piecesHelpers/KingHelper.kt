package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.GameFlags
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.PieceAfterMoveParameters
import com.partos.chess.models.parameters.PieceParameters
import kotlin.math.abs

class KingHelper {

    fun showKingMoves(
        pieceParams: PieceParameters,
        gameFlags: GameFlags
    ): PieceAfterMoveParameters {
        val returnParams = PieceAfterMoveParameters(
            MovesHelper().createMovesList(),
            isChoose = false,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false
        )
        val color = if (pieceParams.piece.color == 0) {
            1
        } else {
            0
        }
        if (pieceParams.piece.positionY >= 1) {
            if (canKingMove(
                    color,
                    pieceParams.piece.positionY - 1,
                    pieceParams.piece.positionX,
                    pieceParams.piece,
                    pieceParams
                )
            ) {
                returnParams.moves[pieceParams.piece.positionY - 1][pieceParams.piece.positionX] =
                    true
            }
        }
        if (pieceParams.piece.positionY >= 1 && pieceParams.piece.positionX <= 6) {
            if (canKingMove(
                    color,
                    pieceParams.piece.positionY - 1,
                    pieceParams.piece.positionX + 1,
                    pieceParams.piece,
                    pieceParams
                )
            ) {
                returnParams.moves[pieceParams.piece.positionY - 1][pieceParams.piece.positionX + 1] =
                    true
            }
        }
        if (pieceParams.piece.positionX <= 6) {
            if (canKingMove(
                    color,
                    pieceParams.piece.positionY,
                    pieceParams.piece.positionX + 1,
                    pieceParams.piece,
                    pieceParams
                )
            ) {
                returnParams.moves[pieceParams.piece.positionY][pieceParams.piece.positionX + 1] =
                    true
            }
        }
        if (pieceParams.piece.positionY <= 6 && pieceParams.piece.positionX <= 6) {
            if (canKingMove(
                    color,
                    pieceParams.piece.positionY + 1,
                    pieceParams.piece.positionX + 1,
                    pieceParams.piece,
                    pieceParams
                )
            ) {
                returnParams.moves[pieceParams.piece.positionY + 1][pieceParams.piece.positionX + 1] =
                    true
            }
        }
        if (pieceParams.piece.positionY <= 6) {
            if (canKingMove(
                    color,
                    pieceParams.piece.positionY + 1,
                    pieceParams.piece.positionX,
                    pieceParams.piece,
                    pieceParams
                )
            ) {
                returnParams.moves[pieceParams.piece.positionY + 1][pieceParams.piece.positionX] =
                    true
            }
        }
        if (pieceParams.piece.positionY <= 6 && pieceParams.piece.positionX >= 1) {
            if (canKingMove(
                    color,
                    pieceParams.piece.positionY + 1,
                    pieceParams.piece.positionX - 1,
                    pieceParams.piece,
                    pieceParams
                )
            ) {
                returnParams.moves[pieceParams.piece.positionY + 1][pieceParams.piece.positionX - 1] =
                    true
            }
        }
        if (pieceParams.piece.positionX >= 1) {
            if (canKingMove(
                    color,
                    pieceParams.piece.positionY,
                    pieceParams.piece.positionX - 1,
                    pieceParams.piece,
                    pieceParams
                )
            ) {
                returnParams.moves[pieceParams.piece.positionY][pieceParams.piece.positionX - 1] =
                    true
            }
        }
        if (pieceParams.piece.positionY >= 1 && pieceParams.piece.positionX >= 1) {
            if (canKingMove(
                    color,
                    pieceParams.piece.positionY - 1,
                    pieceParams.piece.positionX - 1,
                    pieceParams.piece,
                    pieceParams
                )
            ) {
                returnParams.moves[pieceParams.piece.positionY - 1][pieceParams.piece.positionX - 1] =
                    true
            }
        }
        if (pieceParams.piece.color == 0) {
            if (canWhiteKingMakeLongCastle(gameFlags, pieceParams)) {
                returnParams.moves[7][2] = true
                returnParams.longWhiteCastleAvailable = true
            }
            if (canWhiteKingMakeShortCastle(gameFlags, pieceParams)) {
                returnParams.moves[7][6] = true
                returnParams.shortWhiteCastleAvailable = true
            }
        } else {
            if (canBlackKingMakeLongCastle(gameFlags, pieceParams)) {
                returnParams.moves[0][2] = true
                returnParams.longBlackCastleAvailable = true
            }
            if (canBlackKingMakeShortCastle(gameFlags, pieceParams)) {
                returnParams.moves[0][6] = true
                returnParams.shortBlackCastleAvailable = true
            }
        }
        return returnParams
    }

    private fun canKingMove(
        color: Int,
        positionY: Int,
        positionX: Int,
        pieceFocused: Piece,
        pieceParams: PieceParameters
    ): Boolean {
        if (!isOtherKingTooClose(positionY, positionX, color, pieceParams)) {
            val king: Piece = pieceFocused.copy()
            king.positionY = positionY
            king.positionX = positionX
            if (BoardHelper().isPiece(pieceParams.board[positionY][positionX])) {
                val piece = PiecesHelper().findPiece(positionY, positionX, pieceParams.piecesList)
                if (piece.color == color) {
                    val piecesListCopy = pieceParams.piecesList.toMutableList() as ArrayList<Piece>
                    piecesListCopy.set(
                        piecesListCopy.indexOf(pieceFocused),
                        Piece(
                            pieceFocused.type,
                            pieceFocused.color,
                            king.positionX,
                            king.positionY,
                            pieceFocused.isActive
                        )
                    )
                    piecesListCopy.set(
                        piecesListCopy.indexOf(
                            PiecesHelper().findPiece(
                                positionY,
                                positionX,
                                pieceParams.piecesList
                            )
                        ),
                        Piece(
                            0,
                            0,
                            0,
                            0,
                            false
                        )
                    )
                    val boardCopy = pieceParams.board.clone()
                    BoardHelper().resetBoard(piecesListCopy, boardCopy, pieceParams.context)
                    if (PiecesHelper().isCheck(piecesListCopy, pieceFocused.color, boardCopy)) {
                        BoardHelper().resetBoard(
                            pieceParams.piecesList,
                            pieceParams.board,
                            pieceParams.context
                        )
                        return false
                    } else {
                        BoardHelper().resetBoard(
                            pieceParams.piecesList,
                            pieceParams.board,
                            pieceParams.context
                        )
                        return true
                    }
                } else {
                    return false
                }
            } else {
                val piecesListCopy = pieceParams.piecesList.toMutableList() as ArrayList<Piece>
                piecesListCopy.set(
                    piecesListCopy.indexOf(pieceFocused),
                    Piece(
                        pieceFocused.type,
                        pieceFocused.color,
                        king.positionX,
                        king.positionY,
                        pieceFocused.isActive
                    )
                )
                val boardCopy = pieceParams.board.clone()
                BoardHelper().resetBoard(piecesListCopy, boardCopy, pieceParams.context)
                if (PiecesHelper().isCheck(piecesListCopy, pieceFocused.color, boardCopy)) {
                    BoardHelper().resetBoard(
                        pieceParams.piecesList,
                        pieceParams.board,
                        pieceParams.context
                    )
                    return false
                } else {
                    BoardHelper().resetBoard(
                        pieceParams.piecesList,
                        pieceParams.board,
                        pieceParams.context
                    )
                    return true
                }
            }
        } else {
            return false
        }
    }

    private fun canBlackKingMakeShortCastle(
        gameFlags: GameFlags,
        pieceParams: PieceParameters
    ): Boolean {
        if (gameFlags.canCastleShortBlack && areNoPiecesInBlackKingShortCastle(pieceParams)) {
            if (!isBlackKingCheckAtShortCastle(pieceParams)) {
                return true
            }
        }
        return false
    }

    private fun isOtherKingTooClose(
        positionY: Int,
        positionX: Int,
        color: Int,
        pieceParams: PieceParameters
    ): Boolean {
        val otherKing = findKing(color, pieceParams.piecesList)
        if (abs(otherKing.positionX - positionX) <= 1) {
            if (abs(otherKing.positionY - positionY) <= 1) {
                return true
            }
        }
        return false
    }

    fun findKing(color: Int, piecesList: ArrayList<Piece>): Piece {
        for (piece in piecesList) {
            if (piece.isActive) {
                if (piece.type == 5 && piece.color == color) {
                    return piece
                }
            }
        }
        return Piece(0, 0, 0, 0, false)
    }

    private fun isBlackKingCheckAtShortCastle(pieceParams: PieceParameters): Boolean {
        val king = findKing(1, pieceParams.piecesList)
        val pieces1 = pieceParams.piecesList.clone() as ArrayList<Piece>
        val pieces2 = pieceParams.piecesList.clone() as ArrayList<Piece>
        pieces1.set(
            pieces1.indexOf(king),
            Piece(
                king.type,
                king.color,
                5,
                0,
                true
            )
        )
        pieces2.set(
            pieces2.indexOf(king),
            Piece(
                king.type,
                king.color,
                6,
                0,
                true
            )
        )
        if (isBlackCheck(pieceParams)) {
            return true
        } else if (isBlackCheck(pieces1, pieceParams)) {
            return true
        } else if (isBlackCheck(pieces2, pieceParams)) {
            return true
        }
        return false
    }

    private fun areNoPiecesInBlackKingShortCastle(pieceParams: PieceParameters): Boolean {
        return !BoardHelper().isPiece(pieceParams.board[0][5]) && !BoardHelper().isPiece(pieceParams.board[0][6])
    }

    private fun canBlackKingMakeLongCastle(
        gameFlags: GameFlags,
        pieceParams: PieceParameters
    ): Boolean {
        if (gameFlags.canCastleLongBlack && areNoPiecesInBlackKingLongCastle(pieceParams)) {
            if (!isBlackKingCheckAtLongCastle(pieceParams)) {
                return true
            }
        }
        return false
    }

    private fun isBlackKingCheckAtLongCastle(pieceParams: PieceParameters): Boolean {
        val king = findKing(1, pieceParams.piecesList)
        val pieces1 = pieceParams.piecesList.clone() as ArrayList<Piece>
        val pieces2 = pieceParams.piecesList.clone() as ArrayList<Piece>
        pieces1.set(
            pieces1.indexOf(king),
            Piece(
                king.type,
                king.color,
                3,
                0,
                true
            )
        )
        pieces2.set(
            pieces2.indexOf(king),
            Piece(
                king.type,
                king.color,
                2,
                0,
                true
            )
        )
        if (isBlackCheck(pieceParams)) {
            return true
        } else if (isBlackCheck(pieces1, pieceParams)) {
            return true
        } else if (isBlackCheck(pieces2, pieceParams)) {
            return true
        }
        return false
    }

    private fun areNoPiecesInBlackKingLongCastle(pieceParams: PieceParameters): Boolean {
        return !BoardHelper().isPiece(pieceParams.board[0][3]) && !BoardHelper().isPiece(pieceParams.board[0][2]) && !BoardHelper().isPiece(
            pieceParams.board[0][1]
        )
    }

    private fun canWhiteKingMakeShortCastle(
        gameFlags: GameFlags,
        pieceParams: PieceParameters
    ): Boolean {
        if (gameFlags.canCastleShortWhite && areNoPiecesInWhiteKingShortCastle(pieceParams)) {
            if (!isWhiteKingCheckAtShortCastle(pieceParams)) {
                return true
            }
        }
        return false
    }

    private fun isWhiteKingCheckAtShortCastle(pieceParams: PieceParameters): Boolean {
        val king = findKing(0, pieceParams.piecesList)
        val pieces1 = pieceParams.piecesList.clone() as ArrayList<Piece>
        val pieces2 = pieceParams.piecesList.clone() as ArrayList<Piece>
        pieces1.set(
            pieces1.indexOf(king),
            Piece(
                king.type,
                king.color,
                5,
                7,
                true
            )
        )
        pieces2.set(
            pieces2.indexOf(king),
            Piece(
                king.type,
                king.color,
                6,
                7,
                true
            )
        )
        if (isWhiteCheck(pieceParams)) {
            return true
        } else if (isWhiteCheck(pieces1, pieceParams)) {
            return true
        } else if (isWhiteCheck(pieces2, pieceParams)) {
            return true
        }
        return false
    }

    private fun areNoPiecesInWhiteKingShortCastle(pieceParams: PieceParameters): Boolean {
        return !BoardHelper().isPiece(pieceParams.board[7][5]) && !BoardHelper().isPiece(pieceParams.board[7][6])
    }

    private fun canWhiteKingMakeLongCastle(
        gameFlags: GameFlags,
        pieceParams: PieceParameters
    ): Boolean {
        if (gameFlags.canCastleLongWhite && areNoPiecesInWhiteKingLongCastle(pieceParams)) {
            if (!isWhiteKingCheckAtLongCastle(pieceParams)) {
                return true
            }
        }
        return false
    }

    private fun areNoPiecesInWhiteKingLongCastle(pieceParams: PieceParameters): Boolean {
        return !BoardHelper().isPiece(pieceParams.board[7][3]) && !BoardHelper().isPiece(pieceParams.board[7][2]) &&
                !BoardHelper().isPiece(pieceParams.board[7][1])
    }

    private fun isWhiteKingCheckAtLongCastle(pieceParams: PieceParameters): Boolean {
        val king = findKing(0, pieceParams.piecesList)
        val pieces1 = pieceParams.piecesList.clone() as ArrayList<Piece>
        val pieces2 = pieceParams.piecesList.clone() as ArrayList<Piece>
        pieces1.set(
            pieces1.indexOf(king),
            Piece(
                king.type,
                king.color,
                3,
                7,
                true
            )
        )
        pieces2.set(
            pieces2.indexOf(king),
            Piece(
                king.type,
                king.color,
                2,
                7,
                true
            )
        )
        if (isWhiteCheck(pieceParams)) {
            return true
        } else if (isWhiteCheck(pieces1, pieceParams)) {
            return true
        } else if (isWhiteCheck(pieces2, pieceParams)) {
            return true
        }
        return false
    }

    private fun isBlackCheck(pieceParams: PieceParameters): Boolean {
        return PiecesHelper().isCheck(pieceParams.piecesList, 1, pieceParams.board)
    }

    private fun isBlackCheck(piecesGiven: ArrayList<Piece>, pieceParams: PieceParameters): Boolean {
        return PiecesHelper().isCheck(piecesGiven, 1, pieceParams.board)
    }

    private fun isWhiteCheck(pieceParams: PieceParameters): Boolean {
        return PiecesHelper().isCheck(pieceParams.piecesList, 0, pieceParams.board)
    }

    private fun isWhiteCheck(piecesGiven: ArrayList<Piece>, pieceParams: PieceParameters): Boolean {
        return PiecesHelper().isCheck(piecesGiven, 0, pieceParams.board)
    }

    fun checkIfKingHasMoves(pieceParams: PieceParameters, gameFlags: GameFlags): Boolean {
        val moves = showKingMoves(pieceParams, gameFlags).moves
        return MovesHelper().checkIsAnyMovePossible(moves)
    }

}