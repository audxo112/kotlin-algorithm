// https://www.acmicpc.net/problem/12852
package solution12852


/**
 * DP를 계산 후 경우의 수는 어떤걸 출력해도 상관이 없으므로
 * 1을 빼거나 나누어 떨어질 때 나누거나 했을 경우
 * dp[n] - 1 == dp[n - 1]
 * dp[n] - 1 == dp[n / 2]
 * dp[n] - 1 == dp[n / 3]
 * 결국 경우의 수가 다를 뿐 어디로 가든 목적지까지 거리는 같다
 */


private fun getDp(N:Int, dp:IntArray) : Int{
    for(n in 2 .. N){
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


private fun solution(N:Int) : String{
    val dp = IntArray(N + 1)
    dp[0] = -1

    val sb = StringBuilder().appendLine(getDp(N, dp))
    var n = N
    while(n > 0){
        sb.append("$n ")
        when{
            dp[n - 1] == dp[n] - 1 -> n -= 1
            n % 2 == 0 && dp[n / 2] == dp[n] - 1 -> n /= 2
            n % 3 == 0 && dp[n / 3] == dp[n] - 1 -> n /= 3
        }
    }

    return sb.toString()
}


private fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    println(solution(n))
}