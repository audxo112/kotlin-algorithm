// https://www.acmicpc.net/problem/1644
package solution1644__code2

import kotlin.math.sqrt

private fun solution(n: Int): Int {
    // 에라토스테네스의 체를 이용하여 소수 판별
    val visited = BooleanArray(n + 1)
    visited[1] = true
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (visited[i]) {
            continue
        }
        for (j in i * i .. n step i) {
            visited[j] = true
        }
    }

    var left = 2
    var right = 2
    var sum = 2
    var count = 0

    while (right <= n) {
        if (sum <= n) {
            if (sum == n) {
                count += 1
            }
            // 소수를 찾을때까지 right 를 증가
            do {
                right += 1
                if (right > n) {
                    return count
                }
            } while (visited[right])
            sum += right
        } else {
            sum -= left
            // 소수를 찾을때까지 left 를 증가
            do {
                left += 1
                if (left > right) {
                    return count
                }
            } while (visited[left])
        }
    }
    return count
}


private fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    println(solution(n))
}