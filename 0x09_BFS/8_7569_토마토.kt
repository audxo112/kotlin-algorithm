// [토마토](https://www.acmicpc.net/problem/7569)

package `kotlin-algorithm`.`0x09_BFS`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

private val dx = arrayOf(-1, 1, 0, 0, 0, 0)
private val dy = arrayOf(0, 0, -1, 1, 0, 0)
private val dh = arrayOf(0, 0, 0, 0, -1, 1)
private lateinit var arr: Array<Array<IntArray>>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()
    val h = st.nextToken().toInt()
    arr = Array(h) {
        Array(n) {
            st = StringTokenizer(br.readLine())
            IntArray(m) {
                st.nextToken().toInt()
            }
        }
    }

    val queue = LinkedList<Square>()
    for (k in 0 until h) {
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (arr[k][i][j] == 1) {
                    queue.add(Square(i, j, k))
                }
            }
        }
    }

    bfs(queue)

    var days = Int.MIN_VALUE
    for (k in 0 until h) {
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (arr[k][i][j] == 0) {
                    println(-1)
                    return
                }
                days = days.coerceAtLeast(arr[k][i][j])
            }
        }
    }

    println(days - 1) // 2부터 시작하여서
}

private fun bfs(queue: Queue<Square>) {
    while (queue.isNotEmpty()) {
        val node = queue.poll()

        // 상하좌우 방문
        for (i in dx.indices) {
            val x = node.x + dx[i]
            val y = node.y + dy[i]
            val z = node.z + dh[i]

            if (z in arr.indices && x in arr[0].indices && y in arr[0][0].indices && arr[z][x][y] == 0) {
                queue.add(Square(x, y, z))
                arr[z][x][y] = arr[node.z][node.x][node.y] + 1
            }
        }
    }
}

private data class Square(val x: Int, val y: Int, val z: Int)