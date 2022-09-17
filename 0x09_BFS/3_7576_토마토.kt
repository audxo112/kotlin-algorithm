//https://www.acmicpc.net/problem/7576
package solution7576

import java.util.*

private class Point(val x:Int, val y:Int)

private val dx = intArrayOf(0, 1, 0, -1)
private val dy = intArrayOf(1, 0, -1, 0)

private fun solution(m:Int, n:Int, graph:Array<IntArray>, tomato:Queue<Point>, minus:Int) : Int{
    var zero = m * n - tomato.size - minus
    if(zero == 0){
        return 0
    }

    var date = 0
    while(tomato.isNotEmpty()){
        repeat(tomato.size){
            val tPos = tomato.poll()

            for(dir in 0 .. 3){
                val nx = tPos.x + dx[dir]
                val ny = tPos.y + dy[dir]

                if(nx < 0 || nx >= m || ny < 0 || ny >= n || graph[ny][nx] != 0){
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
    var tokenizer = StringTokenizer(readLine(), " ")
    val m = tokenizer.nextToken().toInt()
    val n = tokenizer.nextToken().toInt()

    val tomato = LinkedList<Point>()
    var minus = 0
    val graph = Array(n){ y ->
        tokenizer = StringTokenizer(readLine(), " ")
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