import java.util.*

private var n = 0
private var m = 0
private val dx = listOf(0,0,1,-1)
private val dy = listOf(1,-1,0,0)
private lateinit var graph : Array<IntArray>
private lateinit var visited : Array<BooleanArray>

fun main(args: Array<String>) = with(System.`in`.bufferedReader()){
    val input = readLine().split(" ").map { it.toInt() }
    n = input[0]
    m = input[1]
    graph = Array(n) {IntArray(m) {0} }
    visited = Array(n) {BooleanArray(m) {false} }
    for (i in 0 until n){
        val line = readLine().chunked(1).map { it.toInt() }
        line.forEachIndexed{index,it ->
            graph[i][index] = if (it == 1) 1 else 0
        }
    }
    bfs(0,0)
    println(graph[n-1][m-1])
}

fun bfs(x:Int,y:Int){
    val q = LinkedList<Pair<Int,Int>>()
    q.offer(Pair(x,y))
    visited[x][y] = true

    while (q.isNotEmpty()){
        val now = q.poll()

        for (i in 0 until 4){
            val nx = now.first + dx[i]
            val ny = now.second + dy[i]
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || graph[nx][ny] != 1 || visited[nx][ny]) continue

            graph[nx][ny] = graph[now.first][now.second] + 1
            visited[nx][ny] = true
            q.add(Pair(nx,ny))
        }
    }
}
