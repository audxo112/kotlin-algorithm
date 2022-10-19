package `kotlin-algorithm`.`0x10_DP`

import java.util.StringTokenizer

fun main(){
    data class PinaryNum(val zero: Long, val one: Long)

    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()

    val dp = Array(91){
        PinaryNum(0,0)
    }
    dp[1] = PinaryNum(0,1)
    dp[2] = PinaryNum(1,0)

    for(i in 3..n){
        dp[i] = PinaryNum(dp[i-1].zero + dp[i-1].one, dp[i-1].zero)
    }

    println(dp[n].zero + dp[n].one)
}

//[(0,1),(1,0),(1,1),(2,1),(3,2)]