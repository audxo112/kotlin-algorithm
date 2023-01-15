// [나이트의 이동](https://www.acmicpc.net/problem/7562)

package `kotlin-algorithm`.`0x09_BFS`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

private val dx = arrayOf(-2, -2, -1, -1, 1, 1, 2, 2)
private val dy = arrayOf(1, -1, 2, -2, 2, -2, 1, -1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tc = br.readLine().toInt()
    val sb = StringBuilder()

    repeat(tc) {
        val n = br.readLine().toInt()
        var st = StringTokenizer(br.readLine())
        val start = Box(st.nextToken().toInt(), st.nextToken().toInt())
        st = StringTokenizer(br.readLine())
        val end = Box(st.nextToken().toInt(), st.nextToken().toInt())

        if (start == end) {
            sb.appendLine(0)
        } else {
            sb.appendLine(bfs(n, start, end))
        }
    }

    println(sb.toString())
}

private fun bfs(n: Int, start: Box, end: Box): Int {
    val visited = Array(n) {
        BooleanArray(n)
    }
    val que: Queue<Box> = LinkedList()
    visited[start.x][start.y] = true
    que.add(start)

    var count = 0
    while (que.isNotEmpty()) {
        count++
        repeat(que.size) {
            val node = que.poll()

            for (i in dx.indices) {
                val x = node.x + dx[i]
                val y = node.y + dy[i]

                if (x !in 0 until n || y !in 0 until n || visited[x][y]) continue

                val next = Box(x, y)
                if (next == end) {
                    return count
                }

                visited[x][y] = true
                que.add(next)
            }
        }
    }

    return 0
}

private data class Box(val x: Int, val y: Int)