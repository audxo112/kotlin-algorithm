// https://www.acmicpc.net/problem/14502
package solution14502__code2

import java.io.StreamTokenizer
import java.util.*
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
        for(y in 0 until n){
            for(x in 0 until m){
                copyMap[y][x] = map[y][x]
            }
        }
    }

    var maxZero = 0

    fun bfs(): Int {
        val queue: Queue<Node> = LinkedList()
        queue.addAll(initVirus)

        var zero = candiList.size - 3

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            for (i in 0 until 4) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]

                if (nx !in 0 until m || ny !in 0 until n || copyMap[ny][nx] != 0) {
                    continue
                }

                copyMap[ny][nx] = 2
                zero -= 1
                queue.add(Node(nx, ny))
                // zero 개수가 max 보다 적으면 종료
                if (zero < maxZero) {
                    return maxZero
                }
            }
        }
        return zero
    }

    // 완전 탐색
    for(i in 0 until candiList.size - 2){
        for(j in i + 1 until candiList.size - 1){
            for(k in j + 1 until candiList.size){
                copy()

                copyMap[candiList[i].y][candiList[i].x] = 1
                copyMap[candiList[j].y][candiList[j].x] = 1
                copyMap[candiList[k].y][candiList[k].x] = 1

                maxZero = bfs().coerceAtLeast(maxZero)
            }
        }
    }

    println(maxZero)
}
