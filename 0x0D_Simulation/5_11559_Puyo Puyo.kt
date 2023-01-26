package solve_11559

import java.util.LinkedList
import java.util.Queue

private val dx = arrayOf(1, -1, 0, 0)
private val dy = arrayOf(0, 0, 1, -1)

private data class Node(val x: Int, val y: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val h = 12
    val w = 6
    var answer = 0

    val map = Array(h) {
        readLine().toCharArray()
    }

    val defaultVisited = Array(h) { y ->
        BooleanArray(w) { x ->
            map[y][x] == '.'
        }
    }

    fun dropDown(x: Int, y: Int) {
        if (y + 1 >= h || y < 0) return
        if (x == 6) {
            println()
        }
        if (map[y][x] != '.') {
            var ny = y + 1
            if (map[ny][x] == '.') {
                while (map[ny][x] == '.') {
                    ny++
                    if (ny >= h) break
                }
                ny--
                map[ny][x] = map[y][x]
                map[y][x] = '.'
                dropDown(x, y - 1)
            }
        }
    }

    fun bfs(start: Node, visited: Array<BooleanArray>, renPuyoList: ArrayList<Node>) {
        if (map[start.y][start.x] == '.') return

        val queue: Queue<Node> = LinkedList()
        queue.add(start)
        visited[start.y][start.x] = true

        val tempPuyoList = arrayListOf<Node>()

        tempPuyoList.add(start)
        while (queue.isNotEmpty()) {
            val (x, y) = queue.poll()
            val nowColor = map[y][x]
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx < 0 || nx > map[0].lastIndex) continue
                if (ny < 0 || ny > map.lastIndex) continue

                if (map[ny][nx] == nowColor && visited[ny][nx].not()) {
                    tempPuyoList.add(Node(nx, ny))
                    visited[ny][nx] = true
                    queue.add(Node(nx, ny))
                }
            }
        }
        if (tempPuyoList.size >= 4) {
            renPuyoList += tempPuyoList
        }
    }

    fun searchRen() {
        val visited = Array(h) {
            defaultVisited[it].clone()
        }
        val renPuyoList = arrayListOf<Node>()

        for (i in 0 until h) {
            for (j in 0 until w) {
                dropDown(j, i)
            }
        }

        for (i in 0 until h) {
            for (j in 0 until w) {
                if(visited[i][j].not()){
                    bfs(Node(j, i), visited, renPuyoList)
                }
            }
        }

        if (renPuyoList.isNotEmpty()) {
            renPuyoList.forEach {
                map[it.y][it.x] = '.'
            }
            answer += 1
            searchRen()
        }
    }

    searchRen()

    println(answer)

}


