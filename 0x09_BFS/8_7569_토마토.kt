package solve_7569

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.Queue

private val dx = listOf(-1, 1, 0, 0, 0, 0)
private val dy = listOf(0, 0, -1, 1, 0, 0)
private val dz = listOf(0, 0, 0, 0, -1, 1)
private lateinit var tomatoBox: Array<Array<IntArray>>

private data class Node(val x: Int, val y: Int, val z: Int)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val m = input()
    val n = input()
    val h = input()
    val tomatoQueue: Queue<Node> = LinkedList()
    var tomatoCnt = 0

    tomatoBox = Array(h) { z ->
        Array(n) { y ->
            IntArray(m) { x ->
                val tomato = input()
                if (tomato == 1) {
                    tomatoQueue.add(Node(x, y, z))
                }
                if (tomato == 0) {
                    tomatoCnt++
                }
                tomato
            }
        }
    }
    bfs(tomatoQueue, tomatoCnt)
}

private fun bfs(queue: Queue<Node> = LinkedList(), tomatoCnt: Int){
    var date = -1
    var cnt = tomatoCnt
    while (queue.isNotEmpty()) {
        date++
        repeat(queue.size) {
            val nowNode = queue.poll()
            for (i in dx.indices) {
                val nx = nowNode.x + dx[i]
                val ny = nowNode.y + dy[i]
                val nz = nowNode.z + dz[i]

                if (nx < 0 || nx > tomatoBox[0][0].lastIndex) continue
                if (ny < 0 || ny > tomatoBox[0].lastIndex) continue
                if (nz < 0 || nz > tomatoBox.lastIndex) continue

                if (tomatoBox[nz][ny][nx] == 0) {
                    cnt--
                    tomatoBox[nz][ny][nx] = 1
                    queue.add(Node(nx, ny, nz))
                }
            }
        }
    }
    println(if (cnt == 0) date else -1)
}