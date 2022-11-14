// https://www.acmicpc.net/problem/1822
package solution1822__code2

import java.io.StreamTokenizer

private fun binarySearch(arr:IntArray, value:Int) : Boolean{
    var left = 0
    var right = arr.size

    while(left < right){
        val mid = (left + right).div(2)
        if(arr[mid] == value){
            return true
        }
        else if(arr[mid] > value){
            right = mid
        }
        else{
            left = mid + 1
        }
    }
    return false
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }

    val nA = input()
    val nB = input()

    val aArr = IntArray(nA){ input() }
    aArr.sort()

    val bArr = IntArray(nB){ input() }
    bArr.sort()

    val sb = StringBuilder()
    var count = 0

    for(num in aArr){
        if(!binarySearch(bArr, num)){
            sb.append(num).append(' ')
            count += 1
        }
    }

    println(count)
    println(sb.toString())
}
