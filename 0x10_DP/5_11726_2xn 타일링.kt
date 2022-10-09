// https://www.acmicpc.net/problem/11726
package solution11726

/**
 * 종이에 n=1 ~ 5 까지 그려보는데 
 * f(n) = f(n - 1) + f(n - 2) 라는 식이 나옴
 * 1 2 3 더하기와 같은 원리
 *
 * 생각해보니 IntArray(1001) 은 4004 byte = 4KB가 안되는 수치
 */

private fun solution(n:Int) : Int{
    val dp = IntArray(1001)
    dp[1] = 1
    dp[2] = 2

    for(i in 3 .. n){
        dp[i] = (dp[i - 1] + dp[i - 2]) % 10007
    }
    return dp[n]
}


private fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    println(solution(n))
}