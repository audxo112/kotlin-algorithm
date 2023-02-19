// https://www.acmicpc.net/problem/11559

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = arrayOf(-1, 1, 0, 0)
private val dy = arrayOf(0, 0, -1, 1)
private val BLANK = '.'

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val arr = Array(12) {
        br.readLine().toCharArray()
    }
    var repeat = 0 // 연쇄

    while (true) {
        var count = 0
        for (i in arr.lastIndex downTo 0) {
            for (j in 0 until 6) {
                // 빈공간이 아니라면 탐색
                if (arr[i][j] != BLANK && bfs(Node5(i, j), arr)) {
                    count++
                }
            }
        }

        // 4개 이상의 뿌요 그룹이 하나도 없으몀
        if (count == 0) break

        // 뿌요 내려서 빈공간 채우기
        for (j in 0 until 6) {
            // 빈공간인 시작점 찾기
            var p = arr.lastIndex
            for (i in arr.lastIndex downTo 0) {
                if (arr[i][j] == BLANK) break
                p--
            }
            for (i in p - 1 downTo 0) {
                if (arr[i][j] != BLANK) {
                    arr[p--][j] = arr[i][j] //옮기기
                    arr[i][j] = BLANK
                }
            }
        }

        repeat++
    }

    println(repeat)
}

private fun bfs(first: Node5, arr: Array<CharArray>): Boolean {
    val que: Queue<Node5> = LinkedList()
    val visited = Array(12) { BooleanArray(6) }
    que.add(first)
    val puyos = mutableListOf(first)
    visited[first.x][first.y] = true

    while (que.isNotEmpty()) {
        val (x, y) = que.poll()

        for (i in 0 until 4) {
            val xx = x + dx[i]
            val yy = y + dy[i]

            if (xx !in arr.indices || yy !in arr[0].indices || visited[xx][yy] || arr[xx][yy] != arr[x][y]) continue

            val node = Node5(xx, yy)
            que.add(node)
            puyos.add(node) // 뿌요 담기
            visited[xx][yy] = true
        }
    }

    // 4개 이상이면 뿌요 터뜨리기
    if (puyos.size >= 4) {
        puyos.forEach {
            arr[it.x][it.y] = BLANK
        }
        return true
    }
    return false
}

data class Node5(val x: Int, val y: Int)