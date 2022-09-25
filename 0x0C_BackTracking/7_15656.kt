package `kotlin-algorithm`.`0x0C_BackTracking`

private val sb = StringBuilder()
private lateinit var arr: List<Int>
private lateinit var res: IntArray

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(' ').map { it.toInt() }
    arr = readLine().split(' ').map { it.toInt() }.sorted()
    res = IntArray(m)

    dfs(0)

    println(sb.toString())
}

private tailrec fun dfs(idx: Int) {
    if (idx == res.size) {
        res.forEach {
            sb.append("$it ")
        }
        sb.append('\n')
        return
    }

    for (i in 0 until arr.size) {
        res[idx] = arr[i]
        dfs(idx + 1)
    }
}