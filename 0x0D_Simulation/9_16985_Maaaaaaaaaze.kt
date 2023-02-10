package solve_16985

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.Queue

const val SIZE = 5

private val numbers = arrayOf(0, 1, 2, 3, 4)
private val dx = arrayOf(0, 0, 0, 0, 1, -1)
private val dy = arrayOf(0, 0, 1, -1, 0, 0)
private val dz = arrayOf(1, -1, 0, 0, 0, 0)

private var answer = Int.MAX_VALUE
private lateinit var maze: Array<Array<IntArray>>

private data class Node(val x: Int, val y: Int, val z: Int)


fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    maze = Array(SIZE) {   //z
        Array(SIZE) {          //y
            IntArray(SIZE) {   //x
                val num = input()
                if (num == 0) {
                    -1
                } else {
                    0
                }
            }
        }
    }

    rotate(0)

    if(answer == Int.MAX_VALUE) {
        println(-1)
    } else {
        println(answer)
    }
}

private fun rotate(depth: Int) {
    if (depth == SIZE) {
        stackMaze(0, BooleanArray(SIZE), arrayListOf())
        return
    }
    for (i in 0 until 4) { //회전 횟수
        val tempPlane = maze[depth].copy()

        for (j in 0 until SIZE) {
            for (k in 0 until SIZE) {
                maze[depth][k][SIZE - 1 - j] = tempPlane[j][k]
            }
        }
        rotate(depth + 1) //다음층 회전
    }
}

private fun Array<IntArray>.copy() =
    Array(SIZE) { i ->
        IntArray(SIZE) { j ->
            this[i][j]
        }
    }


private fun stackMaze(depth: Int, visited: BooleanArray, order: ArrayList<Int>) {
    if (depth == SIZE) {
        val newMaze = Array(SIZE) { i ->
            maze[order[i]].copy()
        }
        if (newMaze[0][0][0] == 0 && newMaze[SIZE - 1][SIZE - 1][SIZE - 1] == 0) {
            bfs(newMaze, Node(0, 0, 0), Node(SIZE - 1, SIZE - 1, SIZE - 1))
        }

        return
    }
    for (i in 0 until SIZE) {
        if (visited[i]) continue
        order.add(numbers[i])
        visited[i] = true
        stackMaze(depth + 1, visited, order)
        order.removeLast()
        visited[i] = false
    }
}


private fun bfs(maze: Array<Array<IntArray>>, start: Node, end: Node) {
    val queue: Queue<Node> = LinkedList()
    queue.add(start)
    maze[start.z][start.y][start.x] = 1
    while (queue.isNotEmpty()) {
        val (x, y, z) = queue.poll()

        for(i in dx.indices) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            val nz = z + dz[i]

            if(nx == end.x && ny == end.y && nz == end.z ) {
                answer = minOf(maze[z][y][x], answer)
                return
            }

            if(nx < 0 || nx > maze.lastIndex) continue
            if(ny < 0 || ny > maze.lastIndex) continue
            if(nz < 0 || nz > maze.lastIndex) continue

            if(maze[nz][ny][nx] == 0) {
                queue.add(Node(nx,ny,nz))
                maze[nz][ny][nx] = maze[z][y][x] + 1
            }
        }

    }
}
