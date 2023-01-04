package `kotlin-algorithm`.`0x14_TwoPointer`

import java.io.StreamTokenizer
import kotlin.math.max


fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val k = input()

    val arr = IntArray(n) {
        input()
    }
    val visited = IntArray(100001)

    var left = 0
    var right = 0

    var answer = 0

    while (right < n) {
        if (visited[arr[right]] < k) {
            visited[arr[right]]++
            right++
        } else {
            visited[arr[left]]--
            left++
        }
        answer = max(answer, right - left)
    }
    println(answer)
}