// https://www.acmicpc.net/problem/16401
package solution16401

import java.io.StreamTokenizer


private fun binarySearch(arr: IntArray, max:Int, value: Int): Int {
    var left = 1
    var right = max

    while(left <= right){
        val mid = (left + right) / 2
        val count = arr.fold(0) { total, add -> total + add / mid }
        if(count < value){
            right = mid - 1
        }
        else{
            left = mid + 1
        }
    }
    return left - 1
}


private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val m = input()
    val n = input()
    var max = 0
    val arr = IntArray(n) {
        input().also{ value ->
            if(value > max){
                max = value
            }
        }
    }

    println(binarySearch(arr, max + 1, m))
}