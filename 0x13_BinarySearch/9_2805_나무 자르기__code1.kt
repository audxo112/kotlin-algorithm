// https://www.acmicpc.net/problem/2805
package solution2805

import java.io.StreamTokenizer

/*
 * Parameteric Search
 * isEnough 사용 전
 */
private fun binarySearch(max: Long, arr: LongArray, value: Long): Long {
    var left = 0L
    var right = max + 1

    while (left < right) {
        val mid = (left + right) / 2L
        var len = 0L
        arr.forEach {
            if(it > mid){
                len += it - mid
            }
        }

        if (len >= value) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    return right - 1
}


private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }

    val n = input().toInt()
    val m = input()

    var max = 0L
    val arr = LongArray(n) {
        input().also { value ->
            if(value > max){
                max = value
            }
        }
    }

    println(binarySearch(max, arr, m))
}