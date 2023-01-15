// https://www.acmicpc.net/problem/5427

package `kotlin-algorithm`.`0x09_BFS`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = arrayOf(-1, 1, 0, 0)
private val dy = arrayOf(0, 0, -1, 1)
private const val WALL = '#'

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()
    val IMPOSSIBLE = "IMPOSSIBLE"

    repeat(br.readLine().toInt()) {
        val st = StringTokenizer(br.readLine())
        val w = st.nextToken().toInt()
        val h = st.nextToken().toInt()
        val board = Array(h) { i ->
            br.readLine().toCharArray()
        }

        val humanQueue: Queue<Node10> = LinkedList()
        val fireQueue: Queue<Node10> = LinkedList()
        for (i in 0 until h) {
            for (j in 0 until w) {
                when (board[i][j]) {
                    '*' -> {
                        fireQueue.add(Node10(i, j))
                        board[i][j] = WALL
                    }
                    '@' -> {
                        humanQueue.add(Node10(i, j))
                    }
                    else -> continue
                }
            }
        }

        // 처음부터 가장자리에 있는지 검사!!!
        val (x, y) = humanQueue.peek()
        if (x == 0 || x == board.lastIndex || y == 0 || y == board[0].lastIndex) {
            sb.appendLine(1)
        } else {
            sb.appendLine(bfs(board, humanQueue, fireQueue) ?: IMPOSSIBLE)
        }
    }

    println(sb.toString())
}

private fun bfs(board: Array<CharArray>, humanQueue: Queue<Node10>, fireQueue: Queue<Node10>): Int? {
    var count = 0

    while (humanQueue.isNotEmpty()) {
        count++

        repeat(fireQueue.size) {
            val node = fireQueue.poll()

            for (i in dx.indices) {
                val x = node.x + dx[i]
                val y = node.y + dy[i]

                if (x !in board.indices || y !in board[0].indices || board[x][y] == WALL) continue

                fireQueue.add(Node10(x, y))
                board[x][y] = WALL
            }
        }

        repeat(humanQueue.size) {
            val node = humanQueue.poll()

            for (i in dx.indices) {
                val x = node.x + dx[i]
                val y = node.y + dy[i]

                if (x !in board.indices || y !in board[0].indices || board[x][y] != '.') continue

                if (x == 0 || x == board.lastIndex || y == 0 || y == board[0].lastIndex) {
                    return count + 1
                }

                humanQueue.add(Node10(x, y))
                board[x][y] = '@'
            }
        }
    }

    return null
}

data class Node10(val x: Int, val y: Int)