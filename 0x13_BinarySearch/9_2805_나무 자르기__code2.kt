// https://www.acmicpc.net/problem/2805
package solution2805

import java.io.StreamTokenizer

/*
 * Parameteric Search
 */

private fun binarySearch(max: Int, arr: IntArray, value: Int): Int {
    var left = 0
    var right = max + 1

    while (left <= right) {
        val mid = (left + right) / 2
        if (isEnough(arr, mid, value)) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return right
}

private fun isEnough(arr: IntArray, mid: Int, value: Int): Boolean {
    var len = 0L
    for (num in arr) {
        if (num > mid) {
            len += num - mid
            if (len >= value) {
                return true
            }
        }
    }
    return false
}


private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    var max = 0
    val arr = IntArray(n) {
        input().also { value ->
            if (value > max) {
                max = value
            }
        }
    }

    println(binarySearch(max, arr, m))
}