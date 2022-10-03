// https://www.acmicpc.net/problem/2579
package solution2579

/**
 * 뒤에서 부터 찾아갈 필요가 없이 앞에서 부터 찾아가도 됨
 */


private fun solution(n:Int, stairs:IntArray) : Int{
    return when(n){
        1 -> stairs[1]
        2 -> stairs[1] + stairs[2]
        3 -> stairs[3] + Math.max(stairs[1], stairs[2])
        else -> {
            val dp = IntArray(n + 1)
            dp[1] = stairs[1]
            dp[2] = stairs[2] + stairs[1]
            dp[3] = stairs[3] + Math.max(stairs[1], stairs[2])
            for(i in 4 .. n){
                dp[i] = stairs[i] + Math.max(stairs[i - 1] + dp[i - 3],  dp[i - 2])
            }
            dp[n]
        }
    }
}


private fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val stairs = IntArray(n + 1)
    for(i in 1 .. n){
        stairs[i] = readLine().toInt()
    }
    println(solution(n, stairs))
}