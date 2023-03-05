// https://www.acmicpc.net/problem/16985

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var maze: Array<Array<IntArray>>
private val arr = Array(5) { Array(5) { IntArray(5) } }
private val visited = BooleanArray(5)
private var min = Int.MAX_VALUE
private val dz = intArrayOf(1, -1, 0, 0, 0, 0)
private val dx = intArrayOf(0, 0, 1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 0, 0, -1, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    maze = Array(5) {
        Array(5) {
            val st = StringTokenizer(br.readLine())
            IntArray(5) {
                st.nextToken().toInt()
            }
        }
    }

    dfs(0)

    println(if (min == Int.MAX_VALUE) -1 else min)
}

private fun dfs(idx: Int) {
    if (idx == 5) {
        bfs()
        return
    }

    for (i in maze.indices) {
        if (visited[i].not()) {
            visited[i] = true

            // 회전
            arr[idx] = maze[i]
            dfs(idx + 1)
            repeat(3) {
                arr[idx] = rotate(arr[idx])
                dfs(idx + 1)
            }

            visited[i] = false
        }
    }
}

private fun rotate(a: Array<IntArray>): Array<IntArray> {
    return Array(5) { i ->
        IntArray(5) { j ->
            a[4 - j][i]
        }
    }
}

private fun bfs() {
    if (arr[0][0][0] == 0 || arr[4][4][4] == 0) return
    val que: Queue<Node> = LinkedList()
    val v = Array(5) { Array(5) { BooleanArray(5) }}
    que.add(Node(0, 0, 0, 0))
    v[0][0][0] = true

    while (que.isNotEmpty()) {
        val (z, x, y, cnt) = que.poll()

        for (i in dx.indices) {
            val zz = z + dz[i]
            val xx = x + dx[i]
            val yy = y + dy[i]

            if (zz !in arr.indices || xx !in arr[0].indices || yy !in arr[0][0].indices || v[zz][xx][yy] || arr[zz][xx][yy] == 0) continue

            if (zz == 4 && xx == 4 && yy == 4 && cnt + 1 < min) {
                min = cnt + 1 // 12가 항상 최소
                return
            }
            que.add(Node(zz, xx, yy, cnt + 1))
            v[zz][xx][yy] = true
        }
    }
}

private data class Node(val z: Int, val x: Int, val y: Int, val cnt: Int)