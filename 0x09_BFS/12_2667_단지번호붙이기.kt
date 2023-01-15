package `kotlin-algorithm`.`0x09_BFS`

import java.io.BufferedReader
import java.io.InputStreamReader

private val dx = arrayOf(-1, 1, 0, 0)
private val dy = arrayOf(0, 0, -1, 1)
private lateinit var arr: Array<CharArray>
private val ONE = '1'
private val ZERO = '0'
private var count = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    arr = Array(n) {
        br.readLine().toCharArray()
    }

    var group = 0
    val res = mutableListOf<Int>()
    for (i in arr.indices) {
        for (j in arr[0].indices) {
            if (arr[i][j] == ONE) {
                dfs(i, j)
                res.add(count)
                count = 0
                group++
            }
        }
    }

    val sb = StringBuilder()
    sb.appendLine(group)
    res.sort()
    res.forEach {
        sb.appendLine(it)
    }
    println(sb.toString())
}

private fun dfs(x: Int, y: Int) {
    if (arr[x][y] == ZERO) return

    arr[x][y] = ZERO
    count++

    for (i in dx.indices) {
        val xx = x + dx[i]
        val yy = y + dy[i]

        if (xx !in arr.indices || yy !in arr.indices || arr[xx][yy] != ONE) continue

        dfs(xx, yy)
    }
}