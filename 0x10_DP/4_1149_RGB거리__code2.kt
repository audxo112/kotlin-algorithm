// https://www.acmicpc.net/problem/1149
package solution1149

/**
 * 입력과 동시에 최소 값을 진행
 */

import java.util.StringTokenizer
import kotlin.math.min


private fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val dp = Array(n) {
        IntArray(3)
    }
    var tokenizer = StringTokenizer(readLine(), " ")
    dp[0][0] = tokenizer.nextToken().toInt()
    dp[0][1] = tokenizer.nextToken().toInt()
    dp[0][2] = tokenizer.nextToken().toInt()
    repeat(n - 1){ i ->
        tokenizer = StringTokenizer(readLine(), " ")
        dp[i + 1][0] = min(dp[i][1], dp[i][2]) + tokenizer.nextToken().toInt()
        dp[i + 1][1] = min(dp[i][0], dp[i][2]) + tokenizer.nextToken().toInt()
        dp[i + 1][2] = min(dp[i][0], dp[i][1]) + tokenizer.nextToken().toInt()
    }
    print(min(dp[n - 1][0], min(dp[n - 1][1], dp[n - 1][2])))
}