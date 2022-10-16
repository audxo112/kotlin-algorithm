// https://www.acmicpc.net/problem/1463
package solution1463


/**
 * A.coerceAtMost(B)
 * A의 "최대값"을 B로 한다
 *
 * A.coerceAtLess(B)
 * A의 "최소값"을 B로 한다
 */


private fun solution(N:Int) : Int {
    val dp = IntArray(N + 1)
    for (n in 2..N){
        dp[n] = dp[n - 1] + 1
        if(n % 2 == 0){
            dp[n] = (dp[n / 2] + 1).coerceAtMost(dp[n])
        }
        if(n % 3 == 0){
            dp[n] = (dp[n / 3] + 1).coerceAtMost(dp[n])
        }
    }
    return dp[N]
}


private fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    println(solution(n))
}