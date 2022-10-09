package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val n = BufferedReader(InputStreamReader(System.`in`)).readLine().toInt()
    val dp = IntArray(n + 1)
    dp[1] = 1
    if (n > 1) {
        dp[2] = 2
    }

    for (i in 3..n) {
        dp[i] = (dp[i - 1] + dp[i - 2]) % 10007
    }

    println(dp[n])
}