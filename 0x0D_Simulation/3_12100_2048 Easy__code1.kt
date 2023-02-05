// https://www.acmicpc.net/problem/12100
package solution12100__code1

import java.io.StreamTokenizer

private fun leftShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = Array(board.size) { board[it].clone() }

    for (y in newBoard.indices) {
        var i = 0
        var j = 0
        while (j < newBoard[y].lastIndex) {
            j += 1
            if (newBoard[y][j] == 0) {
                continue
            }

            if (newBoard[y][i] == 0) {
                newBoard[y][i] = newBoard[y][j]
                newBoard[y][j] = 0
            } else {
                if (newBoard[y][i] == newBoard[y][j]) {
                    newBoard[y][i] *= 2
                    newBoard[y][j] = 0
                } else {
                    if (i + 1 != j) {
                        newBoard[y][i + 1] = newBoard[y][j]
                        newBoard[y][j] = 0
                    }
                }
                i += 1
            }
        }
    }
    return newBoard
}

private fun rightShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = Array(board.size) { board[it].clone() }

    for (y in newBoard.indices) {
        var i = newBoard[y].lastIndex
        var j = newBoard[y].lastIndex
        while (j > 0) {
            j -= 1
            if (newBoard[y][j] == 0) {
                continue
            }

            if (newBoard[y][i] == 0) {
                newBoard[y][i] = newBoard[y][j]
                newBoard[y][j] = 0
            } else {
                if (newBoard[y][i] == newBoard[y][j]) {
                    newBoard[y][i] *= 2
                    newBoard[y][j] = 0
                } else {
                    if (i - 1 != j) {
                        newBoard[y][i - 1] = newBoard[y][j]
                        newBoard[y][j] = 0
                    }
                }
                i -= 1
            }
        }
    }
    return newBoard
}

private fun topShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = Array(board.size) { board[it].clone() }

    for (x in board[0].indices) {
        var i = 0
        var j = 0
        while (j < newBoard.lastIndex) {
            j += 1
            if (newBoard[j][x] == 0) {
                continue
            }

            if (newBoard[i][x] == 0) {
                newBoard[i][x] = newBoard[j][x]
                newBoard[j][x] = 0
            } else {
                if (newBoard[i][x] == newBoard[j][x]) {
                    newBoard[i][x] *= 2
                    newBoard[j][x] = 0
                } else {
                    if (i + 1 != j) {
                        newBoard[i + 1][x] = newBoard[j][x]
                        newBoard[j][x] = 0
                    }
                }
                i += 1
            }
        }
    }
    return newBoard
}

private fun bottomShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = Array(board.size) { board[it].clone() }

    for (x in board[0].indices) {
        var i = board.lastIndex
        var j = board.lastIndex
        while (j > 0) {
            j -= 1
            if (newBoard[j][x] == 0) {
                continue
            }

            if (newBoard[i][x] == 0) {
                newBoard[i][x] = newBoard[j][x]
                newBoard[j][x] = 0
            } else {
                if (newBoard[i][x] == newBoard[j][x]) {
                    newBoard[i][x] *= 2
                    newBoard[j][x] = 0
                } else {
                    if (i - 1 != j) {
                        newBoard[i - 1][x] = newBoard[j][x]
                        newBoard[j][x] = 0
                    }
                }
                i -= 1
            }
        }
    }
    return newBoard
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    fun Array<IntArray>.coerceAtLeast(least: Int): Int {
        var result = least
        for (line in this) for (value in line) {
            if (result < value) {
                result = value
            }
        }
        return result
    }

    val n = input()
    val initBoard = Array(n) { IntArray(n) { input() } }
    var maxValue = 0

    fun backTracking(n: Int, board: Array<IntArray>) {
        if (n == 5) {
            maxValue = board.coerceAtLeast(maxValue)
            return
        }

        backTracking(n + 1, leftShift(board))
        backTracking(n + 1, rightShift(board))
        backTracking(n + 1, topShift(board))
        backTracking(n + 1, bottomShift(board))
    }

    backTracking(0, initBoard)
    println(maxValue)
}
