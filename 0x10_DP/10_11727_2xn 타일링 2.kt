// https://www.acmicpc.net/problem/11727
package solution11727


/**
 * 1개를 만드는 경우의 수 1
 * 2개를 만드는 경우의 수 3
 * 1 : 1개를 만드는 경우의 수
 * 0 : 2개를 만드는 경우의 수
 */

private fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val dp = IntArray(N + 2)
    dp[1] = 1
    dp[2] = 3
    for(n in 3 .. N){
        dp[n] = (dp[n - 1] + (dp[n - 2] shl 1)) % 10007
    }
    println(dp[N])
}