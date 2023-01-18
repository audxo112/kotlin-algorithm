package solve_156836

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.Queue

private data class Node(val x: Int, val y: Int)

private val dx = arrayOf(1, -1, 0, 0)
private val dy = arrayOf(0, 0, -1, 1)
private lateinit var map: Array<IntArray>

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()


    val houseArr = arrayListOf<Node>()
    val chickenArr = arrayListOf<Node>()

    map = Array(n) { x ->
        IntArray(n) { y ->
            val o = input()
            if (o == 1) {
                houseArr.add(Node(x, y))
            }
            if (o == 2) {
                chickenArr.add(Node(x, y))
            }
            o
        }
    }

    val chickenCombinations = arrayListOf<ArrayList<Node>>()
    getCombination(chickenArr,m,0, arrayListOf(),chickenCombinations)

    var answer = Int.MAX_VALUE
    for(chickenComb in chickenCombinations) {
        var totalDist = 0
        for(house in houseArr) {
            totalDist += bfs(house, chickenComb)
        }

        if(answer > totalDist) answer = totalDist
    }
    println(answer)
}

private fun getCombination(
    list: ArrayList<Node>,
    size: Int,
    start: Int,
    current: ArrayList<Node>,
    result: ArrayList<ArrayList<Node>>,
) {
    if (current.size == size) {
        result.add(ArrayList(current))
        return
    }
    for (i in start until list.size) {
        current.add(list[i])
        getCombination(list, size, i + 1, current, result)
        current.removeAt(current.size - 1)
    }
}

private fun bfs(start: Node, chickenComb: ArrayList<Node>) : Int{

    val dist = Array(map.size) {
        IntArray(map.size)
    }

    val queue: Queue<Node> = LinkedList()
    queue.add(start)

    while (queue.isNotEmpty()) {
        val nowNode = queue.poll()
        val x = nowNode.x
        val y = nowNode.y

        for (i in dx.indices) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || nx > map.lastIndex) continue
            if (ny < 0 || ny > map[0].lastIndex) continue

            if (dist[nx][ny] == 0) {
                dist[nx][ny] = dist[x][y] + 1
                if (map[nx][ny] == 2 && chickenComb.contains(Node(nx,ny))) {
                    return dist[nx][ny]
                }
                queue.add(Node(nx, ny))
            }
        }
    }
    return 0
}
