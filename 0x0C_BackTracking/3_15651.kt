package `kotlin-algorithm`.`0x0C_BackTracking`

/*
    중복 순열 문제 : 순서O, 중복X
 */

private val sb =  StringBuilder()
private lateinit var arr: IntArray

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = IntArray(m)

    dfs(n, 0)

    println(sb.toString())
}

private tailrec fun dfs(n: Int, idx: Int) {
    if (idx == arr.size) {
        arr.forEach {
            sb.append("$it ")
        }
        sb.append('\n')
        return
    }

    for (i in 0 until n) {
        arr[idx] = i + 1
        dfs(n, idx + 1)
    }
}