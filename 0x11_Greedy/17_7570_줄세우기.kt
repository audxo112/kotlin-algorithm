package `kotlin-algorithm`.`0x11_Greedy`

import java.util.StringTokenizer

fun main(){
    val n = readln().toInt()
    val st = StringTokenizer(readln())
    val dp = IntArray(n+1)

    while (st.hasMoreTokens()){
        val child = st.nextToken().toInt()
        dp[child] = dp[child-1] + 1

    }
    println(n - dp.maxOrNull()!!)
}