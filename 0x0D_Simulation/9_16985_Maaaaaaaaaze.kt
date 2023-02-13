// https://www.acmicpc.net/problem/16985
package solution16985

import java.io.StreamTokenizer
import java.util.Queue
import java.util.LinkedList

private class Node(val x: Int, val y: Int, val z: Int, val count: Int)

private val dx = intArrayOf(1, -1, 0, 0, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1, 0, 0)
private val dz = intArrayOf(0, 0, 0, 0, 1, -1)

// 시계 방향으로 회전하기 위한 함수
private fun Array<IntArray>.rotate(): Array<IntArray> {
    val arr = Array(this[0].size) {
        IntArray(this.size)
    }
    for (y in this.indices) {
        for (x in this[y].indices) {
            arr[x][this.size - y - 1] = this[y][x]
        }
    }
    return arr
}

// 회전시 다른 배열과 같은지 확인
private fun Array<IntArray>.isDuplication(arr: Array<IntArray>): Boolean {
    for (y in 0 until 5) {
        for (x in 0 until 5) {
            if (this[y][x] != arr[y][x]) {
                return false
            }
        }
    }
    return true
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val maze = Array(5) {
        Array(4) {
            Array(5) {
                IntArray(5)
            }
        }
    }
    // 사용하지 않을 배열을 미리 체크
    val disable = Array(5) {
        BooleanArray(4)
    }

    repeat(5) { z ->
        for (y in 0 until 5) {
            for (x in 0 until 5) {
                maze[z][0][y][x] = input()
            }
        }

        for (r in 1 until 4) {
            maze[z][r] = maze[z][r - 1].rotate()
        }
    }

    repeat(5) { z ->
        for (i in 1 until 4) {
            for (j in 0 until i) {
                if (disable[z][j]) {
                    continue
                }
                if (maze[z][i].isDuplication(maze[z][j])) {
                    disable[z][i] = true
                    break
                }
            }
        }
    }

    val zOrder = IntArray(5)

    var count = 125
    val rotation = IntArray(5)
    fun bfs(): Int {
        val visited = Array(5) {
            Array(5) {
                BooleanArray(5)
            }
        }

        val queue: Queue<Node> = LinkedList()
        queue.add(Node(0, 0, 0, 0))
        visited[0][0][0] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            for (i in 0 until 6) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                val nz = cur.z + dz[i]

                if (nx !in 0 until 5 || ny !in 0 until 5 || nz !in 0 until 5 ||
                        maze[zOrder[nz]][rotation[zOrder[nz]]][ny][nx] == 0 || visited[nz][ny][nx]) {
                    continue
                }

                if (nx == 4 && ny == 4 && nz == 4) {
                    return cur.count + 1
                }

                visited[nz][ny][nx] = true
                queue.add(Node(nx, ny, nz, cur.count + 1))
            }
        }

        return 125
    }

    val orderVisited = BooleanArray(5)

    // 순서를 정하는 백트래킹
    fun orderBackTracking(n: Int) {
        if (count == 12) {
            return
        }
        if (n == 5) {
            // 순서를 정했을때 도착 지점에 갈 수 있는지 확인
            if (maze[zOrder[4]][rotation[zOrder[4]]][4][4] == 0) {
                return
            }
            count = bfs().coerceAtMost(count)
            return
        }
        // zOrder, rotation 을 이용하여 변경하기
        if (n == 1 && maze[zOrder[0]][rotation[zOrder[0]]][0][0] == 0) {
            // 첫번째 판을 골랐을때 시작이 가능한지 확인
            return
        }
        for (i in 0 until 5) {
            if (orderVisited[i]) {
                continue
            }
            orderVisited[i] = true
            zOrder[n] = i
            orderBackTracking(n + 1)
            orderVisited[i] = false
        }
    }

    // 회전을 정하는 백트래킹
    fun backTracking(n: Int) {
        if (count == 12) {
            return
        }
        if (n == 5) {
            // 회전을 정했으면 배열의 순서를 정하기
            orderBackTracking(0)
            return
        }
        for (r in 0 until 4) {
            // 위에서 같은 배열로 판별된건 확인할 필요 없음
            if (disable[n][r]) {
                continue
            }
            rotation[n] = r
            backTracking(n + 1)
        }
    }

    backTracking(0)

    if (count != 125) {
        println(count)
    } else {
        println(-1)
    }
}