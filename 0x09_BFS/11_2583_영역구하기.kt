package solve_2583

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.Queue

private val dx = listOf(1, -1, 0, 0)
private val dy = listOf(0, 0, 1, -1)
private lateinit var visited: List<BooleanArray>

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val m = input()
    val n = input()
    val k = input()

    visited = List(m) {
        BooleanArray(n)
    }

    repeat(k) {
        val leftBottom = Node(input(), input())
        val rightTop = Node(input(), input())

        for (i in leftBottom.x until rightTop.x) {
            for (j in leftBottom.y until rightTop.y) {
                visited[j][i] = true
            }
        }
    }

    val answerArr = arrayListOf<Int>()

    for (i in 0 until n) {
        for (j in 0 until m) {
            if (visited[j][i].not()) {
                answerArr.add(bfs(i,j))
            }
        }
    }
    println(answerArr.size)
    answerArr.sorted().forEach {
        print("$it ")
    }

}

private fun bfs(x: Int, y: Int): Int {
    val queue: Queue<Node> = LinkedList()

    queue.add(Node(x, y))

    visited[y][x] = true
    var size = 1

    while (queue.isNotEmpty()) {
        val nowNode = queue.poll()

        for (i in dx.indices) {
            val nx = nowNode.x + dx[i]
            val ny = nowNode.y + dy[i]

            if (nx < 0 || nx > visited[0].lastIndex) continue
            if (ny < 0 || ny > visited.lastIndex) continue

            if(x==3 && y == 0) {
                println()
            }
            if (visited[ny][nx].not()) {
                size += 1
                visited[ny][nx] = true
                queue.add(Node(nx, ny))
            }
        }
    }
    return size
}

data class Node(val x: Int, val y: Int)