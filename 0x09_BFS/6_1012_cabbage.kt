package `kotlin-algorithm`.`0x09_BFS`

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val dx = arrayOf(-1, 1, 0, 0)
    val dy = arrayOf(0, 0, -1, 1)

    val bfs = { array: Array<IntArray>, visited: Array<BooleanArray>, x: Int, y: Int ->
        val que = LinkedList<Pair<Int, Int>>()
        que.offer(x to y)
        visited[x][y] = true

        while (que.isNotEmpty()) {
            val (a, b) = que.poll()

            for (i in dx.indices) {
                val aa = a + dx[i]
                val bb = b + dy[i]

                if (aa < 0 || aa >= array.size || bb < 0 || bb >= array[0].size || array[aa][bb] == 0 || visited[aa][bb]) continue

                que.offer(aa to bb)
                visited[aa][bb] = true
            }
        }
    }

    repeat(readLine().toInt()) { _ ->
        val (m, n, k) = readLine().split(" ").map { it.toInt() }
        var array = Array(n) { IntArray(m) }
        var visited = Array(n) { BooleanArray(m) }

        repeat(k) {
            val (y, x) = readLine().split(" ").map { it.toInt() }
            array[x][y] = 1
        }

        var count = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (array[i][j] == 1 && !visited[i][j]) {
                    count++
                    bfs(array, visited, i, j)
                }
            }
        }

        println(count)
    }
}

fun dfs(array: Array<IntArray>, visited: Array<BooleanArray>, x: Int, y: Int, dx: Array<Int>, dy: Array<Int>) {
    if (visited[x][y]) return

    visited[x][y] = true

    for (i in dx.indices) {
        val xx = x + dx[i]
        val yy = y + dy[i]

        if (xx < 0 || xx >= array.size || yy < 0 || yy >= array[0].size || array[xx][yy] == 0 || visited[xx][yy]) continue

        dfs(array, visited, xx, yy, dx, dy)
    }

}
