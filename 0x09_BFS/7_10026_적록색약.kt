// [적록색약](https://www.acmicpc.net/problem/10026)
package `kotlin-algorithm`.`0x09_BFS`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var arr: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private val dx = arrayOf(-1, 1, 0, 0)
private val dy = arrayOf(0, 0, -1, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    arr = Array(n) {
        br.readLine().toCharArray()
    }
    visited = Array(n) { BooleanArray(n) }
    var redGreenPills = 0
    var antiRedGreenPills = 0

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (visited[i][j].not()) {
                bfs(i, j)
                antiRedGreenPills++
            }
            // 이미 방문한 것들 변경
            if (arr[i][j] == 'G') {
                arr[i][j] = 'R'
            }
        }
    }

    visited = Array(n) { BooleanArray(n) }

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (visited[i][j].not()) {
                bfs(i, j)
                redGreenPills++
            }
        }
    }

    println("$antiRedGreenPills $redGreenPills")
}

private fun bfs(i: Int, j: Int) {
    val queue = LinkedList<Node>()
    queue.push(Node(i, j, arr[i][j]))
    visited[i][j] = true

    while (queue.isNotEmpty()) {
        val node = queue.poll()
        val originColor = node.color

        for (k in dx.indices) {
            val x = node.x + dx[k]
            val y = node.y + dy[k]

            if (x in arr.indices && y in arr[0].indices && visited[x][y].not()) {
                if (arr[x][y] == originColor) {
                    queue.push(Node(x, y, arr[x][y]))
                    visited[x][y] = true
                }
            }
        }
    }
}

private data class Node(val x: Int, val y: Int, val color: Char)