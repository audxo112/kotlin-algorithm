// https://www.acmicpc.net/problem/18870
package solution18870__code1

import java.io.StreamTokenizer
import java.lang.StringBuilder

private fun binarySearch(arr:List<Int>, value:Int) : Int{
    var left = 0
    var right = arr.size
    while(left < right){
        val mid = (left + right).div(2)
        if(value > arr[mid]){
            left = mid + 1
        }
        else{
            right = mid
        }
    }
    return right
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val dp = hashMapOf<Int, Int>()
    val n = input()
    val arr = IntArray(n){ input() }
    val sorted = arr.distinct().sorted()
    val sb = StringBuilder()

    for(i in 0 until n){
        dp[arr[i]]?.let{
            sb.append(it).append(" ")
        } ?: run{
            val index = binarySearch(sorted, arr[i])
            dp[arr[i]] = index
            sb.append(index).append(" ")
        }
    }
    println(sb.toString())
}