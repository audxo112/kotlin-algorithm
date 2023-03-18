package solve_2606

import java.io.StreamTokenizer
import java.util.*

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    val computers = Array(n + 1) {
        LinkedList<Int>()
    }

    val visited = BooleanArray(n + 1)

    repeat(m) {
        val s = input()
        val e = input()
        computers[s].add(e)
        computers[e].add(s)
    }

    val queue: Queue<Int> = LinkedList()
    queue.add(1)

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        visited[cur] = true

        for(c in computers[cur]){
            if(visited[c].not()){
                visited[c] = true
                queue.add(c)
            }
        }
    }

    println(visited.count{it} - 1)
}