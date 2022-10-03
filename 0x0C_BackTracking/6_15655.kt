package `kotlin-algorithm`.`0x0C_BackTracking`

private val sb = StringBuilder()
private lateinit var arr: List<String>
private lateinit var res: Array<String>

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(' ').map { it.toInt() }
    arr = readLine().split(' ').sortedBy { it.toInt() }
    res = Array(m) { "" }

    dfs(0, 0)

    println(sb.toString())
}

private tailrec fun dfs(idx: Int, start: Int) {
    if (idx == res.size) {
        res.forEach {
            sb.append("$it ")
        }
        sb.append('\n')
        return
    }

    for (i in start until arr.size) {
        res[idx] = arr[i]
        dfs(idx + 1, i + 1)
    }
}