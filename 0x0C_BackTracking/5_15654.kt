package `kotlin-algorithm`.`0x0C_BackTracking`

private val sb = StringBuilder()
private lateinit var res: Array<String>
private lateinit var arr: List<String>
private lateinit var visited: BooleanArray

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = readLine().split(" ").sortedBy { it.toInt() }
    res = Array(m) { "" }
    visited = BooleanArray(n)

    dfs(0)

    println(sb.toString())
}

private fun dfs(idx: Int) {
    if (idx == res.size) {
        res.forEach {
            sb.append("$it ")
        }
        sb.append('\n')
        return
    }

    for (i in arr.indices) {
        if (!visited[i]) {
            visited[i] = true
            res[idx] = arr[i]
            dfs(idx + 1)
            visited[i] = false
        }
    }
}