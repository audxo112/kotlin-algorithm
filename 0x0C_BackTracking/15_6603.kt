package `kotlin-algorithm`.`0x0C_BackTracking`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private val sb = StringBuilder()
private val res = Array<String>(6) { "" }

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    while (true) {
        val st = StringTokenizer(br.readLine())
        val k = st.nextToken().toInt()
        if (k == 0) break

        val arr = Array<String>(k) { st.nextToken() }

        dfs(0, 0, arr)
        sb.append('\n')
    }

    println(sb.toString())
}

private fun dfs(idx: Int, start: Int, arr: Array<String>) {
    if (idx == res.size) {
        res.forEach {
            sb.append("$it ")
        }
        sb.append('\n')
        return
    }

    for (i in start until arr.size) {
        res[idx] = arr[i]
        dfs(idx + 1, i + 1, arr)
    }
}
