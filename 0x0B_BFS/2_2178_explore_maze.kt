package `kotlin-algorithm`.`0x0B_BFS`

import java.util.*

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val dx = arrayOf(0, 0, -1, 1)
    val dy = arrayOf(-1, 1, 0, 0)
    val maze = mutableListOf<List<Int>>()
    val distance = Array(n) { Array(m) { 0 } }
    val visited = Array(n) { Array(m) { false } }

    for (i in 0 until n) {
        maze.add(readLine().toCharArray().map {
            it - '0'
        })
    }

    val bfs = { x: Int, y: Int ->
        val que = LinkedList<Pair<Int, Int>>()
        que.add(Pair(x, y))
        visited[x][y] = true
        distance[x][y] = 1

        while (!que.isEmpty()) {
            val (a, b) = que.poll()

            for (i in dx.indices) {
                val a1 = a + dx[i]
                val b1 = b + dy[i]

                if (a1 < 0 || a1 >= n || b1 < 0 || b1 >= m || maze[a1][b1] != 1 || visited[a1][b1]) continue

                distance[a1][b1] = distance[a][b] + 1
                visited[a1][b1] = true
                que.add(Pair(a1, b1))
            }
        }
    }

    bfs(0, 0)
    println(distance[n - 1][m - 1])
}