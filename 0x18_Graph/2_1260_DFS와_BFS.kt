// https://www.acmicpc.net/problem/1260
package solution1260

import java.io.StreamTokenizer
import java.util.*

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val v = input()

    val graph = Array(n + 1){
        LinkedList<Int>()
    }
    repeat(m){
        val x = input()
        val y = input()

        graph[x].add(y)
        graph[y].add(x)
    }

    for(i in 1 .. n){
        graph[i].sort()
    }

    val sb = StringBuilder()

    val dfsVisited = BooleanArray(n + 1)
    dfsVisited[v] = true
    sb.append("$v ")
    fun dfs(x: Int){
        for(child in graph[x]){
            if(dfsVisited[child]){
                continue
            }
            dfsVisited[child] = true
            sb.append("$child ")
            dfs(child)
        }
    }

    fun bfs(){
        val visited = BooleanArray(n + 1)

        val queue:Queue<Int> = LinkedList()
        visited[v] = true
        sb.append("$v ")
        queue.add(v)

        while(queue.isNotEmpty()){
            val block = queue.poll()

            for(child in graph[block]){
                if(visited[child]){
                    continue
                }
                visited[child] = true
                sb.append("$child ")
                queue.add(child)
            }
        }
    }

    dfs(v)
    sb.appendLine()
    bfs()

    println(sb.toString())
}