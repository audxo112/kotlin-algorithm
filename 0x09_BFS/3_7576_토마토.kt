//https://www.acmicpc.net/problem/7576

import java.util.StringTokenizer

private data class Point(val x:Int, val y:Int)


private val dirs = arrayOf(
    1 to 0, 0 to -1, -1 to 0, 0 to 1
)


private fun solution(m:Int, n:Int, graph:Array<IntArray>, tomato:ArrayDeque<Point>, minus:Int) : Int{
    var zero = m * n - tomato.size - minus
    if(zero == 0){
        return 0
    }

    var date = 0
    while(tomato.isNotEmpty()){
        repeat(tomato.size){
            val (x, y) = tomato.removeFirst()

            for((dx, dy) in dirs){
                val (nx, ny) = x + dx to y + dy

                if(nx !in 0 until m || ny !in 0 until n || graph[ny][nx] != 0){
                    continue
                }

                zero -= 1
                graph[ny][nx] = 1
                tomato.add(Point(nx, ny))
            }
        }
        date += 1
    }

    return if(zero == 0) date - 1 else -1
}


private fun main() = with(System.`in`.bufferedReader()){
    val (m, n) = readLine().split(" ").map{
        it.toInt()
    }

    val tomato = ArrayDeque<Point>()
    var minus = 0
    val graph = Array(n){ y ->
        val tokenizer = StringTokenizer(readLine())
        IntArray(m){ x ->
            tokenizer.nextToken()?.toInt()?.also{ value ->
                if(value == 1){
                    tomato.add(Point(x, y))
                }
                else if(value == -1){
                    minus += 1
                }
            } ?: 0
        }
    }

    println(solution(m, n, graph, tomato, minus))
}