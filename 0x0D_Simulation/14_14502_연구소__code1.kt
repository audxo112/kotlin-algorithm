// https://www.acmicpc.net/problem/14502
package solution14502__code1

import java.io.StreamTokenizer
import java.util.*
import kotlin.collections.ArrayList

private class Node(val x: Int, val y: Int)

private val dx = listOf(1, -1, 0, 0, -1, -1, 1, 1)
private val dy = listOf(0, 0, 1, -1, -1, 1, 1, -1)

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {

    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    val map = Array(n) { IntArray(m) { input() } }
    val candi = Array(n) { BooleanArray(m) }
    val initCandiList = ArrayList<Node>(n * m)
    val initVirus = ArrayList<Node>(n * m)
    var initZero = 0
    val initVisited = Array(n) { BooleanArray(m) }

    fun existWall(x: Int, y: Int): Boolean {
        if(x == 0 || x == m - 1 || y == 0 || y == n - 1){
            return true
        }
        for(i in 0 until 8){
            val nx = x + dx[i]
            val ny = y + dy[i]
            if(map[ny][nx] == 1){
                return true
            }
        }
        return false
    }

    for (y in 0 until n) {
        for (x in 0 until m) {
            when (map[y][x]) {
                2 -> {
                    initVisited[y][x] = true
                    initVirus.add(Node(x, y))
                }

                0 -> {
                    // 근처에 연결해야하기 때문에 근처에 벽이 있는 장소를 후보에 둔다
                    if (existWall(x, y) && !candi[y][x] && map[y][x] == 0) {
                        candi[y][x] = true
                        initCandiList.add(Node(x, y))
                    }

                    initZero += 1
                }
            }
        }
    }

    fun Array<BooleanArray>.copy() = Array(this.size) { y ->
        BooleanArray(this[0].size) { x ->
            this[y][x]
        }
    }

    var maxZero = 0

    fun bfs(): Int {
        val queue: Queue<Node> = LinkedList()
        queue.addAll(initVirus)

        val visited = initVisited.copy()
        var zero = initZero - 3

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            for (i in 0 until 4) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]

                if (nx !in 0 until m || ny !in 0 until n || visited[ny][nx] || map[ny][nx] != 0) {
                    continue
                }

                visited[ny][nx] = true
                zero -= 1
                queue.add(Node(nx, ny))
                if (zero < maxZero) {
                    return maxZero
                }
            }
        }
        return zero
    }

    // 새로운 점에서 새로운 후보를 찾은다
    fun findCandi(x: Int, y: Int): List<Node> {
        val list = ArrayList<Node>(8)
        for (i in 0 until 8) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx !in 0 until m || ny !in 0 until n || map[ny][nx] != 0 || candi[ny][nx]) {
                continue
            }
            candi[ny][nx] = true
            list.add(Node(nx, ny))
        }
        return list
    }


    fun backTracking(N: Int, M: Int, candiList: List<Node>) {
        if (N == 3) {
            maxZero = bfs().coerceAtLeast(maxZero)
            return
        }

        for (i in M until candiList.size) {
            val cur = candiList[i]
            val newList = findCandi(cur.x, cur.y)
            map[cur.y][cur.x] = 1
            backTracking(N + 1, i + 1, candiList + newList)
            // 이번에 생긴 후보 데이터 삭제
            for (item in newList) {
                candi[item.y][item.x] = false
            }
            map[cur.y][cur.x] = 0
        }
    }

    backTracking(0, 0, initCandiList)
    println(maxZero)
}
