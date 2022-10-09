// https://www.acmicpc.net/problem/11659
package solution11659

import java.util.StringTokenizer

private fun main() = with(System.`in`.bufferedReader()){
    var tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    val dp = IntArray(n + 1)

    tokenizer = StringTokenizer(readLine(), " ")
    for(i in 1 .. n){
        dp[i] = dp[i - 1] + tokenizer.nextToken().toInt()
    }

    val sb = StringBuilder()
    repeat(m){
        tokenizer = StringTokenizer(readLine(), " ")
        val i = tokenizer.nextToken().toInt()
        val j = tokenizer.nextToken().toInt()
        sb.appendLine(dp[j] - dp[i - 1])
    }
    println(sb.toString())
}