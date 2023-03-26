package solve_14500

import java.io.*

private val dx = arrayOf(-1, 0, 1, 0)
private val dy = arrayOf(0, 1, 0, -1)
private lateinit var map: Array<IntArray>
private lateinit var visited: Array<IntArray>
private var answer = 0
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    map = Array(n) {
        IntArray(m) {
            input()
        }
    }
    visited = Array(n) {
        IntArray(m)
    }

    for (i in 0 until n) {
        for (j in 0 until m) {
            visited[i][j] = 1
            dfs(j, i, 0)
            visited[i][j] = 0
            answer = maxOf(answer, getTmino(j, i))
        }
    }

    println(answer)
}

fun dfs(x: Int, y: Int, sum: Int) {
    val nowSum = sum + map[y][x]
    if (visited[y][x] == 4) {
        answer = maxOf(answer, nowSum)
        return
    }

    for (i in dx.indices) {
        val nx = x + dx[i]
        val ny = y + dy[i]
        if (nx < 0 || nx > map[0].lastIndex) continue
        if (ny < 0 || ny > map.lastIndex) continue

        if (visited[ny][nx] == 0) {
            visited[ny][nx] = visited[y][x] + 1
            dfs(nx, ny, nowSum)
            visited[ny][nx] = 0
        }
    }
}

fun getTmino(x: Int, y: Int): Int {
    var t1 = 0
    var t2 = 0
    var t3 = 0
    var t4 = 0
    if (x - 1 > 0 && x + 1 < map[0].lastIndex && y - 1 > 0) {
        t1 = map[y][x - 1] + map[y][x] + map[y][x + 1] + map[y - 1][x]// ㅗ
    }
    if (x - 1 > 0 && x + 1 < map[0].lastIndex && y + 1 < map.lastIndex) {
        t2 = map[y][x - 1] + map[y][x] + map[y][x + 1] + map[y + 1][x] // ㅜ
    }
    if (x - 1 > 0 && y + 1 < map.lastIndex && y - 1 > 0) {
        t3 = map[y + 1][x] + map[y][x - 1] + map[y][x] + map[y - 1][x] //ㅓ
    }
    if (x + 1 > map[0].lastIndex && y + 1 < map.lastIndex && y - 1 > 0) {
        t4 = map[y + 1][x] + map[y][x - 1] + map[y][x] + map[y - 1][x] //ㅏ
    }
    return maxOf(t1, t2, t3, t4)
}