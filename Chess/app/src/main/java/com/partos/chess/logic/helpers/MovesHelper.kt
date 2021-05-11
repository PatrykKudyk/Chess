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
        moves: Array<Array<Boolean>>,
        movesList: ArrayList<Array<Array<Boolean>>>
    ): Array<Array<Boolean>> {
        for (i in 0..7) {
            for (j in 0..7) {
                if (isAnyTrue(movesList, i, j))
                    moves[i][j] = true;
            }
        }
        return moves
    }

    private fun isAnyTrue(movesList: ArrayList<Array<Array<Boolean>>>, i: Int, j: Int): Boolean {
        for (array in movesList){
            if (array[i][j])
                return true
        }
        return false
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