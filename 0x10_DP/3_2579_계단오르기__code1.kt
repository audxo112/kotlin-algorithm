// https://www.acmicpc.net/problem/2579
package solution2579

/**
 * 방법이 도저히 생각이 안나 해설 보고 품
 * https://yabmoons.tistory.com/510
 *
 * n == 0 일때, dp[0] == 0 의 경우를 예외처리 안했음
 */


private fun getDp(n:Int, stairs:IntArray, dp:IntArray) : Int{
    if(dp[n] > 0){
        return dp[n]
    }

    dp[n] = stairs[n] + Math.max(
        getDp(n - 2, stairs, dp),
        stairs[n - 1] + getDp(n - 3, stairs, dp)
    )

    return dp[n]
}


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
            getDp(n, stairs, dp)
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