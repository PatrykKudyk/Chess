package com.partos.chess.logic.helpers

import com.partos.chess.enums.PieceType

class MovesHelper{

    fun createMovesList(): Array<Array<Boolean>> {
        val array1 = Array(8) {false}
        val array2 = Array(8) {false}
        val array3 = Array(8) {false}
        val array4 = Array(8) {false}
        val array5 = Array(8) {false}
        val array6 = Array(8) {false}
        val array7 = Array(8) {false}
        val array8 = Array(8) {false}
        return arrayOf(array1, array2, array3, array4, array5, array6, array7, array8)
    }

    fun mergeMovesLists(movesList: Array<Array<Boolean>>, moves: Array<Array<Boolean>>): Array<Array<Boolean>> {
        val movesToReturn = createMovesList()
        for (i in 0..7) {
            for (j in 0..7) {
                if (movesList[i][j] || moves[i][j]) {
                    movesToReturn[i][j] = true
                }
            }
        }
        return movesToReturn
    }

    fun checkIsAnyMovePossible(moves: Array<Array<Boolean>>): Boolean {
        for (array in moves) {
            for (move in array) {
                if (move) {
                    return true
                }
            }
        }
        return false
    }

    fun getMovesNumber(moves: Array<Array<Boolean>>): Int {
        var movesNumber = 0
        for (array in moves) {
            for (move in array) {
                if (move) {
                    movesNumber++
                }
            }
        }
        return movesNumber
    }
}