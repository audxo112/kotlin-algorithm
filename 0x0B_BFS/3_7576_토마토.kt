import java.util.LinkedList
import java.util.Queue

/**
 * 풀이 과정
 * 1. 박스에 안익은 토마토가 있는지 확인
 *  1-1. 안익은 토마토가 없다면 -> 0을 출력하고 끝냄
 *  1-2. 있다면 -> 2번
 * 2. bfs 하면서 그래프에 숫자 채우기 (현재 + 1)
 * 3. 그래프에서 가장 큰 숫자 반환 (마지막으로 그래프에 채워진 숫자)
 */

private val offset = arrayOf(
    arrayOf(1, 0),
    arrayOf(0, 1),
    arrayOf(-1, 0),
    arrayOf(0, -1)
)

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val (m, n) = readLine().split(" ").map { it.toInt() }

    val graph = Array(n) { IntArray(m) }

    for (i in graph.indices) {
        graph[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    var round = 0
    var possibleRoute = true

    val queue:Queue<Pair<Int, Int>> = LinkedList()


    for (i in graph.indices) {
        for (j in graph[i].indices) {
            if (graph[i][j] == 1) {
                queue.add(i to j)
            }
        }
    }

    if (existUnripeTomato(graph)) {
        var max = 0
        while (queue.isNotEmpty()) {
            val (x, y) = queue.remove()

            for (off in offset) {
                val nx = x + off[0]
                val ny = y + off[1]
                if (nx in graph.indices && ny in graph[0].indices && graph[nx][ny] == 0) {
                    graph[nx][ny] = graph[x][y] + 1
                    queue.add(nx to ny)
                }
            }

            max = graph[x][y]
        }

        when (existUnripeTomato(graph)) {
            true -> bw.write("-1")
            false -> bw.write("${max - 1}")
        }
    } else {
        bw.write("0")
    }
    bw.flush()
}

fun findMaxValue(graph: Array<IntArray>):Int {
    var max = 0
    for (row in graph) {
        for (e in row) {
            if (e > max) {
                max = e
            }
        }
    }
    return max
}

fun existUnripeTomato(graph: Array<IntArray>): Boolean {
    for (row in graph) {
        for (e in row) {
            if (e == 0) {
                return true
            }
        }
    }
    return false
}

