package com.partos.chess.logic.helpers

import android.view.View
import com.partos.chess.logic.helpers.piecesHelpers.BishopHelper
import com.partos.chess.logic.helpers.piecesHelpers.KingHelper
import com.partos.chess.logic.helpers.piecesHelpers.KnightHelper
import com.partos.chess.logic.helpers.piecesHelpers.PiecesHelper
import com.partos.chess.logic.logic.GameLogic
import com.partos.chess.models.GameFlags
import com.partos.chess.models.Piece
import com.partos.chess.models.parameters.BaseParametersGroup
import com.partos.chess.models.parameters.PieceParameters
import com.partos.chess.models.parameters.TakenEndGameParameters

class GameHelper {

    fun checkEndOfGame(givenParams: TakenEndGameParameters) {
        if (isStaleMate(givenParams)) {
            showStaleMate(givenParams.rootView)
        } else if (isFiftyMovesWithNoCapture(
                givenParams.movesWithNoCaptureWhite,
                givenParams.movesWithNoCaptureBlack
            )
        ) {
            showFiftyMovesWithNoCapture(givenParams.rootView)
        } else if (isDeadPosition(givenParams)) {
            showDeadPosition(givenParams.rootView)
        }
    }

    private fun showStaleMate(rootView: View) {
        GameLogic().showEndGameMessage("STALEMATE!\nIT IS A DRAW", rootView)
    }

    private fun showFiftyMovesWithNoCapture(rootView: View) {
        GameLogic().showEndGameMessage("50 MOVES WITH\nNO CAPTURE!\nIT IS A DRAW", rootView)
    }

    private fun showDeadPosition(rootView: View) {
        GameLogic().showEndGameMessage("DEAD POSITION!\nIT IS A DRAW", rootView)
    }

    private fun isFiftyMovesWithNoCapture(
        movesWithNoCaptureWhite: Int,
        movesWithNoCaptureBlack: Int
    ): Boolean {
        if (movesWithNoCaptureBlack >= 50 || movesWithNoCaptureWhite >= 50) {
            return true
        }
        return false
    }

    private fun isDeadPosition(givenParams: TakenEndGameParameters): Boolean {
        if (isKingVsKing(givenParams.baseParametersGroup.pieceParameters.piecesList)) {
            return true
        } else if (isKingVsKingAndBishop(givenParams.baseParametersGroup.pieceParameters.piecesList)) {
            return true
        } else if (isKingVsKingAndKnight(givenParams.baseParametersGroup.pieceParameters.piecesList)) {
            return true
        } else if (areKingsPlusTheSameColorBishops(givenParams.baseParametersGroup.pieceParameters.piecesList)) {
            return true
        }
        return false
    }

    private fun isKingVsKing(piecesList: ArrayList<Piece>): Boolean {
        var pieces = 0
        for (piece in piecesList) {
            if (piece.isActive) {
                pieces++
            }
        }
        return pieces == 2
    }

    private fun isKingVsKingAndBishop(piecesList: ArrayList<Piece>): Boolean {
        var pieces = 0
        for (piece in piecesList) {
            if (piece.isActive) {
                pieces++
            }
        }
        if (pieces == 3) {
            if (BishopHelper().isAnyBishopActive(0, piecesList) ||
                BishopHelper().isAnyBishopActive(1, piecesList)
            )
                return true
        }
        return false
    }

    private fun isKingVsKingAndKnight(piecesList: ArrayList<Piece>): Boolean {
        var pieces = 0
        for (piece in piecesList) {
            if (piece.isActive) {
                pieces++
            }
        }
        if (pieces == 3) {
            if (KnightHelper().isAnyKnightActive(0, piecesList) ||
                KnightHelper().isAnyKnightActive(1, piecesList)
            )
                return true
        }
        return false
    }

    private fun areKingsPlusTheSameColorBishops(piecesList: ArrayList<Piece>): Boolean {
        var pieces = 0
        for (piece in piecesList) {
            if (piece.isActive) {
                pieces++
            }
        }
        if (pieces == 4) {
            if (BishopHelper().isAnyBishopActive(0, piecesList) &&
                BishopHelper().isAnyBishopActive(0, piecesList)
            ) {
                if (BishopHelper().colorOfBishopSquare(0, piecesList) ==
                    BishopHelper().colorOfBishopSquare(1, piecesList)
                ) {
                    return true
                }
                return false
            }
            return false
        }
        return false
    }


    private fun isStaleMate(givenParams: TakenEndGameParameters): Boolean {
        if (givenParams.turn == 0) {
            return isWhiteStaleMate(givenParams)
        } else {
            return isBlackStaleMate(givenParams)
        }
    }

    private fun isBlackStaleMate(gameParameters: TakenEndGameParameters): Boolean {
        if (!PiecesHelper().isAnyMovePossible(1, gameParameters.baseParametersGroup)
        ) {
            if (!PiecesHelper().isCheck(gameParameters.baseParametersGroup, 1)
            ) {
                gameParameters.baseParametersGroup.pieceParameters.piece =
                    KingHelper().findKing(
                        1,
                        gameParameters.baseParametersGroup.pieceParameters.piecesList
                    )
                return PiecesHelper().hasKingMoves(gameParameters.baseParametersGroup)
            }
        }
        return false
    }

    private fun isWhiteStaleMate(gameParameters: TakenEndGameParameters): Boolean {
        if (!PiecesHelper().isAnyMovePossible(0, gameParameters.baseParametersGroup)
        ) {
            if (!PiecesHelper().isCheck(gameParameters.baseParametersGroup, 0)
            ) {
                gameParameters.baseParametersGroup.pieceParameters.piece =
                    KingHelper().findKing(0, gameParameters.baseParametersGroup.pieceParameters.piecesList)
                return PiecesHelper().hasKingMoves(gameParameters.baseParametersGroup)
            }
        }
        return false
    }

    fun checkChecks(baseParametersGroup: BaseParametersGroup, rootView: View): GameFlags {
        val gameFlags = FlagsHelper().createFlags()
        if (isWhiteCheck(baseParametersGroup)) {
            if (isWhiteCheckMate(baseParametersGroup)) {
                GameLogic().showEndGameMessage("BLACK WINS", rootView)
            } else {
                gameFlags.checkWhite = true
                GameLogic().showWhiteCheck(rootView)
            }
        } else {
            gameFlags.checkWhite = false
            GameLogic().hideWhiteCheck(rootView)
        }
        if (isBlackCheck(baseParametersGroup)) {
            if (isBlackCheckMate(baseParametersGroup)) {
                GameLogic().showEndGameMessage("WHITE WINS", rootView)
            } else {
                gameFlags.checkBlack = true
                GameLogic().showBlackCheck(rootView)
            }
        } else {
            gameFlags.checkBlack = false
            GameLogic().hideBlackCheck(rootView)
        }
        return gameFlags
    }

    private fun isWhiteCheck(baseParametersGroup: BaseParametersGroup): Boolean {
        return PiecesHelper().isCheck(baseParametersGroup, 0)
    }

    private fun isWhiteCheckMate(baseParametersGroup: BaseParametersGroup): Boolean {
        return PiecesHelper().isCheckMate(baseParametersGroup, 0)
    }

    private fun isBlackCheck(baseParametersGroup: BaseParametersGroup): Boolean {
        return PiecesHelper().isCheck(baseParametersGroup, 1)
    }

    private fun isBlackCheckMate(baseParametersGroup: BaseParametersGroup): Boolean {
        return PiecesHelper().isCheckMate(baseParametersGroup, 1)
    }


}