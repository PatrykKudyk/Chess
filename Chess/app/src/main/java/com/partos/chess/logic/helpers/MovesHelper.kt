package com.partos.chess.logic.helpers

class MovesHelper {

    fun createMovesList(): Array<Array<Boolean>> {
        return Array(8) { Array(8) { false } }
    }

    fun mergeMovesLists(
        movesList: Array<Array<Boolean>>,
        moves: Array<Array<Boolean>>
    ): Array<Array<Boolean>> {
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

    fun mergeMovesLists(
        moves1: Array<Array<Boolean>>,
        moves2: Array<Array<Boolean>>,
        moves3: Array<Array<Boolean>>,
        moves4: Array<Array<Boolean>>
    ): Array<Array<Boolean>> {
        val movesToReturn = createMovesList()
        for (i in 0..7) {
            for (j in 0..7) {
                if (moves1[i][j] || moves2[i][j] || moves3[i][j] || moves4[i][j]) {
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