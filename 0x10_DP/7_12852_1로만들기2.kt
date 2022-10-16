package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = IntArray(n + 1)
    val trace = IntArray(n + 1)
    dp[1] = 0

    for (i in 2..n) {
        dp[i] = dp[i - 1] + 1
        trace[i] = i - 1

        if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
            dp[i] = dp[i / 3] + 1
            trace[i] = i / 3
        }

        if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
            dp[i] = dp[i / 2] + 1
            trace[i] = i / 2
        }
    }

    val sb = StringBuilder()
    sb.appendLine(dp[n])

    var idx = n
    while(idx > 0) {
        sb.append("$idx ")
        idx = trace[idx]
    }
    sb.appendLine()

    println(sb)
}