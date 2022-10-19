import java.util.*

private val dx = listOf(0,0,1,-1)
private val dy = listOf(1,-1,0,0)

fun main() {
    val t = readln().toInt()

    for (q in 0 until t) {
        val (m, n, k) = readln().split(" ").map { it.toInt() }
        val graph = Array(n) { IntArray(m) { 0 } }
        for (i in 0 until k) {
            val (x, y) = readln().split(" ").map { it.toInt() }
            graph[y][x] = 1
        }
        var result = 0
        for (i in 0 until n){
            for (j in 0 until m){
                if (graph[i][j] == 1) {
                    val q = LinkedList<Pair<Int, Int>>()
                    q.add(i to j)
                    while (q.isNotEmpty()) {
                        val (x, y) = q.poll()

                        for (k in 0 until 4) {
                            val nx = x + dx[k]
                            val ny = y + dy[k]

                            if (nx in 0 until n && ny in 0 until m && graph[nx][ny] == 1) {
                                q.add(nx to ny)
                                graph[nx][ny] = 0
                            }
                        }
                    }
                    result += 1
                }
            }
        }
        println(result)
    }
}