package solve_5567

import java.io.StreamTokenizer
import java.util.*

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val m = input()

    val friends = Array(n+1){
        LinkedList<Int>()
    }
    val visited = IntArray(n+1) {
        n
    }
    repeat(m){
        val a = input()
        val b = input()

        friends[a].add(b)
        friends[b].add(a)
    }

    val queue : Queue<Int> = LinkedList()
    queue.add(1)
    visited[1] = 0
    while(queue.isNotEmpty()){
        val cur = queue.poll()
        val nowDepth = visited[cur]
        if(nowDepth <= 2){
            for(f in friends[cur]){
                if( visited[f] > nowDepth + 1) {
                    visited[f] = nowDepth + 1
                    queue.add(f)
                }
            }
        }
    }

    println(visited.count{ it in 1..2 })

}

