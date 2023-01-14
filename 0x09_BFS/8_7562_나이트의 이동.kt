
import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.Queue

private val dx = listOf(2, 2, -2, -2, 1, -1, 1, -1)
private val dy = listOf(1, -1, 1, -1, 2, 2, -2, -2)
private lateinit var board: List<IntArray>

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    repeat(input()) {
        val l = input()

        board = List(l) {
            IntArray(l)
        }

        val start = Node(input(),input())
        val goal = Node(input(),input())

        println(bfs(start, goal))
    }
}

private fun bfs(start: Node, goal: Node): Int{
    if(start == goal) return 0

    val queue: Queue<Node> = LinkedList()

    queue.add(Node(start.x,start.y))

    while (queue.isNotEmpty()) {
        val nowNode = queue.poll()

        for(i in dx.indices) {
            val nx = nowNode.x + dx[i]
            val ny = nowNode.y + dy[i]

            if(nx < 0 || nx > board.lastIndex) continue
            if(ny < 0 || ny > board.lastIndex) continue

            if(board[ny][nx] == 0) {
                board[ny][nx] = board[nowNode.y][nowNode.x] + 1
                queue.add(Node(nx,ny))
            }
            if(nx == goal.x && ny == goal.y) {
                return board[nowNode.y][nowNode.x] + 1
            }
        }
    }
    return 0
}

data class Node(val x: Int, val y: Int)