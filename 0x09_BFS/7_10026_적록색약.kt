package `kotlin-algorithm`.`0x09_BFS`

import java.util.LinkedList
import java.util.Queue

private val dx = listOf(0, 0, 1, -1)
private val dy = listOf(1, -1, 0, 0)
private lateinit var graph: List<CharArray>
private lateinit var visited: List<BooleanArray>

fun main() = System.`in`.bufferedReader().run {
    val n = readLine().toInt()
    graph = List(n) {
        readLine().toCharArray()
    }
    visited = List(n) {
        BooleanArray(n)
    }

    var cntColorBlind = 0
    var cntNormal = 0

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (visited[j][i].not()) {
                bfs(i, j)
                cntNormal += 1
            }
            if (graph[j][i] == 'G') {
                graph[j][i] = 'R'
            }
        }
    }

    visited = List(n) {
        BooleanArray(n)
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (visited[j][i].not()) {
                bfs(i, j)
                cntColorBlind += 1
            }
        }
    }

    println("$cntNormal $cntColorBlind")
}

private fun bfs(x: Int, y: Int) {
    val queue: Queue<Node> = LinkedList()


    val nowColor = graph[y][x]
    queue.add(Node(x, y))

    visited[y][x] = true
    while (queue.isNotEmpty()) {
        val now = queue.poll()
        for (i in 0 until 4) {
            val nx = now.x + dx[i]
            val ny = now.y + dy[i]

            if (nx < 0 || nx > graph.lastIndex) continue
            if (ny < 0 || ny > graph.lastIndex) continue

            if (visited[ny][nx].not() && graph[ny][nx] == nowColor) {
                visited[ny][nx] = true
                queue.add(Node(nx, ny))
            }
        }
    }
}

data class Node(val x: Int, val y: Int)