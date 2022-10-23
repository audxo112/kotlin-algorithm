// https://www.acmicpc.net/problem/1003
package solution1003


/**
 * 0일때만 다른 값이므로 0인 경우를 따로 처리하고
 * 나머지는 1에 대한 dp로 계산
 */


private fun main() = with(System.`in`.bufferedReader()){
    val t = readLine().toInt()
    val sb = StringBuilder()
    val dp = IntArray(41)
    dp[1] = 1

    var cN = 1
    repeat(t){
        val N = readLine().toInt()
        if(N == 0){
            sb.appendLine("1 0")
        }
        else{
            if(N > cN){
                for(n in cN + 1 .. N){
                    dp[n] = dp[n - 1] + dp[n - 2]
                }
                cN = N
            }
            sb.appendLine("${dp[N - 1]} ${dp[N]}")
        }
    }
    println(sb)
}
