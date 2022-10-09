// https://www.acmicpc.net/problem/1149
package solution1149

/**
 * IntArray.min() 이 백준에서 안됨
 */


import java.util.StringTokenizer


private fun solution(n:Int, houses:Array<IntArray>) : Int{
    val dp = Array(n + 1){
        intArrayOf(0, 0, 0)
    }

    dp[1] = houses[1]
    for(i in 2 .. n){
        dp[i][0] = houses[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2])
        dp[i][1] = houses[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2])
        dp[i][2] = houses[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1])
    }

    return Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2])
}


private fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val houses = Array(n + 1){i ->
        if(i == 0){
            intArrayOf(0, 0, 0)
        }
        else{
            val tokenizer = StringTokenizer(readLine(), " ")
            intArrayOf(
                tokenizer.nextToken().toInt(),
                tokenizer.nextToken().toInt(),
                tokenizer.nextToken().toInt()
            )
        }
    }
    println(solution(n, houses))
}