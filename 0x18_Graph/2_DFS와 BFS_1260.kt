package solve_1260

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.Queue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val v = input()

    val graph = Array(n + 1) {
        ArrayList<Int>()
    }


    repeat(m) {
        val a = input()
        val b = input()
        graph[a].add(b)
        graph[b].add(a)
    }

    for (arr in graph) {
        arr.sort()
    }

    var visited = BooleanArray(n + 1)
    var sb = StringBuilder()
    sb.append("$v ")
    dfs(v, graph, visited, sb)
    visited = BooleanArray(n + 1)
    println(sb)

    sb = StringBuilder()
    sb.append("$v ")
    bfs(v, graph, visited, sb)
    println(sb)

}

private fun dfs(start: Int, graph: Array<ArrayList<Int>>, visited: BooleanArray, sb: StringBuilder) {
    visited[start] = true
    for (i in graph[start]) {
        if (visited[i].not()) {
            sb.append("$i ")
            dfs(i, graph, visited, sb)
        }
    }
}

private fun bfs(start: Int, graph: Array<ArrayList<Int>>, visited: BooleanArray, sb: StringBuilder) {
    visited[start] = true
    val queue: Queue<Int> = LinkedList()
    queue.add(start)

    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        for (i in graph[cur]) {
            if(visited[i].not()){
                sb.append("$i ")
                queue.add(i)
                visited[i] = true
            }
        }
    }
}