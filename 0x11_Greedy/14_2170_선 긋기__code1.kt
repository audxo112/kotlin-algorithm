// https://www.acmicpc.net/problem/2170
package solution2170

/**
 * 시작값 기준으로 정렬을 한것보다
 * 시작값 정렬, 같은경우 끝값 정렬을 하는 경우가 더 빠름
 *
 * 새로운 시작값이 기존의 끝보다 큰경우 이어지지 않은선
 * -> 지금까지 기록된 길이를 저장 후 start와 end를 새로 지정
 * 시작 값이 끝보다 작고 끝값이 더 크다면 end를 지금 선의 끝으로 변경
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