// https://www.acmicpc.net/problem/2193
package solution2193


private fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()

    val dp = LongArray(91)
    dp[1] = 1
    dp[2] = 1
    for(n in 3 .. N){
        dp[n] = dp[n - 1] + dp[n - 2]
    }
    println(dp[N])
}