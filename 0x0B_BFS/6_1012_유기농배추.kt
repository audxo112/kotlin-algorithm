import java.util.LinkedList
import java.util.Queue
/**
 * 풀이과정
 *
 * 1. bfs로 전체 탐색 -> 횟수 체크
 */
private val offset = arrayOf(
    arrayOf(1, 0),
    arrayOf(0, 1),
    arrayOf(-1, 0),
    arrayOf(0, -1)
)

fun main() = with(System.`in`.bufferedReader()){
    val bw = System.out.bufferedWriter()
    val t = readLine().toInt()


    for (i in 0 until t) {
        val (m, n, k) = readLine().split(" ").map { it.toInt() }
        val graph = Array(n) { IntArray(m) }
        val visited = Array(n) { BooleanArray(m) }
        for (j in 0 until k) {
            val (y, x) = readLine().split(" ").map { it.toInt() }
            graph[x][y] = 1
        }

        val result = bfs(graph, visited)
        bw.write("$result\n")
    }
    bw.flush()
    bw.close()
}

private fun bfs(graph: Array<IntArray>, visited: Array<BooleanArray>): Int {
    var result = 0
    for (i in graph.indices) {
        for (j in graph[0].indices) {
            if (graph[i][j] == 1 && !visited[i][j]) {
                bfs(i, j, graph, visited)
                result++
            }
        }
    }

    return result
}

private fun bfs(i: Int, j: Int, graph: Array<IntArray>, visited: Array<BooleanArray>) {

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(i to j)

    while (queue.isNotEmpty()) {
        val (x, y) = queue.remove()

        if (visited[x][y]) {
            continue
        }
        visited[x][y] = true

        for (off in offset) {
            val nx = x + off[0]
            val ny = y + off[1]

            if (nx in graph.indices && ny in graph[0].indices && graph[nx][ny] == 1 && !visited[nx][ny]) {
                queue.add(nx to ny)
            }
        }
    }

}