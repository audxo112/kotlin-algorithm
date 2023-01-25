// https://www.acmicpc.net/problem/11559
package solution11559__code2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

private class Node(val x: Int, val y: Int)

private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)

// 링크드 리스트를 이용한 풀이
// bfs 에서 인덱스 접근으로 인해 시간이 많이 걸린듯함

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val H = 12
    val W = 6

    val map = Array(W) { LinkedList<Char>() }
    val visited = Array(W) { IntArray(H) }

    for (y in 0 until H) {
        val line = br.readLine()
        for (x in 0 until W) {
            if (line[x] == '.') {
                continue
            }
            map[x].addFirst(line[x])
        }
    }

    var combo = 0

    fun mark(x: Int, y: Int): Boolean {
        visited[x][y] = combo
        val shape = map[x][y]

        val markList = ArrayList<Node>()
        markList.add(Node(x, y))
        var index = 0

        while (index < markList.size) {
            val cur = markList[index]
            for (i in 0 until 4) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]

                if (nx !in 0 until W || ny !in 0 until map[nx].size || visited[nx][ny] == combo || map[nx][ny] != shape) {
                    continue
                }

                visited[nx][ny] = combo
                markList.add(Node(nx, ny))
            }
            index += 1
        }

        if (markList.size >= 4) {
            for (node in markList.sortedBy { -it.y }) {
                map[node.x][node.y] = '*'
            }
            return true
        }

        return false
    }

    fun sweep() {
        for (x in 0 until W) {
            for(y in map[x].indices.reversed()){
                when(map[x][y]){
                    '*' -> map[x].removeAt(y)
                }
            }
        }
    }

    var play = true
    while (play) {
        play = false
        combo += 1
        for (x in 0 until W) {
            for (y in map[x].indices) {
                if (map[x][y] > 'A' && visited[x][y] < combo && mark(x, y)) {
                    play = true
                }
            }
        }
        if (play) {
            sweep()
        }
    }

    println(combo - 1)
}