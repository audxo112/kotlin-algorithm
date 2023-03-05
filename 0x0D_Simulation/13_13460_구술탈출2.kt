// https://acmicpc.net/problem/13460

package `kotlin-algorithm`.`0x0D_Simulation`.`13`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

private val dx = arrayOf(0, 0, 1, -1)
private val dy = arrayOf(1, -1, 0, 0)
private lateinit var board: Array<CharArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val rm = Node()
    val bm = Node()
    val hole = Node()

    board = Array(n) { i ->
        val chars = br.readLine().toCharArray()
        for (j in chars.indices) {
            when {
                rm.x != -1 && bm.x != -1 && hole.x != -1 -> break
                chars[j] == 'R' -> {
                    rm.x = i
                    rm.y = j
                }
                chars[j] == 'B' -> {
                    bm.x = i
                    bm.y = j
                }
                chars[j] == 'O' -> {
                    hole.x = i
                    hole.y = j
                }
            }
        }
        chars
    }

    println(bfs(Marbles(rm, bm), hole))
}

private fun bfs(start: Marbles, hole: Node): Int {
    val que: Queue<Marbles> = LinkedList()
    val visited = Array(10) { Array(10) { Array(10) { BooleanArray(10) } } }
    que.add(start)
    visited[start.red.x][start.red.y][start.blue.x][start.blue.y] = true
    var count = 0

    while (que.isNotEmpty() && count <= 10) {
        repeat(que.size) {
            val (rm, bm) = que.poll()

            if (rm == hole) return count

            for (i in dx.indices) {
                val nrm = moveMarble(rm, i)
                val nbm = moveMarble(bm, i)

                if (nbm == hole) continue // 파란 구슬이 구멍에 들어갔을 때 무시

                if (nrm == nbm) {
                    // 빨간 구슬이 더 움직였으면 더 뒤에 있었던 것
                    if (abs(nrm.x - rm.x) + abs(nrm.y - rm.y) > abs(nbm.x - bm.x) + abs(nbm.y - bm.y)) {
                        nrm.x -= dx[i]
                        nrm.y -= dy[i]
                    } else {
                        nbm.x -= dx[i]
                        nbm.y -= dy[i]
                    }
                }

                if (visited[nrm.x][nrm.y][nbm.x][nbm.y].not()) {
                    que.add(Marbles(nrm, nbm))
                    visited[nrm.x][nrm.y][nbm.x][nbm.y] = true
                }
            }
        }
        count++
    }

    return -1
}

private fun moveMarble(node: Node, dir: Int): Node {
    var x = node.x
    var y = node.y

    // 구멍이면 더 이동하지 않음
    while (board[x][y] != 'O') {
        val xx = x + dx[dir]
        val yy = y + dy[dir]

        if (xx !in board.indices || yy !in board[0].indices || board[xx][yy] == '#') break

        x = xx
        y = yy
    }

    return Node(x, y)
}

private data class Marbles(val red: Node, val blue: Node)

private data class Node(var x: Int = -1, var y: Int = -1)