// https://www.acmicpc.net/problem/5567
package solution5567

import java.io.StreamTokenizer
import java.util.*

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    val friends = Array(n + 1){ LinkedList<Int>() }
    val visited = BooleanArray(n + 1)
    visited[1] = true

    repeat(m){
        val x = input()
        val y = input()

        friends[x].add(y)
        friends[y].add(x)
    }

    val queue: Queue<Int> = friends[1]
    var count = 0
    for(i in 0 until queue.size){
        val friend = queue.poll()
        if(!visited[friend]){
            visited[friend] = true
            count += 1
        }
        for(ff in friends[friend]){
            if(visited[ff]){
                continue
            }
            visited[ff] = true
            count += 1
        }
    }

    println(count)
}