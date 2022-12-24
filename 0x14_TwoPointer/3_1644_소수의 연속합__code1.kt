// https://www.acmicpc.net/problem/1644
package solution1644__code1

import kotlin.math.sqrt

private fun solution(n: Int): Int {
    if(n == 1){
        return 0
    }

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

    // 누산 값 리스트를 만듬
    val arr = IntArray(n / 2 + 2)
    var index = 1
    for (i in 1 .. n){
        if(visited[i]){
            continue
        }
        arr[index] = arr[index - 1] + i
        index ++
    }

    var left = 0
    var right = 0
    var count = 0

    while (left < index) {
        val sum = arr[right] - arr[left]
        if(sum < n){
            right += 1
            if(right >= index){
                return count
            }
        } else if (sum > n){
            left += 1
        } else {
            count += 1
            left += 1
        }
    }

    return count
}

private fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    println(solution(n))
}