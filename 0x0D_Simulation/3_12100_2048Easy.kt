// https://www.acmicpc.net/problem/12100

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val LIMIT = 5
private var max = Int.MIN_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val board = Array(n) {
        val st = StringTokenizer(br.readLine())
        IntArray(n) {
            st.nextToken().toInt()
        }
    }

    dfs(board, 0)

    println(max)
}

private fun dfs(board: Array<IntArray>, depth: Int) {
    if (depth == LIMIT) {
        board.forEach { arr ->
            arr.forEach { num ->
                if (num > max) {
                    max = num
                }
            }
        }
        return
    }

    repeat(4) {
        // 이동
        val res = moveBoard(copyBoard(board), it)
        // 다음 진행
        dfs(res, depth + 1)
    }
}

private fun copyBoard(board: Array<IntArray>): Array<IntArray> {
    return Array(board.size) {
        board[it].clone()
    }
}

private fun moveBoard(board: Array<IntArray>, dir: Int): Array<IntArray> {
    when(dir) {
        0 -> {
            // UP
            for (j in board.indices) {
                // 반영할 인덱스
                var current = 0
                for (i in 1 until board.size) {
                    if (board[i][j] != 0) {
                        val tmp = board[i][j]
                        board[i][j] = 0
                        when {
                            board[current][j] == 0 -> board[current][j] = tmp // 현재 자리로 이동
                            board[current][j] == tmp -> board[current++][j] = tmp * 2 // 현재 자리에 합치기
                            else -> board[++current][j] = tmp // 숫자가 다를 경우 다음 인덱스에 반영
                        }
                    }
                }
            }
        }
        1 -> {
            // DOWN
            for (j in board.indices) {
                var current = board.lastIndex
                for (i in board.lastIndex - 1 downTo 0) {
                    if (board[i][j] != 0) {
                        val tmp = board[i][j]
                        board[i][j] = 0
                        when {
                            board[current][j] == 0 -> board[current][j] = tmp
                            board[current][j] == tmp -> board[current--][j] = tmp * 2
                            else -> board[--current][j] = tmp
                        }
                    }
                }
            }
        }
        2 -> {
            // LEFT
            for (i in board.indices) {
                var current = board.lastIndex
                for (j in board.lastIndex - 1 downTo 0) {
                    if (board[i][j] != 0) {
                        val tmp = board[i][j]
                        board[i][j] = 0
                        when {
                            board[i][current] == 0 -> board[i][current] = tmp
                            board[i][current] == tmp -> board[i][current--] = tmp * 2
                            else -> board[i][--current] = tmp
                        }
                    }
                }
            }
        }
        else -> {
            // RIGHT
            for (i in board.indices) {
                var current = 0
                for (j in 1 until board.size) {
                    if (board[i][j] != 0) {
                        val tmp = board[i][j]
                        board[i][j] = 0
                        when {
                            board[i][current] == 0 -> board[i][current] = tmp
                            board[i][current] == tmp -> board[i][current++] = tmp * 2
                            else -> board[i][++current] = tmp
                        }
                    }
                }
            }
        }
    }

    return board
}