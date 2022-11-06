package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

// 5 2 4 1 3
// 1 1 1 1 2
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val dp = IntArray(n + 1)

    var max = Int.MIN_VALUE
    repeat(n) {
        val num = st.nextToken().toInt()
        dp[num] = dp[num-1] + 1
        max = max.coerceAtLeast(dp[num])
    }

    println(n - max)
}