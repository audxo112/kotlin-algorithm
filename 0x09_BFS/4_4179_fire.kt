package `kotlin-algorithm`.`0x09_BFS`

import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val (r, c) = readLine().split(" ").map { it.toInt() }
    val maze = Array(r) { CharArray(c) }
    val dx = arrayOf(-1, 1, 0, 0)
    val dy = arrayOf(0, 0, -1, 1)

    for (i in 0 until r) {
        val list = readLine().toCharArray()
        for (j in 0 until c) {
            maze[i][j] = list[j]
        }
    }

    val bfs = bfs@ {
        val queueJ = LinkedList<Triple<Int, Int, Int>>()
        val queueF = LinkedList<Pair<Int, Int>>()

        for (i in 0 until r) {
            for (j in 0 until c) {
                when (maze[i][j]) {
                    'J' -> {
                        queueJ.offer(Triple(i, j, 0))
                        maze[i][j] = 'J'
                    }
                    'F' -> {
                        queueF.offer(i to j)
                        maze[i][j] = 'F'
                    }
                }
            }
        }

        var res = -1
        while (queueJ.isNotEmpty()) {
            // 모든 불 한 번 번짐
            repeat(queueF.size) {
                val (aF, bF) = queueF.poll()
                for (i in 0 until 4) {
                    val a1 = aF + dx[i]
                    val b1 = bF + dy[i]

                    if (a1 < 0 || a1 >= r || b1 < 0 || b1 >= c || maze[a1][b1] == 'F' || maze[a1][b1] == '#') continue

                    maze[a1][b1] = 'F'
                    queueF.offer(a1 to b1)
                }
            }

            // 이동
            repeat(queueJ.size) {
                val (aJ, bJ, distance) = queueJ.poll()
                for (i in 0 until 4) {
                    val a1 = aJ + dx[i]
                    val b1 = bJ + dy[i]

                    if (a1 < 0 || a1 >= r || b1 < 0 || b1 >= c) {
                        res = distance + 1
                        return@bfs res
                    }

                    if (maze[a1][b1] == 'F' || maze[a1][b1] == '#' || maze[a1][b1] == 'J') continue

                    maze[a1][b1] = 'J'
                    queueJ.offer(Triple(a1, b1, distance + 1))
                }
            }
        }

        return@bfs res
    }

    bfs().let {
        if(it == -1) {
            println("IMPOSSIBLE")
        } else {
            println(it)
        }
    }

}