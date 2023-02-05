// https://www.acmicpc.net/problem/12100
package solution12100__code2

import java.io.StreamTokenizer

private fun leftShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = Array(board.size) { board[it].clone() }

    for (y in newBoard.indices) {
        var i = 0
        for (j in 1 until newBoard.size) {
            // 검색 부분이 0 이면 패스
            if (newBoard[y][j] == 0) {
                continue
            }
            // 값을 복사하여 미리 0으로 삭제
            val tmp = newBoard[y][j]
            newBoard[y][j] = 0
            // i 값에 따른 이동
            when (newBoard[y][i]) {
                0 -> newBoard[y][i] = tmp
                tmp -> {
                    newBoard[y][i] = tmp * 2
                    i += 1
                }

                else -> {
                    i += 1
                    newBoard[y][i] = tmp
                }
            }
        }
    }
    return newBoard
}

private fun rightShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = Array(board.size) { board[it].clone() }

    for (y in newBoard.indices) {
        var i = newBoard[y].lastIndex
        for (j in newBoard.lastIndex - 1 downTo 0) {

            if (newBoard[y][j] == 0) {
                continue
            }

            val tmp = newBoard[y][j]
            newBoard[y][j] = 0
            when (newBoard[y][i]) {
                0 -> newBoard[y][i] = tmp
                tmp -> {
                    newBoard[y][i] = tmp * 2
                    i -= 1
                }

                else -> {
                    i -= 1
                    newBoard[y][i] = tmp
                }
            }
        }
    }
    return newBoard
}

private fun topShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = Array(board.size) { board[it].clone() }

    for (x in newBoard[0].indices) {
        var i = 0
        for (j in 1 until newBoard.size) {
            if (newBoard[j][x] == 0) {
                continue
            }

            val tmp = newBoard[j][x]
            newBoard[j][x] = 0
            when (newBoard[i][x]) {
                0 -> newBoard[i][x] = tmp
                tmp -> {
                    newBoard[i][x] = tmp * 2
                    i += 1
                }

                else -> {
                    i += 1
                    newBoard[i][x] = tmp
                }
            }
        }
    }
    return newBoard
}

private fun bottomShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = Array(board.size) { board[it].clone() }

    for (x in newBoard[0].indices) {
        var i = newBoard.lastIndex
        for (j in newBoard.lastIndex - 1 downTo 0) {
            if (newBoard[j][x] == 0) {
                continue
            }

            val tmp = newBoard[j][x]
            newBoard[j][x] = 0
            when (newBoard[i][x]) {
                0 -> newBoard[i][x] = tmp
                tmp -> {
                    newBoard[i][x] = tmp * 2
                    i -= 1
                }

                else -> {
                    i -= 1
                    newBoard[i][x] = tmp
                }
            }
        }
    }
    return newBoard
}

// 세로로 진행시 값이 바뀌는지 여부
private fun verticalOverlap(board: Array<IntArray>): Boolean {
    for (x in board[0].indices) {
        for (y in 0 until board.lastIndex) {
            if (board[y][x] == 0) {
                continue
            }
            if (board[y][x] == board[y + 1][x]) {
                return true
            }
        }
    }
    return false
}

// 가로로 진행시 값이 바뀌는지 여부
private fun horizontalOverlap(board: Array<IntArray>): Boolean {
    for (y in board.indices) {
        for (x in 0 until board[0].lastIndex) {
            if (board[y][x] == 0) {
                continue
            }
            if (board[y][x] == board[y][x + 1]) {
                return true
            }
        }
    }
    return false
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

    fun backTracking(n: Int, board: Array<IntArray>, vertical: Boolean) {
        if (n == 5) {
            maxValue = board.coerceAtLeast(maxValue)
            return
        }

        // 백트래킹의 분기 가지치기
        if (vertical) {
            val overlap = verticalOverlap(board)

            backTracking(n + 1, leftShift(board), false)
            backTracking(n + 1, rightShift(board), false)
            if (overlap) {
                backTracking(n + 1, topShift(board), true)
                backTracking(n + 1, bottomShift(board), true)
            }
        } else {
            val overlap = horizontalOverlap(board)

            backTracking(n + 1, topShift(board), true)
            backTracking(n + 1, bottomShift(board), true)
            if (overlap) {
                backTracking(n + 1, leftShift(board), false)
                backTracking(n + 1, rightShift(board), false)
            }
        }
    }

    backTracking(1, leftShift(initBoard), false)
    backTracking(1, rightShift(initBoard), false)
    backTracking(1, topShift(initBoard), true)
    backTracking(1, bottomShift(initBoard), true)
    println(maxValue)
}
