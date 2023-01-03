package `kotlin-algorithm`.`0x14_TwoPointer`

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val d = input()
    val k = input()
    val c = input()

    val arr = IntArray(n + k)
    for (i in 0 until n) {
        arr[i] = input()
    }

    for (i in 0 until k) {
        arr[n + i] = arr[i]
    }

    val visited = IntArray(d + 1)
    visited[c] = 1

    var start = 0
    var end = 0
    var cnt = 1
    var answer = 0
    while (start + k < arr.size) {
        visited[arr[end]]++
        if (visited[arr[end]] == 1) {
            cnt++
        }

        if (end >= start + k) {
            visited[arr[start]]--
            if (visited[arr[start]] == 0) {
                cnt--
            }
            start++
        }

        answer = max(answer, cnt)
        end++
    }

    println(answer)
}