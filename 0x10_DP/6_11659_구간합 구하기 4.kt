package `kotlin-algorithm`.`0x10_DP`

import java.util.*


fun main(){

    val (n,m) = readln().split(" ").map { it.toInt() }
    val st = StringTokenizer(readln())
    val dp = IntArray(n+1)
    for (k in 1..n){
        dp[k] = dp[k-1] + st.nextToken().toInt()
    }

    val sb = StringBuilder()
    for(k in 1..m){
        val (i,j) = readln().split(" ").map { it.toInt() }

        sb.appendLine(dp[j] - dp[i-1])
    }
    println(sb)
}