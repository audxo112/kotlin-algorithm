package `kotlin-algorithm`.`0x0C_BackTracking`

private val sb = StringBuilder()
private lateinit var arr: List<Int>
private lateinit var res: IntArray
private val set = HashSet<String>()

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(' ').map { it.toInt() }
    arr = readLine().split(' ').map { it.toInt() }.sorted()
    res = IntArray(m)

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
        res[idx] = arr[i]
        dfs(idx + 1)
    }
}