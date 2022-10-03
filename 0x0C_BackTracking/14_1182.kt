package `kotlin-algorithm`.`0x0C_BackTracking`

private lateinit var arr: List<Int>
private var count = 0

fun main() = with(System.`in`.bufferedReader()) {
    val (n, s) = readLine().split(" ").map { it.toInt() }
    arr = readLine().split(" ").map { it.toInt() }

    fun dfs(idx: Int, sum: Int) {
        if (idx == n) {
            if (sum == s) {
                count++
            }
            return
        }

        dfs(idx + 1, sum)
        dfs(idx + 1, sum + arr[idx])
    }

    // 아무것도 선택 안됐을 때 제외
    if (s == 0) {
        count--
    }

    dfs(0, 0)

    println(count)
}
