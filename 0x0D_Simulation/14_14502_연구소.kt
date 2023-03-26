package solve_14502

import java.io.*
import java.util.*

private data class Node(val x: Int, val y: Int)

private lateinit var map: Array<IntArray>
private val virusList = ArrayList<Node>()
private var zeroCnt = 0

private val dx = arrayOf(0, 0, -1, 1)
private val dy = arrayOf(1, -1, 0, 0)

private var answer = 0


fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    zeroCnt = n * m
    map = Array(n) { y ->
        IntArray(m) { x ->
            val num = input()
            if (num != 0) {
                zeroCnt -= 1
            }
            if (num == 2) {
                virusList.add(Node(x, y))
            }
            num
        }
    }

    zeroCnt -= 3

    backTrack(0, 0)

    println(answer)

}

private fun backTrack(depth: Int, iStart: Int) {
    if (depth == 3) {
        answer = maxOf(answer, bfs(map.copy()))
        return
    }
    for (i in iStart until map.size) {
        for (j in 0 until map[0].size) {
            if (map[i][j] == 0) {
                map[i][j] = 1
                backTrack(depth + 1, i)
                map[i][j] = 0
            }
        }
    }
}


private fun bfs(map: Array<IntArray>): Int {
    var nowZeroCnt = zeroCnt

    for (start in virusList) {
        val queue: Queue<Node> = LinkedList()
        queue.add(start)
        while (queue.isNotEmpty()) {
            val (x, y) = queue.poll()
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx < 0 || nx > map[0].lastIndex) continue
                if (ny < 0 || ny > map.lastIndex) continue

                if (map[ny][nx] == 0) {
                    nowZeroCnt -= 1
                    queue.add(Node(nx, ny))
                    map[ny][nx] = 1
                }
            }
        }
    }

    return nowZeroCnt

}

private fun Array<IntArray>.copy() =
    Array(this.size) { i ->
        this[i].clone()
    }