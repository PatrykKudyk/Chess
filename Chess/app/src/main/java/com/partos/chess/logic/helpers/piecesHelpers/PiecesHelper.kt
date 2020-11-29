package com.partos.chess.logic.helpers.piecesHelpers

import com.partos.chess.logic.helpers.BoardHelper
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.PieceAfterMoveParameters
import com.partos.chess.models.parameters.PieceParameters

class PiecesHelper {


    fun initPieces(): ArrayList<Piece> {
        val piecesList = ArrayList<Piece>()
        //white pawns
        piecesList.add(Piece(0, 0, 0, 6, true))
        piecesList.add(Piece(0, 0, 1, 6, true))
        piecesList.add(Piece(0, 0, 2, 6, true))
        piecesList.add(Piece(0, 0, 3, 6, true))
        piecesList.add(Piece(0, 0, 4, 6, true))
        piecesList.add(Piece(0, 0, 5, 6, true))
        piecesList.add(Piece(0, 0, 6, 6, true))
        piecesList.add(Piece(0, 0, 7, 6, true))

        //black pawns
        piecesList.add(Piece(0, 1, 0, 1, true))
        piecesList.add(Piece(0, 1, 1, 1, true))
        piecesList.add(Piece(0, 1, 2, 1, true))
        piecesList.add(Piece(0, 1, 3, 1, true))
        piecesList.add(Piece(0, 1, 4, 1, true))
        piecesList.add(Piece(0, 1, 5, 1, true))
        piecesList.add(Piece(0, 1, 6, 1, true))
        piecesList.add(Piece(0, 1, 7, 1, true))

        //white rest
        piecesList.add(Piece(3, 0, 0, 7, true))
        piecesList.add(Piece(3, 0, 7, 7, true))
        piecesList.add(Piece(2, 0, 1, 7, true))
        piecesList.add(Piece(2, 0, 6, 7, true))
        piecesList.add(Piece(1, 0, 2, 7, true))
        piecesList.add(Piece(1, 0, 5, 7, true))
        piecesList.add(Piece(4, 0, 3, 7, true))
        piecesList.add(Piece(5, 0, 4, 7, true))

        //black rest
        piecesList.add(Piece(3, 1, 0, 0, true))
        piecesList.add(Piece(3, 1, 7, 0, true))
        piecesList.add(Piece(2, 1, 1, 0, true))
        piecesList.add(Piece(2, 1, 6, 0, true))
        piecesList.add(Piece(1, 1, 2, 0, true))
        piecesList.add(Piece(1, 1, 5, 0, true))
        piecesList.add(Piece(4, 1, 3, 0, true))
        piecesList.add(Piece(5, 1, 4, 0, true))

        return piecesList
    }

    private fun createMovesList(): Array<Array<Boolean>> {
        val array1 = arrayOf(false, false, false, false, false, false, false, false)
        val array2 = arrayOf(false, false, false, false, false, false, false, false)
        val array3 = arrayOf(false, false, false, false, false, false, false, false)
        val array4 = arrayOf(false, false, false, false, false, false, false, false)
        val array5 = arrayOf(false, false, false, false, false, false, false, false)
        val array6 = arrayOf(false, false, false, false, false, false, false, false)
        val array7 = arrayOf(false, false, false, false, false, false, false, false)
        val array8 = arrayOf(false, false, false, false, false, false, false, false)
        return arrayOf(array1, array2, array3, array4, array5, array6, array7, array8)
    }

    fun isCheck(
        baseParametersGroup: BaseParametersGroup,
        color: Int
    ): Boolean {
        var movesList = MovesHelper().createMovesList()
        if (color == 0) {
            for (piece in baseParametersGroup.pieceParameters.piecesList) {
                if (piece.color == 1 && piece.isActive) {
                    baseParametersGroup.pieceParameters.piece = piece
                    movesList = MovesHelper().mergeMovesLists(
                        movesList,
                        checkPieceMoves(baseParametersGroup).moves
                    )
                }
            }
            val king = KingHelper().findKing(0, baseParametersGroup.pieceParameters.piecesList)
            return movesList[king.positionY][king.positionX]
        } else {
            for (piece in baseParametersGroup.pieceParameters.piecesList) {
                if (piece.color == 0 && piece.isActive) {
                    baseParametersGroup.pieceParameters.piece = piece
                    movesList = MovesHelper().mergeMovesLists(
                        movesList,
                        checkPieceMoves(baseParametersGroup).moves
                    )
                }
            }
            val king = KingHelper().findKing(1, baseParametersGroup.pieceParameters.piecesList)
            return movesList[king.positionY][king.positionX]
        }
    }

    private fun checkPieceMoves(baseParametersGroup: BaseParametersGroup): PieceAfterMoveParameters {
        var returnParams = PieceAfterMoveParameters(
            MovesHelper().createMovesList(),
            isChoose = false,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false
        )
        when (baseParametersGroup.pieceParameters.piece.type) {
            0 -> {
                if (baseParametersGroup.pieceParameters.piece.color == 0) {
                    returnParams = PawnHelper().checkWhitePawnMoves(baseParametersGroup)
                } else {
                    returnParams = PawnHelper().checkBlackPawnMoves(baseParametersGroup)
                }
            }
            1 -> {
                returnParams.moves = BishopHelper().checkBishopMoves(baseParametersGroup)
                returnParams.isChoose = false
            }
            2 -> {
                returnParams.moves = KnightHelper().checkKnightMoves(baseParametersGroup)
                returnParams.isChoose = false
            }
            3 -> {
                returnParams.moves = RookHelper().checkRookMoves(baseParametersGroup)
                returnParams.isChoose = false
            }
            4 -> {
                returnParams.moves = QueenHelper().checkQueenMoves(baseParametersGroup)
                returnParams.isChoose = false
            }
        }
        return returnParams
    }

    fun showPieceMoves(baseParametersGroup: BaseParametersGroup): PieceAfterMoveParameters {
        var returnParams = PieceAfterMoveParameters(
            MovesHelper().createMovesList(),
            isChoose = false,
            longWhiteCastleAvailable = false,
            longBlackCastleAvailable = false,
            shortWhiteCastleAvailable = false,
            shortBlackCastleAvailable = false
        )
        when (baseParametersGroup.pieceParameters.piece.type) {
            0 -> {
                if (baseParametersGroup.pieceParameters.piece.color == 0) {
                    returnParams = PawnHelper().showWhitePawnMoves(baseParametersGroup)
                } else {
                    returnParams = PawnHelper().showBlackPawnMoves(baseParametersGroup)
                }
            }
            1 -> {
                returnParams.moves = BishopHelper().showBishopMoves(baseParametersGroup)
                returnParams.isChoose = false
            }
            2 -> {
                returnParams.moves = KnightHelper().showKnightMoves(baseParametersGroup)
                returnParams.isChoose = false
            }
            3 -> {
                returnParams.moves = RookHelper().showRookMoves(baseParametersGroup)
                returnParams.isChoose = false
            }
            4 -> {
                returnParams.moves = QueenHelper().showQueenMoves(baseParametersGroup)
                returnParams.isChoose = false
            }
            5 -> {
                returnParams = KingHelper().showKingMoves(baseParametersGroup)
            }
        }
        return returnParams
    }

    fun findPiece(positionY: Int, positionX: Int, piecesList: ArrayList<Piece>): Piece {
        for (piece in piecesList) {
            if (piece.isActive) {
                if (piece.positionX == positionX && piece.positionY == positionY) {
                    return piece
                }
            }
        }
        return Piece(0, 0, 0, 0, false)
    }


    fun isCheckMate(baseParametersGroup: BaseParametersGroup, color: Int): Boolean {
        var movesList = MovesHelper().createMovesList()
        for (piece in baseParametersGroup.pieceParameters.piecesList) {
            if (piece.color == color && piece.isActive) {
                baseParametersGroup.pieceParameters.piece = piece
                movesList = MovesHelper().mergeMovesLists(
                    movesList,
                    showPieceMoves(baseParametersGroup).moves
                )
            }
        }
        if (movesList[0][0]) {
            val i: Int
        }
        return !MovesHelper().checkIsAnyMovePossible(movesList)
    }

    fun canPieceMove(
        positionY: Int,
        positionX: Int,
        baseParametersGroup: BaseParametersGroup
    ): Boolean {
        val colorOpposite = if (baseParametersGroup.pieceParameters.piece.color == 0) {
            1
        } else {
            0
        }
        if (!BoardHelper().isPiece(baseParametersGroup.pieceParameters.board[positionY][positionX])) {
            val piece = baseParametersGroup.pieceParameters.piece.copy()
            piece.positionY = positionY
            piece.positionX = positionX
            val piecesListCopy =
                baseParametersGroup.pieceParameters.piecesList.toMutableList() as ArrayList<Piece>
            piecesListCopy.set(
                piecesListCopy.indexOf(baseParametersGroup.pieceParameters.piece),
                Piece(
                    baseParametersGroup.pieceParameters.piece.type,
                    baseParametersGroup.pieceParameters.piece.color,
                    piece.positionX,
                    piece.positionY,
                    baseParametersGroup.pieceParameters.piece.isActive
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
                            boardCopy,
                            MovesHelper().createMovesList(),
                            piecesListCopy,
                            baseParametersGroup.pieceParameters.context
                        ),
                        baseParametersGroup.gameFlags,
                        baseParametersGroup.pawnBeforeMoveParameters
                    ),
                    baseParametersGroup.pieceParameters.piece.color
                )
            ) {
                BoardHelper().resetBoard(
                    baseParametersGroup.pieceParameters.piecesList,
                    baseParametersGroup.pieceParameters.board,
                    baseParametersGroup.pieceParameters.context
                )
                return false
            }
            BoardHelper().resetBoard(
                baseParametersGroup.pieceParameters.piecesList,
                baseParametersGroup.pieceParameters.board,
                baseParametersGroup.pieceParameters.context
            )
            return true
        } else {
            if (findPiece(
                    (positionY),
                    (positionX),
                    baseParametersGroup.pieceParameters.piecesList
                ).color == colorOpposite
            ) {
                val piece = baseParametersGroup.pieceParameters.piece.copy()
                piece.positionY = positionY
                piece.positionX = positionX
                val piecesListCopy =
                    baseParametersGroup.pieceParameters.piecesList.toMutableList() as ArrayList<Piece>
                piecesListCopy.set(
                    piecesListCopy.indexOf(baseParametersGroup.pieceParameters.piece),
                    Piece(
                        baseParametersGroup.pieceParameters.piece.type,
                        baseParametersGroup.pieceParameters.piece.color,
                        piece.positionX,
                        piece.positionY,
                        baseParametersGroup.pieceParameters.piece.isActive
                    )
                )
                piecesListCopy.set(
                    piecesListCopy.indexOf(
                        findPiece(
                            positionY,
                            positionX,
                            piecesListCopy
                        )
                    ),
                    Piece(0, 0, 0, 0, false)
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
                                boardCopy,
                                MovesHelper().createMovesList(),
                                piecesListCopy,
                                baseParametersGroup.pieceParameters.context
                            ),
                            baseParametersGroup.gameFlags,
                            baseParametersGroup.pawnBeforeMoveParameters
                        ),
                        baseParametersGroup.pieceParameters.piece.color
                    )
                ) {
                    BoardHelper().resetBoard(
                        baseParametersGroup.pieceParameters.piecesList,
                        baseParametersGroup.pieceParameters.board,
                        baseParametersGroup.pieceParameters.context
                    )
                    return false
                }
                BoardHelper().resetBoard(
                    baseParametersGroup.pieceParameters.piecesList,
                    baseParametersGroup.pieceParameters.board,
                    baseParametersGroup.pieceParameters.context
                )
                return true
            }
        }
        return false
    }

    fun isAnyMovePossible(
        color: Int,
        baseParametersGroup: BaseParametersGroup
    ): Boolean {
        for (piece in baseParametersGroup.pieceParameters.piecesList) {
            if (piece.color == color && piece.isActive && piece.type != 5) {
                var movesList = MovesHelper().createMovesList()
                baseParametersGroup.pieceParameters.piece = piece
                movesList = MovesHelper().mergeMovesLists(
                    movesList,
                    showPieceMoves(baseParametersGroup).moves
                )
                if (MovesHelper().checkIsAnyMovePossible(movesList)) {
                    return true
                }
            }
        }
        return false
    }

    fun isOppositeColorPiece(
        pieceParams: PieceParameters,
        positionY: Int,
        positionX: Int
    ): Boolean {
        val colorOpposite = if (pieceParams.piece.color == 0) {
            1
        } else {
            0
        }
        return findPiece(
            (positionY),
            (positionX),
            pieceParams.piecesList
        ).color == colorOpposite
    }

    fun hasKingMoves(baseParametersGroup: BaseParametersGroup): Boolean {
        return KingHelper().checkIfKingHasMoves(baseParametersGroup)
    }

    fun getActivePiecesAmount(piecesList: ArrayList<Piece>): Int {
        var amount = 0
        for (piece in piecesList) {
            if (piece.isActive) {
                amount++
            }
        }
        return amount
    }
}