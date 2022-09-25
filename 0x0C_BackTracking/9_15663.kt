package `kotlin-algorithm`.`0x0C_BackTracking`

import java.util.StringTokenizer

private val sb = StringBuilder()
private lateinit var arr: List<Int>
private lateinit var res: IntArray
private lateinit var visited: BooleanArray
private val set = HashSet<String>()

fun main() = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    arr = readLine().split(' ').map { it.toInt() }.sorted()
    res = IntArray(m)
    visited = BooleanArray(n)

    dfs(0)

    println(sb.toString())
}

private fun dfs(idx: Int) {
    if (idx == res.size) {
        val tmp = StringBuilder()
        res.forEach {
            tmp.append("$it ")
        }
        tmp.toString().let {
            if (!set.contains(it)) {
                set.add(it)
                sb.append(it).append('\n')
            }
        }
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