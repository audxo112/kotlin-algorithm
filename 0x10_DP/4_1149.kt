package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = Array(n) {
        val st = StringTokenizer(br.readLine())
        IntArray(3) { st.nextToken().toInt() }
    }

    for (i in 1 until n) {
        dp[i][0] += minOf(dp[i - 1][1], dp[i - 1][2])
        dp[i][1] += minOf(dp[i - 1][0], dp[i - 1][2])
        dp[i][2] += minOf(dp[i - 1][0], dp[i - 1][1])
    }

    println(minOf(dp[n - 1][0], dp[n - 1][1], dp[n - 1][2])) // dp[n].min() 하면 백준에서 컴파일 에러
}