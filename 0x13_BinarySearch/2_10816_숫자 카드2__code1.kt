// https://www.acmicpc.net/problem/10816
package solution10816

import java.io.StreamTokenizer

private fun binaryLeftSearch(arr:IntArray, value:Int) : Int{
    var left = 0
    var right = arr.size

    while(left < right){
        val mid = left + (right - left).div(2)
        if(arr[mid] < value){
            left = mid + 1
        }
        else{
            right = mid
        }
    }
    return right
}

private fun binaryRightSearch(arr:IntArray, value:Int) : Int{
    var left = 0
    var right = arr.size

    while(left < right){
        val mid = left + (right - left).div(2)
        if(arr[mid] <= value){
            left = mid + 1
        }
        else{
            right = mid
        }
    }
    return right
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val nArr = IntArray(n){
        input()
    }
    nArr.sort()

    val m = input()
    val mArr = IntArray(m){
        input()
    }
    val sb = StringBuilder()
    for(num in mArr){
        val left = binaryLeftSearch(nArr, num)
        val right = binaryRightSearch(nArr, num)
        sb.append(right - left).append(" ")
    }
    println(sb.toString())
}

