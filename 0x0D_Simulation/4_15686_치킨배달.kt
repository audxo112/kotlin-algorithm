// https://www.acmicpc.net/problem/15686

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.abs

private val home = mutableListOf<Node4>()
private val chicken = mutableListOf<Node4>()
private var total = Int.MAX_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    // for 보다 빨랐다.
    repeat(n) { i ->
        st = StringTokenizer(br.readLine())
        repeat(n) { j ->
            val num = st.nextToken().toInt()
            if (num == 1) {
                home.add(Node4(i, j))
            } else if (num == 2) {
                chicken.add(Node4(i, j))
            }
        }
    }

    if (chicken.size == m) {
        println(getCityChickenDistance(chicken.toTypedArray()))
    } else {
        dfs(Array(m) { Node4() }, 0, 0)
        println(total)
    }
}

private fun getCityChickenDistance(chicken: Array<Node4>): Int {
    var tmp = 0
    home.forEach { h ->
        var min = Int.MAX_VALUE
        chicken.forEach { c ->
            val distance = abs(c.x - h.x) + abs(c.y - h.y)
            if (distance < min) {
                min = distance
            }
        }
        tmp += min
    }
    return tmp
}

private fun dfs(visited: Array<Node4>, depth: Int, start: Int) {
    if (depth == visited.size) {
        total = total.coerceAtMost(getCityChickenDistance(visited))
        return
    }

    for (i in start until chicken.size) {
        visited[depth] = chicken[i]
        dfs(visited, depth + 1, i + 1)
    }
}

data class Node4(val x: Int = 0, val y: Int = 0)