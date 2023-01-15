// https://www.acmicpc.net/problem/2583
package `kotlin-algorithm`.`0x09_BFS`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

private val dx = arrayOf(1, -1, 0, 0)
private val dy = arrayOf(0, 0, 1, -1)
private lateinit var visited: Array<BooleanArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    visited = Array(m) {
        BooleanArray(n)
    }

    repeat(k) {
        st = StringTokenizer(br.readLine())
        val sX = st.nextToken().toInt()
        val sY = st.nextToken().toInt()
        val eX = st.nextToken().toInt()
        val eY = st.nextToken().toInt()
        // 상하 반전
        for (i in sY until eY) {
            for (j in sX until eX) {
                if (visited[i][j].not()) visited[i][j] = true
            }
        }
    }

    var boundary = 0
    val res = mutableListOf<Int>()
    for (i in 0 until m) {
        for (j in 0 until n) {
            if (visited[i][j].not()) {
                boundary++
                res.add(bfs(Node11(i, j)))
            }
        }
    }

    res.sort()
    val sb = StringBuilder()
    sb.appendLine(boundary)
    res.forEach {
        sb.append("$it ")
    }
    println(sb.toString())
}

private fun bfs(start: Node11): Int {
    val queue: Queue<Node11> = LinkedList()
    var count = 0
    queue.add(start)
    visited[start.x][start.y] = true
    count++

    while (queue.isNotEmpty()) {
        val node = queue.poll()

        for (i in dx.indices) {
            val x = node.x + dx[i]
            val y = node.y + dy[i]

            if (x !in visited.indices || y !in visited[0].indices || visited[x][y]) continue

            visited[x][y] = true
            queue.add(Node11(x, y))
            count++
        }
    }

    return count
}

data class Node11(val x: Int, val y: Int)