// https://www.acmicpc.net/problem/12852
package solution12852


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

    val sb = StringBuilder()
        .appendLine(getDp(N, dp))
        .append("$N ")

    var n = N
    while(n > 1){
        val div2 = dp[n / 2] + n % 2
        val div3 = dp[n / 3] + n % 3

        if (div2 < div3) {
            if(n % 2 == 1) {
                sb.append("${n - 1} ")
            }
            n /= 2
        }
        else {
            for (i in 1 .. n % 3){
                sb.append("${n - i} ")
            }
            n /= 3
        }
        sb.append("$n ")
    }

    return sb.toString()
}


private fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    println(solution(n))
}