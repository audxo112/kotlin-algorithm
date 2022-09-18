import java.util.*

private val offset = arrayOf(
    arrayOf(1, 0),
    arrayOf(0, 1),
    arrayOf(-1, 0),
    arrayOf(0, -1)
)

fun main() = with(System.`in`.bufferedReader()) {

    val bw = System.out.bufferedWriter()
    val (n, m) = readLine().split(" ").map { it.toInt() }


    val array = Array(n) { it.toString() }
    val before = Array(n) { Array<Pair<Int, Int>?>(m) { null } }

    for (i in 0 until n) {
        array[i] = readLine()
    }

    for (pairs in before) {
        println(pairs.contentToString())
    }

    bfs(array, before)
    for (pairs in before) {
        println(pairs.contentToString())
    }
    before[0][0] = null

    val count = findMinRouteCount(n, m, before)


    bw.write("${count}")
    bw.flush()
    bw.close()

}

fun findMinRouteCount(n: Int, m: Int, before: Array<Array<Pair<Int, Int>?>>): Any {
    var cur: Pair<Int, Int>? = n - 1 to m - 1
    var count = 0
    while (true) {
        if (cur == null) {
            break
        }
        cur = before[cur.first][cur.second]
        count++
    }
    return count
}
private fun bfs(array: Array<String>, before: Array<Array<Pair<Int, Int>?>>) {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(0 to 0)

    while (queue.isNotEmpty()) {
        val (x, y) = queue.remove()

        for (off in offset) {
            val nx = x + off[0]
            val ny = y + off[1]

            if (nx in array.indices && ny in array[0].indices && array[nx][ny] == '1' && before[nx][ny] == null) {

                queue.add(nx to ny)
                before[nx][ny] = x to y
            }
        }
    }
}
