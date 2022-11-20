// https://www.acmicpc.net/problem/1654
package solution1654__code2

import java.io.StreamTokenizer

private fun binarySearch(arr:LongArray, n:Int) : Long{
    var left = 1L
    var right = arr.fold(0L){ total, len -> total + len }.div(arr.size) + 1

    while(left < right){
        val mid = (left + right) / 2
        if(isEnough(arr, mid, n)){
            left = mid + 1
        }
        else {
            right = mid
        }
    }
    return left - 1
}

private fun isEnough(arr:LongArray, mid:Long, n:Int) : Boolean{
    var count = 0L
    for(num in arr){
        count += num.div(mid)
        if(count >= n){
            return true
        }
    }
    return false
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