//https://www.acmicpc.net/problem/2178
package solution2178


import java.util.LinkedList
import java.util.StringTokenizer

private class Node(val x:Int, val y:Int)

private val dx = intArrayOf(0, 1, 0, -1)
private val dy = intArrayOf(1, 0, -1, 0)

private fun solution(n:Int, m:Int, graph:Array<IntArray>) : Int{
    val visited = Array(n + 2){
        BooleanArray(m + 2)
    }

    val deque = LinkedList<Node>()
    deque.add(Node(1, 1))
    visited[1][1] = true

    var dist = 1
    while(deque.isNotEmpty()){
        dist += 1
        repeat(deque.size){
            val cur = deque.poll()
            for(dir in 0 .. 3){
                val nx = cur.x + dx[dir]
                val ny = cur.y + dy[dir]

                if(graph[ny][nx] == 0 || visited[ny][nx]){
                    continue
                }

                if(nx == m && ny == n){
                    return dist
                }

                visited[ny][nx] = true
                deque.add(Node(nx, ny))
            }
        }
    }
    return -1
}

private fun main() = with(System.`in`.bufferedReader()){
    val tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    val graph = Array(n + 2){ni ->
        if(ni == 0 || ni == n + 1) {
            IntArray(m + 2)
        }
        else {
            val line = readLine()
            IntArray(m + 2){ mi ->
                if(mi == 0 || mi == m + 1) 0 else line[mi - 1].digitToInt()
            }
        }
    }

    println(solution(n, m, graph))
}