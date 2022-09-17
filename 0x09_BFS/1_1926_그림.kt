//https://www.acmicpc.net/problem/1926
package solution1926

import java.util.Queue
import java.util.LinkedList
import java.util.StringTokenizer

private class Node(val x:Int, val y:Int)

private val dx = intArrayOf(0, 1, 0, -1)
private val dy = intArrayOf(1, 0, -1, 0)

private fun bfs(n:Int, m:Int, graph: Array<Array<Int>>, sx:Int, sy:Int) : Int{
    val queue: Queue<Node> = LinkedList()
    queue.add(Node(sx, sy))

    graph[sy][sx] = 0
    var size = 1

    while(queue.isNotEmpty()){
        val cur = queue.poll()

        for(dir in 0 .. 3){
            val nx = cur.x + dx[dir]
            val ny = cur.y + dy[dir]

            if(nx < 0 || nx >= m || ny < 0 || ny >= n || graph[ny][nx] == 0){
                continue
            }

            size += 1
            graph[ny][nx] = 0
            queue.add(Node(nx, ny))
        }
    }
    return size
}

private fun solution(n:Int, m:Int, graph:Array<Array<Int>>) : Array<Int>{
    var count = 0
    var maxSize = 0

    for (y in 0 until n){
        for (x in 0 until m){
            if(graph[y][x] == 1){
                count += 1
                maxSize = Integer.max(bfs(n, m, graph, x, y), maxSize)
            }
        }
    }

    return arrayOf(
        count, maxSize
    )
}

private fun main() = with(System.`in`.bufferedReader()){
    var tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    val graph = Array(n){
        tokenizer = StringTokenizer(readLine(), " ")
        Array(m){
            tokenizer.nextToken().toInt()
        }
    }

    solution(n, m, graph).forEach {
        println(it)
    }
}