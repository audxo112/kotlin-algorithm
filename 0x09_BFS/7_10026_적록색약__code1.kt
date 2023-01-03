//https://www.acmicpc.net/problem/10026
package solution10026__code1

import java.util.LinkedList
import java.util.Queue

class Node(val x: Int, val y: Int)

fun main() = System.`in`.bufferedReader().run {
    val n = readLine().toInt()
    val graph = List(n) {
        readLine().toCharArray()
    }
    val nVisited = List(n) { BooleanArray(n) }
    val sVisited = List(n) { BooleanArray(n) }
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, -1, 0, 1)

    fun bfs(sx: Int, sy: Int, visited: List<BooleanArray>, flag: Boolean) {
        val color = graph[sy][sx]
        visited[sy][sx] = true

        val queue: Queue<Node> = LinkedList()
        queue.add(Node(sx, sy))

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            for (i in 0 until 4) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                if (nx !in 0 until n || ny !in 0 until n || visited[ny][nx]) {
                    continue
                }
                if (color == graph[ny][nx] || (flag && ((color == 'R' && graph[ny][nx] == 'G') || (color == 'G' && graph[ny][nx] == 'R')))) {
                    visited[ny][nx] = true
                    queue.add(Node(nx, ny))
                }
            }
        }
    }

    var nCount = 0
    var sCount = 0

    for (y in 0 until n) for (x in 0 until n) {
        if (!nVisited[y][x]) {
            nCount += 1
            bfs(x, y, nVisited, false)
        }

        if (!sVisited[y][x]) {
            sCount += 1
            bfs(x, y, sVisited, true)
        }
    }
    println("$nCount $sCount")
}