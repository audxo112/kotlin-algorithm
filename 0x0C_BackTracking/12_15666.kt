package `kotlin-algorithm`.`0x0C_BackTracking`

import java.util.*

private val sb = StringBuilder()
private lateinit var arr: List<String>
private lateinit var res: Array<String>

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(' ').map { it.toInt() }
//    arr = readLine().split(' ').sortedBy { it.toInt() }
    res = Array(m) { "" }

    val st = StringTokenizer(readLine(), " ")
    val set = mutableSetOf<String>()
    repeat(n) {
        set.add(st.nextToken())
    }
    arr = set.sortedBy {
        it.toInt()
    }

    dfs(0, 0)

    println(sb.toString())
}

private fun dfs(idx: Int, start: Int) {
    if (idx == res.size) {
        res.forEach {
            sb.append("$it ")
        }
        sb.append('\n')
        return
    }

//    var before = ""
//    for (i in start until arr.size) {
//        if (before != arr[i]) {
//            before = arr[i]
//            res[idx] = arr[i]
//            dfs(idx + 1, i)
//        }
//    }
    for (i in start until arr.size) {
        res[idx] = arr[i]
        dfs(idx + 1, i)
    }
}