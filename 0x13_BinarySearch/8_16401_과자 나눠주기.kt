// https://www.acmicpc.net/problem/16401
package solution16401

import java.io.StreamTokenizer


private fun binarySearch(arr: LongArray, min:Long, max:Long, value: Long): Long {
    var left = min
    var right = max

    while(left < right){
        val mid = (left + right) / 2
        val count = arr.fold(0L) { total, add -> total + add / mid }
        if(count < value){
            right = mid
        }
        else{
            left = mid + 1
        }
    }
    return left - 1
}


private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }

    val m = input()
    val n = input()
    var min = Long.MAX_VALUE
    var max = 0L
    val arr = LongArray(n.toInt()) {
        input().also{value ->
            min = value.coerceAtMost(min)
            max = value.coerceAtLeast(max)
        }
    }

    println(binarySearch(arr, 1L.coerceAtLeast(min / m), max + 1, m))
}