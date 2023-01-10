//https://www.acmicpc.net/problem/2667
package solution2667__code1

import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

private class Node(val x: Int, val y: Int)

private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, -1, 0, 1)

private fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val map = Array(n) {
        val line = readLine()
        BooleanArray(n) { i ->
            line[i] == '1'
        }
    }

    fun bfs(sx: Int, sy: Int): Int {
        val queue: Queue<Node> = LinkedList()
        queue.add(Node(sx, sy))
        map[sy][sx] = false
        var count = 1
        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            for (i in 0 until 4) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]

                if (nx !in 0 until n || ny !in 0 until n || !map[ny][nx]) {
                    continue
                }

                count += 1
                map[ny][nx] = false
                queue.add(Node(nx, ny))
            }
        }
        return count
    }

    var count = 0
    val house = PriorityQueue<Int>()
    for (y in 0 until n) for (x in 0 until n) {
        if (map[y][x]) {
            count += 1
            house.add(bfs(x, y))
        }
    }

    val sb = StringBuilder()
    while (house.isNotEmpty()) {
        sb.appendLine(house.poll())
    }
    println(count)
    println(sb.toString())
}
