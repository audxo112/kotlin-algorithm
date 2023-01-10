//https://www.acmicpc.net/problem/2667
package solution2667__code2

import java.util.PriorityQueue

private fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val map = Array(n) {
        val line = readLine()
        BooleanArray(n) { i ->
            line[i] == '1'
        }
    }

    fun dfs(sx: Int, sy: Int): Int {
        map[sy][sx] = false
        var count = 1
        if(sx != n - 1 && map[sy][sx + 1]){
            count += dfs(sx + 1, sy)
        }
        if(sx != 0 && map[sy][sx - 1]){
            count += dfs(sx - 1, sy)
        }
        if(sy != n - 1 && map[sy + 1][sx]){
            count += dfs(sx, sy + 1)
        }
        if(sy != 0 && map[sy - 1][sx]){
            count += dfs(sx, sy - 1)
        }
        return count
    }

    var count = 0
    val house = PriorityQueue<Int>()
    for (y in 0 until n) for (x in 0 until n) {
        if (map[y][x]) {
            count += 1
            house.add(dfs(x, y))
        }
    }

    val sb = StringBuilder()
    while (house.isNotEmpty()) {
        sb.appendLine(house.poll())
    }
    println(count)
    println(sb.toString())
}
