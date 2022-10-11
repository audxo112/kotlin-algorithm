package `kotlin-algorithm`.`0x10_DP`

import java.util.StringTokenizer

fun main(){
    var st = StringTokenizer(readln())
    val t = st.nextToken().toInt()
    val dp = Array(41){
        Pair(0,0)
    }
    dp[0] = Pair(1,0)
    dp[1] = Pair(0,1)
    for(i in 2..40){
        dp[i] = add(dp[i-1],dp[i-2])
    }

    for(i in 0 until t){
        st = StringTokenizer(readln())
        val n = st.nextToken().toInt()
        println("${dp[n].first} ${dp[n].second}")
    }
}
private fun add(a: Pair<Int,Int>,b: Pair<Int,Int>): Pair<Int,Int>{
    return Pair(a.first + b.first, a.second+b.second)
}
