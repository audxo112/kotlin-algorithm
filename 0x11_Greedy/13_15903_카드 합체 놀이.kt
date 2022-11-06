// https://www.acmicpc.net/problem/15903
package solution15903

/**
 * 가장 작은 수 2개를 빼고 우선순위큐에 넣는다
 */

import java.io.StreamTokenizer
import java.util.PriorityQueue


private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }
    val N = input().toInt()
    val M = input().toInt()
    val cards = PriorityQueue<Long>()
    var sum = 0L
    repeat(N){
        input().also{ value ->
            cards.offer(value)
            sum += value
        }
    }

    repeat(M){
        (cards.poll() + cards.poll()).also{value ->
            sum += value
            cards.offer(value)
            cards.offer(value)
        }
    }
    println(sum)
}