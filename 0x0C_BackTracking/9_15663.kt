package `kotlin-algorithm`.`0x0C_BackTracking`

import java.util.*

private val sb = StringBuilder()
private lateinit var arr: List<String>
private lateinit var res: Array<String>
private lateinit var visited: BooleanArray

fun main() = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    arr = readLine().split(' ').sortedBy { it.toInt() }
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

    var before = ""  // 이전 수열에서 같은 위치 같은 것 선택하는지 검사용
    for (i in arr.indices) {
        if (!visited[i] && before != arr[i]) {
            visited[i] = true
            before = arr[i]
            res[idx] = arr[i]
            dfs(idx + 1)
            visited[i] = false
        }
    }
}