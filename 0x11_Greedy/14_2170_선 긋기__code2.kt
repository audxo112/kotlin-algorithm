// https://www.acmicpc.net/problem/2170
package solution2170__code2

/**
 * 어차피 중복된 선이 많다면 처음 값을 넣을때 중복되는 선을 합쳐서 계산을 해본다면 ?
 * 새로운 선의 x값이 포함된 선이 존재하고 y값이 새로운 선이 더 크다면 기존선의 값을 변경
 * 없다면 선을 추가하지 않는다
 * y 값에 대해서도 동일
 */

import java.io.StreamTokenizer
import java.util.LinkedList

private class Line(var x:Long, var y:Long)

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }

    val N = input().toInt()
    val nums = LinkedList<Line>().apply{
        add(Line(input(), input()))
    }
    repeat(N - 1){
        val line = Line(input(), input())
        for(i in nums.indices){
            if(line.x in nums[i].x .. nums[i].y){
                if(line.y > nums[i].y){
                    nums[i].y = line.y
                }
                return@repeat
            }
            else if(line.y in nums[i].x .. nums[i].y){
                if(line.x < nums[i].x){
                    nums[i].x = line.x
                }
                return@repeat
            }
        }
        nums.add(line)
    }

    nums.sortWith{num1, num2 ->
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