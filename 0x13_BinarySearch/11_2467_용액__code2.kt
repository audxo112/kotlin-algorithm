// https://www.acmicpc.net/problem/2467
package solution2467__code2

import java.io.StreamTokenizer
import kotlin.math.abs
import kotlin.math.absoluteValue

/*
 * TwoPointer 를 이용한 풀이
 */

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val arr = IntArray(input()){ input() }
    var left = 0
    var right = arr.size - 1
    var minLeft = arr[left]
    var minRight = arr[right]
    var minValue = Int.MAX_VALUE
    while(left < right){
        val absValue = abs(arr[left] + arr[right])
        if(absValue < minValue){
            minLeft = arr[left]
            minRight = arr[right]
            minValue = absValue
            if(minValue == 0){
                break
            }
        }
        // 어느쪽의 포인터를 움직어야 0에 가까워지는지 판단 하여 이동
        if(abs(arr[left + 1] + arr[right]) > abs(arr[left] + arr[right - 1])){
            right -= 1
        }
        else{
            left += 1
        }
    }

    println("$minLeft $minRight")
}