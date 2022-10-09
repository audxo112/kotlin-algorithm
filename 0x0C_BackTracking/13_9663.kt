package `kotlin-algorithm`.`0x0C_BackTracking`

import kotlin.math.abs

private lateinit var visited: Array<BooleanArray>
private lateinit var array: IntArray
private var count = 0

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    visited = Array(n) { BooleanArray(n) }
    array = IntArray(n)

    dfs(0, n)

    println(count)
}

private fun dfs(idx: Int, n: Int) {
    if (idx == n) {
        count++
        return
    }

    // idx는 행, 반복문으로 열 탐색
    for (i in 0 until n) {
        if (isPossibleColumn(idx, i)) {
            // idx 행에서 i 열 선택
            array[idx] = i
            dfs(idx + 1, n)
        }
    }
}

private fun isPossibleColumn(row: Int, column: Int): Boolean {
    for (i in 0 until row) {
        if (array[i] == column) {
            return false
        } else if (abs(array[i] - column) == row - i) {
            return false
        }
    }

    return true
}
