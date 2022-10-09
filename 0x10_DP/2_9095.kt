package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val dp = IntArray(12)
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4

    for (i in 4 until dp.size) {
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
    }

    val sb = StringBuilder()
    repeat(br.readLine().toInt()) {
        val n = br.readLine().toInt()
        sb.append("${dp[n]}\n")
    }

    println(sb.toString())
}