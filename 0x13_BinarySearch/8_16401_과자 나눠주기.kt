// https://www.acmicpc.net/problem/16401
package solution16401

import java.io.StreamTokenizer


/*
 * Parameteric Search
 * 주영님의 isEnough 방식 도입
 */

private fun binarySearch(arr: IntArray, max:Int, value: Int): Int {
    var left = 1
    var right = max

    while(left <= right){
        val mid = (left + right) / 2
        if(isEnough(arr, mid, value)){
            left = mid + 1
        }
        else{
            right = mid - 1
        }
    }
    return left - 1
}

private fun isEnough(arr: IntArray, mid:Int, value: Int) : Boolean{
    var count = 0
    for(num in arr){
        count += num / mid
        if(count >= value){
            return true
        }
    }
    return false
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