// https://www.acmicpc.net/problem/10815
package solution10815

import java.io.StreamTokenizer

private fun binarySearch(arr:IntArray, value:Int) : String{
    var left = 0
    var right = arr.size

    while(left < right){
        val mid = (left + right).div(2)
        if(arr[mid] == value){
            return "1 "
        }
        else if(arr[mid] < value){
            left = mid + 1
        }
        else{
            right = mid
        }
    }
    return "0 "
}


private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val arr = IntArray(n){ input() }
    arr.sort()

    val sb = StringBuilder()
    repeat(input()){
        sb.append(binarySearch(arr, input()))
    }
    println(sb.toString())
}