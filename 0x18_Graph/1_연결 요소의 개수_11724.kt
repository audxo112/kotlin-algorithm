package solve_11724

import java.io.StreamTokenizer
import java.util.*

private lateinit var graph: Array<LinkedList<Int>>
private lateinit var visited: BooleanArray

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    var answer = 0

    graph = Array(n + 1) {
        LinkedList<Int>()
    }
    visited = BooleanArray(n + 1)
    repeat(m) {
        val u = input()
        val v = input()

        graph[u].add(v)
        graph[v].add(u)
    }
    for (i in 1..n) {
        if (visited[i].not()) {
            bfs(i, visited)
            answer += 1
        }
    }
    println(answer)
}

fun bfs(start: Int, visited: BooleanArray) {
    val queue: Queue<Int> = LinkedList()
    queue.add(start)

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        if (visited[cur].not()) {
            graph[cur].forEach { queue.add(it) }
            visited[cur] = true
        }
    }
}