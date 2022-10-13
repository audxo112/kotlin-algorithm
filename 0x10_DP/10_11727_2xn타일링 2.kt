package `kotlin-algorithm`.`0x10_DP`

import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()

    val dp = IntArray(1001)

    dp[1] = 1
    dp[2] = 3
    for(i in 3..n){
        dp[i] = (dp[i-2] * 2 + dp[i-1]) % 10007
    }
    println(dp[n])
}