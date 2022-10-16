// https://www.acmicpc.net/problem/2193
package solution2193


/**
 * 값이 너무 커져서 Long 타입이 필요
 * 직전 경우의 수에서 가장 끝에 0이 붙는경우
 * 2회전 경우의 수에서 가장 끝에 01이 붙는경우
 */


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