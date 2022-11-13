// https://www.acmicpc.net/problem/1654
package solution1654

import java.io.StreamTokenizer

private fun binarySearch(arr:LongArray, n:Int) : Long{
    var left = 1L
    var right = arr.fold(0L){ total, len -> total + len }.div(arr.size) + 1

    while(left < right){
        val mid = (left + right).div(2)
        val count = arr.fold(0L){ count, len -> count + len.div(mid) }

        if(count < n){
            right = mid
        }
        else {
            left = mid + 1
        }
    }
    return left - 1
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Long{
        nextToken()
        return nval.toLong()
    }

    val k = input().toInt()
    val n = input().toInt()
    val arr = LongArray(k){ input() }

    println(binarySearch(arr, n))
}