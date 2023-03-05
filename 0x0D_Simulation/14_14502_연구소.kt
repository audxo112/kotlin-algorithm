// https://www.acmicpc.net/problem/14502

package `kotlin-algorithm`.`0x0D_Simulation`.`14`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = arrayOf(-1, 1, 0, 0)
private val dy = arrayOf(0, 0, -1, 1)
private lateinit var arr: Array<IntArray>
private var max = Int.MIN_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    arr = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(m) {
            st.nextToken().toInt()
        }
    }

    dfs(0, 0, 0)

    println(max)
}

private fun dfs(wallCnt: Int, x: Int, y: Int) {
    if (wallCnt == 3) {
        bfs(deepCopyArr())
        return
    }

    for (i in x  until arr.size) {
        for (j in 0 until arr[0].size) {
            if (arr[i][j] == 0) {
                arr[i][j] = 1
                if (j + 1 != arr[0].size) {
                    dfs(wallCnt + 1, i, j + 1)
                } else {
                    dfs(wallCnt + 1, i + 1, 0)
                }
                arr[i][j] = 0
            }
        }
    }
}

private fun deepCopyArr(): Array<IntArray> {
    return Array(arr.size) {
        arr[it].clone()
    }
}

private fun bfs(a: Array<IntArray>) {
    val que: Queue<Node> = LinkedList()
    for (i in a.indices) {
        for (j in a[0].indices) {
            if (a[i][j] == 2) {
                que.add(Node(i, j))
            }
        }
    }

    while (que.isNotEmpty()) {
        val (x, y) = que.poll()

        for (i in dx.indices) {
            val xx = x + dx[i]
            val yy = y + dy[i]

            if (xx !in a.indices || yy !in a[0].indices || a[xx][yy] != 0) continue

            a[xx][yy] = 2
            que.add(Node(xx, yy))
        }
    }

    var count = 0
    for (i in a.indices) {
        for (j in a[0].indices) {
            if (a[i][j] == 0) {
                count++
            }
        }
    }

    max = max.coerceAtLeast(count)
}

private data class Node(val x: Int, val y: Int)