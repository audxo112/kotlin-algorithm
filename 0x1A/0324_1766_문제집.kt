package solve_1766

import java.io.StreamTokenizer
import java.util.*

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val inDegree = IntArray(n + 1)

    val graph = Array(n + 1) {
        LinkedList<Int>()
    }
    repeat(m) {
        val a = input()
        val b = input()
        graph[a].add(b)
        inDegree[b] += 1
    }

    solve(inDegree, graph)
}

private fun solve(inDegree: IntArray, graph: Array<LinkedList<Int>>) {

    val queue = PriorityQueue<Int>()
    val sb = StringBuilder()

    for (i in 1..inDegree.lastIndex) {
        if (inDegree[i] == 0) {
            queue.add(i)
        }
    }

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        for (i in graph[cur]) {
            inDegree[i]--
            if (inDegree[i] == 0) {
                queue.add(i)
            }
        }
        sb.append("$cur ")
    }

    println(sb)
}

