// https://www.acmicpc.net/problem/14889
package solution14889

import java.io.StreamTokenizer
import kotlin.math.abs

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val nn = n / 2
    val map = Array(n) { IntArray(n) { input() } }
    // 각 선수가 다른 선수와 함께 했을떄 행, 열의 값을 전부 더함
    val abilitySum = IntArray(n)
    for (y in 0 until n) {
        for (x in 0 until n) {
            abilitySum[x] += map[y][x]
            abilitySum[y] += map[y][x]
        }
    }

    // 모든 합의 절반은 모든 능력치의 합
    val allSum = abilitySum.sum() / 2

    var minVal = Int.MAX_VALUE
    fun backTracking(N: Int, M: Int, sum: Int) {
        if(N == nn){
            // 선택된 선수의 값의 합 - 전체합은
            // 선택합 선수의 값 - 선택하지 않은 선수의 값으로 변경됨
            minVal = abs(sum - allSum).coerceAtMost(minVal)
            return
        }

        for(i in M .. n + N - nn){
            backTracking(N + 1, i + 1, sum + abilitySum[i])
        }
    }

    // 0번째 수를 고정하여 절반을 줄임
    backTracking(1, 1, abilitySum[0])
    println(minVal)
}