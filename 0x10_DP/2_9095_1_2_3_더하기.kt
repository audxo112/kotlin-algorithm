// https://www.acmicpc.net/problem/9095
package solution9095


private fun solution(n:Int, dp:IntArray) : Int{
    if(dp[n] > 0){
        return dp[n]
    }
    dp[n] = solution(n - 1, dp) + solution(n - 2, dp) + solution(n - 3, dp)
    return dp[n]
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