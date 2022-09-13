//https://www.acmicpc.net/problem/1926

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import java.util.ArrayDeque


private val dirs = arrayOf(1 to 0, 0 to -1, -1 to 0, 0 to 1)

private fun bfs(n:Int, m:Int, graph: Array<Array<Int>>, sx:Int, sy:Int) : Int{
    val deque = ArrayDeque<Pair<Int, Int>>().apply{
        add(sx to sy)
    }
    var size = 1
    graph[sy][sx] = 0

    while(deque.isNotEmpty()){
        val (x, y) = deque.removeFirst()

        for((dx, dy) in dirs){
            val (nx, ny) = x + dx to y + dy
            if(nx !in 0 until m || ny !in 0 until n || graph[ny][nx] == 0){
                continue
            }

            size += 1
            graph[ny][nx] = 0
            deque.add(nx to ny)
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
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = readLine().split(" ").map{
        it.toInt()
    }

    val graph = Array(n){
        val tokenizer = StringTokenizer(readLine())
        Array(m){
            tokenizer.nextToken()?.toInt() ?: 0
        }
    }

    solution(n, m, graph).forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}