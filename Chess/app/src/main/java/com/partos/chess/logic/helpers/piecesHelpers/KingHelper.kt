package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.enums.PieceType
import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.GameDescription
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.MovesAndFlags
import com.partos.chess.models.parameters.PieceAfterMoveParameters
import com.partos.chess.models.parameters.PieceParameters
import kotlin.math.abs

class KingHelper {

    fun showKingMoves(baseParametersGroup: BaseParametersGroup): PieceAfterMoveParameters {
        val returnParams = PieceAfterMoveParameters(
            MovesHelper().createMovesList(),
            isChoose = false,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false
        )
        val color = if (baseParametersGroup.pieceParameters.piece.color == 0) {
            1
        } else {
            0
        }
        if (baseParametersGroup.pieceParameters.piece.positionY >= 1) {
            if (canKingMove(
                    color,
                    baseParametersGroup.pieceParameters.piece.positionY - 1,
                    baseParametersGroup.pieceParameters.piece.positionX,
                    baseParametersGroup.pieceParameters.piece,
                    baseParametersGroup
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionY >= 1 && baseParametersGroup.pieceParameters.piece.positionX <= 6) {
            if (canKingMove(
                    color,
                    baseParametersGroup.pieceParameters.piece.positionY - 1,
                    baseParametersGroup.pieceParameters.piece.positionX + 1,
                    baseParametersGroup.pieceParameters.piece,
                    baseParametersGroup
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX + 1] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX <= 6) {
            if (canKingMove(
                    color,
                    baseParametersGroup.pieceParameters.piece.positionY,
                    baseParametersGroup.pieceParameters.piece.positionX + 1,
                    baseParametersGroup.pieceParameters.piece,
                    baseParametersGroup
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX + 1] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionY <= 6 && baseParametersGroup.pieceParameters.piece.positionX <= 6) {
            if (canKingMove(
                    color,
                    baseParametersGroup.pieceParameters.piece.positionY + 1,
                    baseParametersGroup.pieceParameters.piece.positionX + 1,
                    baseParametersGroup.pieceParameters.piece,
                    baseParametersGroup
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX + 1] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionY <= 6) {
            if (canKingMove(
                    color,
                    baseParametersGroup.pieceParameters.piece.positionY + 1,
                    baseParametersGroup.pieceParameters.piece.positionX,
                    baseParametersGroup.pieceParameters.piece,
                    baseParametersGroup
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionY <= 6 && baseParametersGroup.pieceParameters.piece.positionX >= 1) {
            if (canKingMove(
                    color,
                    baseParametersGroup.pieceParameters.piece.positionY + 1,
                    baseParametersGroup.pieceParameters.piece.positionX - 1,
                    baseParametersGroup.pieceParameters.piece,
                    baseParametersGroup
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY + 1][baseParametersGroup.pieceParameters.piece.positionX - 1] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionX >= 1) {
            if (canKingMove(
                    color,
                    baseParametersGroup.pieceParameters.piece.positionY,
                    baseParametersGroup.pieceParameters.piece.positionX - 1,
                    baseParametersGroup.pieceParameters.piece,
                    baseParametersGroup
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY][baseParametersGroup.pieceParameters.piece.positionX - 1] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.positionY >= 1 && baseParametersGroup.pieceParameters.piece.positionX >= 1) {
            if (canKingMove(
                    color,
                    baseParametersGroup.pieceParameters.piece.positionY - 1,
                    baseParametersGroup.pieceParameters.piece.positionX - 1,
                    baseParametersGroup.pieceParameters.piece,
                    baseParametersGroup
                )
            ) {
                returnParams.moves[baseParametersGroup.pieceParameters.piece.positionY - 1][baseParametersGroup.pieceParameters.piece.positionX - 1] =
                    true
            }
        }
        if (baseParametersGroup.pieceParameters.piece.color == 0) {
            if (canWhiteKingMakeLongCastle(baseParametersGroup)) {
                returnParams.moves[7][2] = true
                returnParams.longWhiteCastleAvailable = true
            }
            if (canWhiteKingMakeShortCastle(baseParametersGroup)) {
                returnParams.moves[7][6] = true
                returnParams.shortWhiteCastleAvailable = true
            }
        } else {
            if (canBlackKingMakeLongCastle(baseParametersGroup)) {
                returnParams.moves[0][2] = true
                returnParams.longBlackCastleAvailable = true
            }
            if (canBlackKingMakeShortCastle(baseParametersGroup)) {
                returnParams.moves[0][6] = true
                returnParams.shortBlackCastleAvailable = true
            }
        }
        return returnParams
    }

    fun showBlackKingMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        val flags = gameDescription.gameFlags
        val moves = MovesHelper().createMovesList()

        if (pieceY >= 1) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY - 1,
                    pieceX,
                    gameDescription,
                    1
                )
            ) {
                moves[pieceY - 1][pieceX] = true
            }
        }
        if (pieceY >= 1 && pieceX <= 6) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY - 1,
                    pieceX + 1,
                    gameDescription,
                    1
                )
            ) {
                moves[pieceY - 1][pieceX + 1] = true
            }
        }
        if (pieceX <= 6) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY,
                    pieceX + 1,
                    gameDescription,
                    1
                )
            ) {
                moves[pieceY][pieceX + 1] = true
            }
        }
        if (pieceY <= 6 && pieceX <= 6) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY + 1,
                    pieceX + 1,
                    gameDescription,
                    1
                )
            ) {
                moves[pieceY + 1][pieceX + 1] = true
            }
        }
        if (pieceY <= 6) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY + 1,
                    pieceX,
                    gameDescription,
                    1
                )
            ) {
                moves[pieceY + 1][pieceX] = true
            }
        }
        if (pieceY <= 6 && pieceX >= 1) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY + 1,
                    pieceX - 1,
                    gameDescription,
                    1
                )
            ) {
                moves[pieceY + 1][pieceX - 1] = true
            }
        }
        if (pieceX >= 1) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY,
                    pieceX - 1,
                    gameDescription,
                    1
                )
            ) {
                moves[pieceY][pieceX - 1] = true
            }
        }
        if (pieceY >= 1 && pieceX >= 1) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY - 1,
                    pieceX - 1,
                    gameDescription,
                    1
                )
            ) {
                moves[pieceY - 1][pieceX - 1] = true
            }
        }
        if (canBlackKingMakeLongCastle(gameDescription)) {
            moves[0][2] = true
            flags.longBlackCastleAvailable = true
        }
        if (canBlackKingMakeShortCastle(gameDescription)) {
            moves[0][6] = true
            flags.shortBlackCastleAvailable = true
        }
        return MovesAndFlags(
            moves,
            flags
        )
    }

    fun showWhiteKingMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): MovesAndFlags {
        val flags = gameDescription.gameFlags
        val moves = MovesHelper().createMovesList()

        if (pieceY >= 1) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY - 1,
                    pieceX,
                    gameDescription,
                    0
                )
            ) {
                moves[pieceY - 1][pieceX] = true
            }
        }
        if (pieceY >= 1 && pieceX <= 6) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY - 1,
                    pieceX + 1,
                    gameDescription,
                    0
                )
            ) {
                moves[pieceY - 1][pieceX + 1] = true
            }
        }
        if (pieceX <= 6) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY,
                    pieceX + 1,
                    gameDescription,
                    0
                )
            ) {
                moves[pieceY][pieceX + 1] = true
            }
        }
        if (pieceY <= 6 && pieceX <= 6) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY + 1,
                    pieceX + 1,
                    gameDescription,
                    0
                )
            ) {
                moves[pieceY + 1][pieceX + 1] = true
            }
        }
        if (pieceY <= 6) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY + 1,
                    pieceX,
                    gameDescription,
                    0
                )
            ) {
                moves[pieceY + 1][pieceX] = true
            }
        }
        if (pieceY <= 6 && pieceX >= 1) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY + 1,
                    pieceX - 1,
                    gameDescription,
                    0
                )
            ) {
                moves[pieceY + 1][pieceX - 1] = true
            }
        }
        if (pieceX >= 1) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY,
                    pieceX - 1,
                    gameDescription,
                    0
                )
            ) {
                moves[pieceY][pieceX - 1] = true
            }
        }
        if (pieceY >= 1 && pieceX >= 1) {
            if (canKingMove(
                    pieceY,
                    pieceX,
                    pieceY - 1,
                    pieceX - 1,
                    gameDescription,
                    0
                )
            ) {
                moves[pieceY - 1][pieceX - 1] = true
            }
        }
        if (canWhiteKingMakeLongCastle(gameDescription)) {
            moves[7][2] = true
            flags.longWhiteCastleAvailable = true
        }
        if (canWhiteKingMakeShortCastle(gameDescription)) {
            moves[7][6] = true
            flags.shortWhiteCastleAvailable = true
        }
        return MovesAndFlags(
            moves,
            flags
        )
    }

    fun checkBlackKingMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        if (pieceY >= 1) {
            if (!isWhiteKingTooClose(pieceY - 1, pieceX, gameDescription.board)) {
                moves[pieceY - 1][pieceX] = true
            }
        }
        if (pieceY >= 1 && pieceX <= 6) {
            if (!isWhiteKingTooClose(pieceY - 1, pieceX + 1, gameDescription.board)) {
                moves[pieceY - 1][pieceX + 1] = true
            }
        }
        if (pieceX <= 6) {
            if (!isWhiteKingTooClose(pieceY, pieceX + 1, gameDescription.board)) {
                moves[pieceY][pieceX + 1] = true
            }
        }
        if (pieceY <= 6 && pieceX <= 6) {
            if (!isWhiteKingTooClose(pieceY + 1, pieceX + 1, gameDescription.board)) {
                moves[pieceY + 1][pieceX + 1] = true
            }
        }
        if (pieceY <= 6) {
            if (!isWhiteKingTooClose(pieceY + 1, pieceX, gameDescription.board)) {
                moves[pieceY + 1][pieceX] = true
            }
        }
        if (pieceY <= 6 && pieceX >= 1) {
            if (!isWhiteKingTooClose(pieceY + 1, pieceX - 1, gameDescription.board)) {
                moves[pieceY + 1][pieceX - 1] = true
            }
        }
        if (pieceX >= 1) {
            if (!isWhiteKingTooClose(pieceY, pieceX - 1, gameDescription.board)) {
                moves[pieceY][pieceX - 1] = true
            }
        }
        if (pieceY >= 1 && pieceX >= 1) {
            if (!isWhiteKingTooClose(pieceY - 1, pieceX - 1, gameDescription.board)) {
                moves[pieceY - 1][pieceX - 1] = true
            }
        }
        return moves
    }

    fun checkWhiteKingMoves(
        pieceY: Int,
        pieceX: Int,
        gameDescription: GameDescription
    ): Array<Array<Boolean>> {
        val moves = MovesHelper().createMovesList()

        if (pieceY >= 1) {
            if (!isBlackKingTooClose(pieceY - 1, pieceX, gameDescription.board)) {
                moves[pieceY - 1][pieceX] = true
            }
        }
        if (pieceY >= 1 && pieceX <= 6) {
            if (!isBlackKingTooClose(pieceY - 1, pieceX + 1, gameDescription.board)) {
                moves[pieceY - 1][pieceX + 1] = true
            }
        }
        if (pieceX <= 6) {
            if (!isBlackKingTooClose(pieceY, pieceX + 1, gameDescription.board)) {
                moves[pieceY][pieceX + 1] = true
            }
        }
        if (pieceY <= 6 && pieceX <= 6) {
            if (!isBlackKingTooClose(pieceY + 1, pieceX + 1, gameDescription.board)) {
                moves[pieceY + 1][pieceX + 1] = true
            }
        }
        if (pieceY <= 6) {
            if (!isBlackKingTooClose(pieceY + 1, pieceX, gameDescription.board)) {
                moves[pieceY + 1][pieceX] = true
            }
        }
        if (pieceY <= 6 && pieceX >= 1) {
            if (!isBlackKingTooClose(pieceY + 1, pieceX - 1, gameDescription.board)) {
                moves[pieceY + 1][pieceX - 1] = true
            }
        }
        if (pieceX >= 1) {
            if (!isBlackKingTooClose(pieceY, pieceX - 1, gameDescription.board)) {
                moves[pieceY][pieceX - 1] = true
            }
        }
        if (pieceY >= 1 && pieceX >= 1) {
            if (!isBlackKingTooClose(pieceY - 1, pieceX - 1, gameDescription.board)) {
                moves[pieceY - 1][pieceX - 1] = true
            }
        }
        return moves
    }

    private fun canKingMove(
        pieceY: Int,
        pieceX: Int,
        moveY: Int,
        moveX: Int,
        gameDescription: GameDescription,
        color: Int
    ): Boolean {
        return if (color == 0) {
            !isBlackKingTooClose(moveY, moveX, gameDescription.board) &&
                    PiecesBoardHelper().canPieceMove(pieceY, pieceX, moveY, moveX, gameDescription, 0)
        } else {
            !isWhiteKingTooClose(moveY, moveX, gameDescription.board) &&
                    PiecesBoardHelper().canPieceMove(pieceY, pieceX, moveY, moveX, gameDescription, 1)
        }
    }

    private fun canKingMove(
        color: Int,
        positionY: Int,
        positionX: Int,
        pieceFocused: Piece,
        baseParametersGroup: BaseParametersGroup
    ): Boolean {
        val colorOpposite = if (color == 0) {
            1
        } else {
            0
        }
        if (!isOtherKingTooClose(positionY, positionX, color, baseParametersGroup)) {
            val king: Piece = pieceFocused.copy()
            king.positionY = positionY
            king.positionX = positionX
            if (BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[positionY][positionX])) {
                val piece = PiecesHelper().findPiece(
                    positionY,
                    positionX,
                    baseParametersGroup.pieceParameters.piecesList
                )
                if (piece.color == color) {
                    val piecesListCopy =
                        baseParametersGroup.pieceParameters.piecesList.toMutableList() as ArrayList<Piece>
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
                                baseParametersGroup.pieceParameters.piecesList
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
                    val boardCopy = baseParametersGroup.pieceParameters.board.clone()
                    BoardHelper().resetBoard(
                        piecesListCopy,
                        boardCopy,
                        baseParametersGroup.pieceParameters.context
                    )
                    if (PiecesHelper().isCheck(
                            BaseParametersGroup(
                                PieceParameters(
                                    baseParametersGroup.pieceParameters.piece,
                                    baseParametersGroup.pieceParameters.board,
                                    baseParametersGroup.pieceParameters.moves,
                                    piecesListCopy,
                                    baseParametersGroup.pieceParameters.context
                                ),
                                baseParametersGroup.gameFlags,
                                baseParametersGroup.pawnBeforeMoveParameters
                            ),
                            colorOpposite
                        )
                    ) {
                        BoardHelper().resetBoard(
                            baseParametersGroup.pieceParameters.piecesList,
                            baseParametersGroup.pieceParameters.board,
                            baseParametersGroup.pieceParameters.context
                        )
                        return false
                    } else {
                        BoardHelper().resetBoard(
                            baseParametersGroup.pieceParameters.piecesList,
                            baseParametersGroup.pieceParameters.board,
                            baseParametersGroup.pieceParameters.context
                        )
                        return true
                    }
                } else {
                    return false
                }
            } else {
                val piecesListCopy =
                    baseParametersGroup.pieceParameters.piecesList.toMutableList() as ArrayList<Piece>
                piecesListCopy.set(
                    piecesListCopy.indexOf(pieceFocused),
                    Piece(
                        pieceFocused.type,
                        pieceFocused.color,
                        positionX,
                        positionY,
                        pieceFocused.isActive
                    )
                )
                val boardCopy = baseParametersGroup.pieceParameters.board.clone()
                BoardHelper().resetBoard(
                    piecesListCopy,
                    boardCopy,
                    baseParametersGroup.pieceParameters.context
                )
                if (PiecesHelper().isCheck(
                        BaseParametersGroup(
                            PieceParameters(
                                baseParametersGroup.pieceParameters.piece,
                                baseParametersGroup.pieceParameters.board,
                                baseParametersGroup.pieceParameters.moves,
                                piecesListCopy,
                                baseParametersGroup.pieceParameters.context
                            ),
                            baseParametersGroup.gameFlags,
                            baseParametersGroup.pawnBeforeMoveParameters
                        ),
                        colorOpposite
                    )
                ) {
                    BoardHelper().resetBoard(
                        baseParametersGroup.pieceParameters.piecesList,
                        baseParametersGroup.pieceParameters.board,
                        baseParametersGroup.pieceParameters.context
                    )
                    return false
                } else {
                    BoardHelper().resetBoard(
                        baseParametersGroup.pieceParameters.piecesList,
                        baseParametersGroup.pieceParameters.board,
                        baseParametersGroup.pieceParameters.context
                    )
                    return true
                }
            }
        } else {
            return false
        }
    }

    private fun canBlackKingMakeShortCastle(
        baseParametersGroup: BaseParametersGroup
    ): Boolean {
        if (baseParametersGroup.gameFlags.canCastleShortBlack && areNoPiecesInBlackKingShortCastle(
                baseParametersGroup
            )
        ) {
            if (!isBlackKingCheckAtShortCastle(baseParametersGroup)) {
                return true
            }
        }
        return false
    }

    private fun canBlackKingMakeShortCastle(
        gameDescription: GameDescription
    ): Boolean {
        if (gameDescription.gameFlags.canCastleShortBlack && areNoPiecesInBlackKingShortCastle(gameDescription.board)) {
            if (!isBlackKingCheckAtShortCastle(gameDescription)) {
                return true
            }
        }
        return false
    }

    fun isOtherKingTooClose(
        positionY: Int,
        positionX: Int,
        color: Int,
        baseParametersGroup: BaseParametersGroup
    ): Boolean {
        val otherKing = findKing(color, baseParametersGroup.pieceParameters.piecesList)
        if (abs(otherKing.positionX - positionX) <= 1) {
            if (abs(otherKing.positionY - positionY) <= 1) {
                return true
            }
        }
        return false
    }

    private fun isWhiteKingTooClose(
        pieceY: Int,
        pieceX: Int,
        board: Array<Array<PieceType>>
    ): Boolean {
        if (pieceY > 0) {
            if (board[pieceY - 1][pieceX] == PieceType.BlackKing)
                return true
        }
        if (pieceY > 0 && pieceX < 7) {
            if (board[pieceY - 1][pieceX + 1] == PieceType.BlackKing)
                return true
        }
        if (pieceX < 7) {
            if (board[pieceY][pieceX + 1] == PieceType.BlackKing)
                return true
        }
        if (pieceY < 7 && pieceX < 7) {
            if (board[pieceY + 1][pieceX + 1] == PieceType.BlackKing)
                return true
        }
        if (pieceY < 7 && pieceX < 7) {
            if (board[pieceY + 1][pieceX] == PieceType.BlackKing)
                return true
        }
        if (pieceY < 7 && pieceX > 0) {
            if (board[pieceY + 1][pieceX - 1] == PieceType.BlackKing)
                return true
        }
        if (pieceX > 0) {
            if (board[pieceY][pieceX - 1] == PieceType.BlackKing)
                return true
        }
        if (pieceY > 0 && pieceX > 0) {
            if (board[pieceY - 1][pieceX - 1] == PieceType.BlackKing)
                return true
        }
        return false
    }

    private fun isBlackKingTooClose(
        pieceY: Int,
        pieceX: Int,
        board: Array<Array<PieceType>>
    ): Boolean {
        if (pieceY > 0) {
            if (board[pieceY - 1][pieceX] == PieceType.WhiteKing)
                return true
        }
        if (pieceY > 0 && pieceX < 7) {
            if (board[pieceY - 1][pieceX + 1] == PieceType.WhiteKing)
                return true
        }
        if (pieceX < 7) {
            if (board[pieceY][pieceX + 1] == PieceType.WhiteKing)
                return true
        }
        if (pieceY < 7 && pieceX < 7) {
            if (board[pieceY + 1][pieceX + 1] == PieceType.WhiteKing)
                return true
        }
        if (pieceY < 7 && pieceX < 7) {
            if (board[pieceY + 1][pieceX] == PieceType.WhiteKing)
                return true
        }
        if (pieceY < 7 && pieceX > 0) {
            if (board[pieceY + 1][pieceX - 1] == PieceType.WhiteKing)
                return true
        }
        if (pieceX > 0) {
            if (board[pieceY][pieceX - 1] == PieceType.WhiteKing)
                return true
        }
        if (pieceY > 0 && pieceX > 0) {
            if (board[pieceY - 1][pieceX - 1] == PieceType.WhiteKing)
                return true
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

    private fun isBlackKingCheckAtShortCastle(baseParametersGroup: BaseParametersGroup): Boolean {
        val king = findKing(1, baseParametersGroup.pieceParameters.piecesList)
        val pieces1 = baseParametersGroup.pieceParameters.piecesList.clone() as ArrayList<Piece>
        val pieces2 = baseParametersGroup.pieceParameters.piecesList.clone() as ArrayList<Piece>
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
        if (isBlackCheck(baseParametersGroup)) {
            return true
        } else if (isBlackCheck(pieces1, baseParametersGroup)) {
            return true
        } else if (isBlackCheck(pieces2, baseParametersGroup)) {
            return true
        }
        return false
    }

    private fun isBlackKingCheckAtShortCastle(gameDescription: GameDescription): Boolean {
        if (!PiecesBoardHelper().canPieceMove(0,4, 0,5, gameDescription, 1) ||
            !PiecesBoardHelper().canPieceMove(0,4, 0,6, gameDescription, 1)){
            return true
        }
        return false
    }

    private fun areNoPiecesInBlackKingShortCastle(baseParametersGroup: BaseParametersGroup): Boolean {
        return !BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[0][5]) && !BoardHelper().isPiece(
            baseParametersGroup.pieceParameters.board[0][6]
        )
    }

    private fun areNoPiecesInBlackKingShortCastle(board: Array<Array<PieceType>>): Boolean {
        return board[0][5] == PieceType.Empty && board[0][6] == PieceType.Empty
    }

    private fun canBlackKingMakeLongCastle(
        baseParametersGroup: BaseParametersGroup
    ): Boolean {
        if (baseParametersGroup.gameFlags.canCastleLongBlack && areNoPiecesInBlackKingLongCastle(
                baseParametersGroup
            )
        ) {
            if (!isBlackKingCheckAtLongCastle(baseParametersGroup)) {
                return true
            }
        }
        return false
    }

    private fun canBlackKingMakeLongCastle(gameDescription: GameDescription): Boolean {
        if (gameDescription.gameFlags.canCastleLongBlack &&
            areNoPiecesInBlackKingLongCastle(gameDescription.board)
        ) {
            if (!isBlackKingCheckAtLongCastle(gameDescription)) {
                return true
            }
        }
        return false
    }

    private fun isBlackKingCheckAtLongCastle(baseParametersGroup: BaseParametersGroup): Boolean {
        val king = findKing(1, baseParametersGroup.pieceParameters.piecesList)
        val pieces1 = baseParametersGroup.pieceParameters.piecesList.clone() as ArrayList<Piece>
        val pieces2 = baseParametersGroup.pieceParameters.piecesList.clone() as ArrayList<Piece>
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
        if (isBlackCheck(baseParametersGroup)) {
            return true
        } else if (isBlackCheck(pieces1, baseParametersGroup)) {
            return true
        } else if (isBlackCheck(pieces2, baseParametersGroup)) {
            return true
        }
        return false
    }

    private fun isBlackKingCheckAtLongCastle(gameDescription: GameDescription): Boolean {
        if (!PiecesBoardHelper().canPieceMove(0,4, 0,3, gameDescription, 1) ||
            !PiecesBoardHelper().canPieceMove(0,4, 0,2, gameDescription, 1)){
            return true
        }
        return false
    }

    private fun areNoPiecesInBlackKingLongCastle(baseParametersGroup: BaseParametersGroup): Boolean {
        return !BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[0][3]) && !BoardHelper().isPiece(
            baseParametersGroup.pieceParameters.board[0][2]
        ) && !BoardHelper().isPiece(
            baseParametersGroup.pieceParameters.board[0][1]
        )
    }

    private fun areNoPiecesInBlackKingLongCastle(board: Array<Array<PieceType>>): Boolean {
        return board[0][3] == PieceType.Empty && board[0][2] == PieceType.Empty &&
                board[0][1] == PieceType.Empty
    }

    private fun canWhiteKingMakeShortCastle(
        baseParametersGroup: BaseParametersGroup
    ): Boolean {
        if (baseParametersGroup.gameFlags.canCastleShortWhite && areNoPiecesInWhiteKingShortCastle(
                baseParametersGroup
            )
        ) {
            if (!isWhiteKingCheckAtShortCastle(baseParametersGroup)) {
                return true
            }
        }
        return false
    }

    private fun canWhiteKingMakeShortCastle(
        gameDescription: GameDescription
    ): Boolean {
        if (gameDescription.gameFlags.canCastleShortWhite && areNoPiecesInWhiteKingShortCastle(gameDescription.board)
        ) {
            if (!isWhiteKingCheckAtShortCastle(gameDescription)) {
                return true
            }
        }
        return false
    }

    private fun isWhiteKingCheckAtShortCastle(baseParametersGroup: BaseParametersGroup): Boolean {
        val king = findKing(0, baseParametersGroup.pieceParameters.piecesList)
        val pieces1 = baseParametersGroup.pieceParameters.piecesList.clone() as ArrayList<Piece>
        val pieces2 = baseParametersGroup.pieceParameters.piecesList.clone() as ArrayList<Piece>
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
        if (isWhiteCheck(baseParametersGroup)) {
            return true
        } else if (isWhiteCheck(pieces1, baseParametersGroup)) {
            return true
        } else if (isWhiteCheck(pieces2, baseParametersGroup)) {
            return true
        }
        return false
    }

    private fun isWhiteKingCheckAtShortCastle(gameDescription: GameDescription): Boolean {
        if (!PiecesBoardHelper().canPieceMove(7,4, 0,5, gameDescription, 0) ||
            !PiecesBoardHelper().canPieceMove(7,4, 0,6, gameDescription, 0)){
            return true
        }
        return false
    }

    private fun areNoPiecesInWhiteKingShortCastle(baseParametersGroup: BaseParametersGroup): Boolean {
        return !BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[7][5]) && !BoardHelper().isPiece(
            baseParametersGroup.pieceParameters.board[7][6]
        )
    }

    private fun areNoPiecesInWhiteKingShortCastle(board: Array<Array<PieceType>>): Boolean {
        return board[7][5] == PieceType.Empty && board[7][6] == PieceType.Empty
    }

    private fun canWhiteKingMakeLongCastle(
        baseParametersGroup: BaseParametersGroup
    ): Boolean {
        if (baseParametersGroup.gameFlags.canCastleLongWhite && areNoPiecesInWhiteKingLongCastle(
                baseParametersGroup
            )
        ) {
            if (!isWhiteKingCheckAtLongCastle(baseParametersGroup)) {
                return true
            }
        }
        return false
    }

    private fun canWhiteKingMakeLongCastle(
        gameDescription: GameDescription
    ): Boolean {
        if (gameDescription.gameFlags.canCastleLongWhite && areNoPiecesInWhiteKingLongCastle(gameDescription.board)
        ) {
            if (!isWhiteKingCheckAtLongCastle(gameDescription)) {
                return true
            }
        }
        return false
    }


    private fun areNoPiecesInWhiteKingLongCastle(baseParametersGroup: BaseParametersGroup): Boolean {
        return !BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[7][3]) && !BoardHelper().isPiece(
            baseParametersGroup.pieceParameters.board[7][2]
        ) &&
                !BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[7][1])
    }

    private fun areNoPiecesInWhiteKingLongCastle(board: Array<Array<PieceType>>): Boolean {
        return board[7][3] == PieceType.Empty && board[7][2] == PieceType.Empty &&
                board[7][1] == PieceType.Empty
    }

    private fun isWhiteKingCheckAtLongCastle(baseParametersGroup: BaseParametersGroup): Boolean {
        val king = findKing(0, baseParametersGroup.pieceParameters.piecesList)
        val pieces1 = baseParametersGroup.pieceParameters.piecesList.clone() as ArrayList<Piece>
        val pieces2 = baseParametersGroup.pieceParameters.piecesList.clone() as ArrayList<Piece>
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
        if (isWhiteCheck(baseParametersGroup)) {
            return true
        } else if (isWhiteCheck(pieces1, baseParametersGroup)) {
            return true
        } else if (isWhiteCheck(pieces2, baseParametersGroup)) {
            return true
        }
        return false
    }

    private fun isWhiteKingCheckAtLongCastle(gameDescription: GameDescription): Boolean {
        if (!PiecesBoardHelper().canPieceMove(7,4, 0,3, gameDescription, 0) ||
            !PiecesBoardHelper().canPieceMove(7,4, 0,2, gameDescription, 0)){
            return true
        }
        return false
    }

    private fun isBlackCheck(baseParametersGroup: BaseParametersGroup): Boolean {
        return PiecesHelper().isCheck(baseParametersGroup, 1)
    }

    private fun isBlackCheck(
        piecesGiven: ArrayList<Piece>,
        baseParametersGroup: BaseParametersGroup
    ): Boolean {
        return PiecesHelper().isCheck(
            BaseParametersGroup(
                PieceParameters(
                    baseParametersGroup.pieceParameters.piece,
                    baseParametersGroup.pieceParameters.board,
                    baseParametersGroup.pieceParameters.moves,
                    piecesGiven,
                    baseParametersGroup.pieceParameters.context
                ),
                baseParametersGroup.gameFlags,
                baseParametersGroup.pawnBeforeMoveParameters
            ),
            1
        )
    }

    private fun isWhiteCheck(baseParametersGroup: BaseParametersGroup): Boolean {
        return PiecesHelper().isCheck(baseParametersGroup, 0)
    }

    private fun isWhiteCheck(
        piecesGiven: ArrayList<Piece>,
        baseParametersGroup: BaseParametersGroup
    ): Boolean {
        return PiecesHelper().isCheck(
            BaseParametersGroup(
                PieceParameters(
                    baseParametersGroup.pieceParameters.piece,
                    baseParametersGroup.pieceParameters.board,
                    baseParametersGroup.pieceParameters.moves,
                    piecesGiven,
                    baseParametersGroup.pieceParameters.context
                ),
                baseParametersGroup.gameFlags,
                baseParametersGroup.pawnBeforeMoveParameters
            ),
            0
        )
    }

    fun checkIfKingHasMoves(
        baseParametersGroup: BaseParametersGroup
    ): Boolean {
        val moves = showKingMoves(baseParametersGroup).moves
        return MovesHelper().checkIsAnyMovePossible(moves)
    }

}