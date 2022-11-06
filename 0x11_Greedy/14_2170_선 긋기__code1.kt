// https://www.acmicpc.net/problem/2170
package solution2170

/**
 *
 */

import java.io.StreamTokenizer

private data class Line(val x:Long, val y:Long)

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }

    val nums = Array(input().toInt()){
        Line(input(), input())
    }.sortedWith { num1, num2 ->
        if(num1.x == num2.x) num1.y.compareTo(num2.y) else num1.x.compareTo(num2.x)
    }

    var sum = 0L
    var start = nums[0].x
    var end = nums[0].y

    for(i in 1 until nums.size){
        if(nums[i].x > end){
            sum += end - start
            start = nums[i].x
            end = nums[i].y
        }
        else if(nums[i].y > end){
            end = nums[i].y
        }
    }
    sum += end - start
    println(sum)
}