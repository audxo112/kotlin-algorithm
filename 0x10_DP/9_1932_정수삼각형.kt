package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = Array(n) { IntArray(n) }
    val arr = Array(n) {
        val st = StringTokenizer(br.readLine())
        IntArray(n) {
            if (st.hasMoreTokens()) {
                st.nextToken().toInt()
            } else {
                0
            }
        }
    }

    dp[0][0] = arr[0][0]
    for (i in 1 until n) {
        for (j in 0..i) {
            dp[i][j] = arr[i][j] + when (j) {
                0 -> dp[i-1][j]
                i -> dp[i-1][j - 1]
                else -> maxOf(dp[i - 1][j - 1], dp[i - 1][j])
            }
        }
    }

    println(dp.last().maxOrNull().toString())
}