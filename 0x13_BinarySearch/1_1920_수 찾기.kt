// https://www.acmicpc.net/problem/1920
package solution1920

import java.io.StreamTokenizer

private fun binarySearch(src:IntArray, value:Int) : Int{
    var left = 0
    var right = src.size

    while(left < right){
        val mid = (left + right).div(2)
        if(src[mid] == value){
            return 1
        }
        else if(src[mid] < value){
            left = mid + 1
        }
        else if(src[mid] > value){
            right = mid
        }
    }
    return 0
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val nArr = IntArray(n){ input() }
    nArr.sort()

    val sb = StringBuilder()
    repeat(input()){
        sb.appendLine(binarySearch(nArr, input()))
    }
    println(sb.toString())
}
