// https://www.acmicpc.net/problem/11000
package solution11000

import java.util.PriorityQueue
import java.io.StreamTokenizer

private data class Lecture(val start:Int, val end:Int)

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    val arr = Array(N){
        Lecture(input(), input())
    }
    arr.sortBy { it.start }

    val queue = PriorityQueue<Int>()
    queue.offer(arr[0].end)
    for(i in 1 until N){
        if(queue.peek() <= arr[i].start){
            queue.poll()
        }
        queue.offer(arr[i].end)
    }

    println(queue.size)
}