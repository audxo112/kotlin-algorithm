package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val stairs = IntArray(n) { br.readLine().toInt() }
    val dp = IntArray(n + 1)
    dp[1] = stairs[0]
    if (n > 1) {
        dp[2] = dp[1] + stairs[1]
    }

    for (i in 3..n) {
        dp[i] = stairs[i - 1] + minOf(dp[i - 2], dp[i - 3] + stairs[i - 2])
    }

    println(dp[n])
}