//https://www.acmicpc.net/problem/2178


import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque


private val dirs = arrayOf(
    1 to 0, 0 to -1, -1 to 0, 0 to 1
)

private fun solution(n:Int, m:Int, graph:Array<Array<Int>>) : Int{
    val visited = Array(n + 2){
        Array(m + 2){ false }
    }

    val deque = ArrayDeque<Pair<Int, Int>>()
    deque.add(1 to 1)
    visited[1][1] = true

    var dist = 1
    while(deque.isNotEmpty()){
        dist += 1
        repeat(deque.size){
            val (x, y) = deque.removeFirst()
            for ((dx, dy) in dirs){
                val (nx, ny) = x + dx to y + dy
                if(graph[ny][nx] == 0 || visited[ny][nx]){
                    continue
                }

                if(nx == m && ny == n){
                    return dist
                }

                visited[ny][nx] = true
                deque.add(nx to ny)
            }
        }
    }
    return -1
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = br.readLine().split(" ").map{
        it.toInt()
    }

    val graph = Array(n + 2){ni ->
        if(ni == 0 || ni == n + 1) {
            Array(m + 2){ 0 }
        }
        else {
            val line = br.readLine()
            Array(m + 2){ mi ->
                if(mi == 0 || mi == m + 1) 0 else line[mi - 1].digitToInt()
            }
        }
    }
    br.close()

    val answer = solution(n, m, graph)
    bw.write("$answer\n")
    bw.flush()
    bw.close()
}