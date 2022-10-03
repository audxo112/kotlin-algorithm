package `kotlin-algorithm`.`0x0C_BackTracking`

import java.util.*
import kotlin.collections.HashSet

/*
    중복 순열, 원소가 중복됨
    1. before : 이전 수열에서 같은 위치의 값이 같은지 검사
    2. set : 중복 제거 후 dfs
 */

private val sb = StringBuilder()
private lateinit var arr: List<String>
private lateinit var res: Array<String>
private val set = HashSet<String>()

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(' ').map { it.toInt() }
    arr = readLine().split(' ').sortedBy { it.toInt() }
    res = Array(m) { "" }

//    val st = StringTokenizer(readLine(), " ")
//    val set = mutableSetOf<String>()
//    repeat(n) {
//        set.add(st.nextToken())
//    }
//    arr = set.sortedBy {
//        it.toInt()
//    }

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

    // 1. before 사용
    var before = ""
    for (i in arr.indices) {
        if (before != arr[i]) {
            before = arr[i]
            res[idx] = arr[i]
            dfs(idx + 1)
        }
    }

    // 2. 이미 중복 제거 후
//    for (i in arr.indices) {
//        res[idx] = arr[i]
//        dfs(idx + 1)
//    }
}