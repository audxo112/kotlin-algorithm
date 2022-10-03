package `kotlin-algorithm`.`0x0C_BackTracking`

/*
    순열 문제 : 순서O, 중복X
 */

private val sb = StringBuilder()
private lateinit var arr: IntArray
private lateinit var visited: BooleanArray

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = IntArray(m)
    visited = BooleanArray(n)

    dfs(n, m, 0)

    println(sb.toString())
}

private fun dfs(n: Int, m: Int, idx: Int) {
    if(idx == m) {
        arr.forEach {
            sb.append("$it ")
        }
        sb.append('\n')
        return
    }

    for (i in 0 until n) {
        if (!visited[i]) {
            visited[i] = true
            arr[idx] = i + 1
            dfs(n, m, idx + 1)
            visited[i] = false
        }
    }
}