package solve_2667

import java.util.LinkedList
import java.util.Queue

private val dx = listOf(1, -1, 0, 0)
private val dy = listOf(0, 0, 1, -1)
private lateinit var map: List<CharArray>

private data class Node(val x: Int, val y: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    map = List(n) {
        readLine().toCharArray()
    }

    val answerArr = arrayListOf<Int>()
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (map[j][i] != '0') {
                answerArr.add(bfs(i, j))
            }
        }
    }

    println(answerArr.size)
    answerArr.sorted().forEach {
        println(it)
    }
}

private fun bfs(x: Int, y: Int): Int {
    val queue: Queue<Node> = LinkedList()
    queue.add(Node(x, y))
    map[y][x] = '0'
    var size = 1

    while (queue.isNotEmpty()) {
        val nowNode = queue.poll()

        for (i in dx.indices) {
            val nx = nowNode.x + dx[i]
            val ny = nowNode.y + dy[i]

            if (nx < 0 || nx > map.lastIndex) continue
            if (ny < 0 || ny > map.lastIndex) continue

            if (map[ny][nx] != '0') {
                map[ny][nx] = '0'
                size += 1
                queue.add(Node(nx, ny))
            }
        }
    }

    return size
}