
import java.util.*
import kotlin.collections.ArrayList

private val dx = listOf(0,0,1,-1)
private val dy = listOf(1,-1,0,0)
private lateinit var graph : Array<IntArray>
private lateinit var visited : Array<BooleanArray>

fun main(){
    val (m,n) = readln().split(" ").map { it.toInt() }
    graph = Array(n) {IntArray(m) {0} }
    visited = Array(n) {BooleanArray(m) {false} }
    val tomatoes = ArrayList<Pair<Int,Int>>()


    for (i in 0 until n){
        val line = readln().split(" ").map{it.toInt()}
        line.forEachIndexed{index,it ->
            graph[i][index] = it
        }
    }

    for (i in 0 until n){
        for (j in 0 until m){
            if (graph[i][j] == 1) {
                tomatoes.add(j to i)
            }
        }
    }
    bfs(n,m,graph,tomatoes)

    var check = 0
    var max = 0
    for(i in 0 until n) {
        for(j in 0 until m) {
            if (graph[i][j] == 0)
                check += 1
            if (graph[i][j] > max){
                max = graph[i][j]
            }
        }
    }

    if (check != 0)
        println(-1)
    else
        println(max-1)

}

private fun bfs(n:Int,m:Int,graph:Array<IntArray>,tomatoes:ArrayList<Pair<Int,Int>>){

    val q = LinkedList<Pair<Int,Int>>()
    tomatoes.forEach { q.add(it) }

    while (q.isNotEmpty()){
        val (x,y) = q.poll()

        for (i in 0 until 4){
            val nx = x + dx[i]
            val ny = y + dy[i]

            if(nx < 0 || ny < 0 || nx >= m || ny >= n || graph[ny][nx] == -1) continue
            else if (graph[ny][nx] == 0){
                graph[ny][nx] = graph[y][x] + 1
                q.add(nx to ny)
                continue
            }
            else continue
        }
    }
}