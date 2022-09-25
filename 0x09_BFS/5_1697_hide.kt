package `kotlin-algorithm`.`0x09_BFS`

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (s, b) = readLine().split(" ").map { it.toInt() }
    val distance = IntArray(100001)
    val dx = arrayOf(-1, 1, 2)

    val bfs = bfs@ { x: Int ->
        val que = LinkedList<Int>()
        que.offer(x)
        distance[x] = 1

        while (que.isNotEmpty()) {
            val a = que.poll()

            for (i in dx.indices) {
                val a1 = if (i == dx.lastIndex) a * dx[i] else a + dx[i]

                if (a1 < 0 || a1 >= distance.size || distance[a1] != 0) continue

                if (a1 == b) {
                    distance[a1] = distance[a] + 1
                    return@bfs
                }

                distance[a1] = distance[a] + 1
                que.offer(a1)
            }
        }
    }

    bfs(s)

    println(distance[b] - 1)

}