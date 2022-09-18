package `kotlin-algorithm`.`0x09_BFS`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

lateinit var dx: Array<Int>
lateinit var dy: Array<Int>
lateinit var board: Array<Array<Int>>
lateinit var visited: Array<Array<Boolean>>
var max = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    dx = arrayOf(0, 0, -1, 1)
    dy = arrayOf(-1, 1, 0, 0)
    board = Array(n) { Array(m) { 0 } }
    visited = Array(n) { Array(m) { false } }

    for (i in 0 until n) {
        st = StringTokenizer(br.readLine())
        for (j in 0 until m) {
            board[i][j] = st.nextToken().toInt()
        }
    }

    var count = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (board[i][j] == 1 && !visited[i][j]) {
                count++
                max = max.coerceAtLeast(bfs(i, j, n, m))
            }
        }
    }

    println(count)
    println(max)
}

fun bfs(x: Int, y: Int, n: Int, m: Int): Int {
    val que = LinkedList<Pair<Int, Int>>()
    que.add(Pair(x, y))
    visited[x][y] = true
    var count = 1

    while (!que.isEmpty()) {
        val node = que.poll()

        for (i in 0 until 4) {
            val x1 = node.first + dx[i]
            val y1 = node.second + dy[i]

            if (x1 < 0 || x1 >= n || y1 < 0 || y1 >= m || board[x1][y1] != 1 || visited[x1][y1]) continue

            visited[x1][y1] = true
            count++
            que.add(Pair(x1, y1))
        }
    }

    return count
}
