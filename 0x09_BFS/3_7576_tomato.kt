package `kotlin-algorithm`.`0x09_BFS`

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (m, n) = readLine().split(" ").map { it.toInt() }
    val box = Array(n) { Array(m ) { 0 } }
    val dx = arrayOf(0, 0, -1, 1)
    val dy = arrayOf(-1, 1, 0, 0)

    for (i in 0 until n) {
        val line = readLine().split(" ").map { it.toInt() }
        for (j in 0 until m) {
            box[i][j] = line[j]
        }
    }

    val bfs =  {
        val que = LinkedList<Pair<Int, Int>>()
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (box[i][j] == 1) {
                    que.offer(i to j)
                }
            }
        }

        while (que.isNotEmpty()) {
            val (a, b) = que.poll()

            for (i in 0 until 4){
                val a1 = a + dx[i]
                val b1 = b + dy[i]

                if (a1 < 0 || a1 >= n || b1 < 0 || b1 >= m || box[a1][b1] != 0) continue

                box[a1][b1] = box[a][b] + 1
                que.offer(a1 to b1)
            }
        }
    }

    bfs()

    var max = -1
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (box[i][j] == 0) {
                println(-1)
                return
            }

            if (max < box[i][j]) {
                max = box[i][j]
            }
        }
    }
    println(max - 1)
}
