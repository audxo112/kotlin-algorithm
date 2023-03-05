// https://www.acmicpc.net/problem/14502
package solution14502__code3

import java.io.StreamTokenizer
import kotlin.collections.ArrayList

private class Node(val x: Int, val y: Int)

private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {

    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    val map = Array(n) { IntArray(m) { input() } }
    val copyMap = Array(n) { IntArray(m) }
    val candiList = ArrayList<Node>(n * m)
    val initVirus = ArrayList<Node>(n * m)

    for (y in 0 until n) {
        for (x in 0 until m) {
            when (map[y][x]) {
                2 -> {
                    initVirus.add(Node(x, y))
                }

                0 -> {
                    candiList.add(Node(x, y))
                }
            }
        }
    }

    fun copy() {
        for (y in 0 until n) {
            for (x in 0 until m) {
                copyMap[y][x] = map[y][x]
            }
        }
    }

    var minVirus = Int.MAX_VALUE
    var virus = 0

    fun dfs(x: Int, y: Int) {
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx !in 0 until m || ny !in 0 until n || copyMap[ny][nx] != 0) {
                continue
            }

            copyMap[ny][nx] = 2
            virus += 1

            if (virus >= minVirus) {
                return
            }

            dfs(nx, ny)
        }
    }

    // 완전 탐색
    for (i in 0 until candiList.size - 2) {
        for (j in i + 1 until candiList.size - 1) {
            for (k in j + 1 until candiList.size) {
                copy()
                virus = 0

                copyMap[candiList[i].y][candiList[i].x] = 1
                copyMap[candiList[j].y][candiList[j].x] = 1
                copyMap[candiList[k].y][candiList[k].x] = 1

                for (v in initVirus) {
                    dfs(v.x, v.y)

                    if (virus > minVirus) {
                        break
                    }
                }
                if (virus > minVirus) {
                    continue
                }

                minVirus = virus.coerceAtMost(minVirus)
            }
        }
    }

    println(candiList.size - 3 - minVirus)
}
