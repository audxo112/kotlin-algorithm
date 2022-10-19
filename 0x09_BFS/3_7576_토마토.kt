
import java.util.*

private val dx = listOf(0,0,1,-1)
private val dy = listOf(1,-1,0,0)
private lateinit var graph : Array<IntArray>

fun main(){
    val (m,n) = readln().split(" ").map { it.toInt() }
    graph = Array(n) {IntArray(m) {0} }
    val tomatoes = LinkedList<Pair<Int,Int>>()


    for (i in 0 until n){
        val line = readln().split(" ").map{it.toInt()}
        line.forEachIndexed{index,it ->
            graph[i][index] = it
            if (it == 1){
                tomatoes.add(i to index)
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

private fun bfs(n:Int,m:Int,graph:Array<IntArray>,tomatoes:LinkedList<Pair<Int,Int>>){

    while (tomatoes.isNotEmpty()){
        val (x,y) = tomatoes.poll()

        for (i in 0 until 4){
            val nx = x + dx[i]
            val ny = y + dy[i]

            if(nx < 0 || ny < 0 || nx >= n || ny >= m || graph[nx][ny] == -1) continue
            else if (graph[nx][ny] == 0){
                graph[nx][ny] = graph[x][y] + 1
                tomatoes.add(nx to ny)
            }
        }
    }
}