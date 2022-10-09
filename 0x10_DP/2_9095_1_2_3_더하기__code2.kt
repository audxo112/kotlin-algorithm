// https://www.acmicpc.net/problem/9095
package solution9095


private fun solution(N:Int, dp:IntArray) : Int{
    if(dp[N] > 0){
        return dp[N]
    }

    for(n in 4 .. N){
        dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3]
    }
    return dp[N]
}


private fun main() = with(System.`in`.bufferedReader()){
    val t = readLine().toInt()
    val dp = IntArray(11)
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4
    for(i in 0 until t){
        val n = readLine().toInt()
        println(solution(n, dp))
    }
}