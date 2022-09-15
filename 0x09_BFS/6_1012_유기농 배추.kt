//https://www.acmicpc.net/problem/1012
package solution1012


import java.util.*


private data class Node(val x:Int, val y:Int)


private val dirs = listOf(
    Node(1, 0), Node(0, -1), Node(-1, 0), Node(0, 1),
)

private fun bfs(m:Int, n:Int, graph: Array<BooleanArray>, sx:Int, sy:Int){
    val queue:Queue<Node> = LinkedList()
    queue.add(Node(sx, sy))
    graph[sy][sx] = false

    while(queue.isNotEmpty()){
        val (x, y) = queue.poll()

        for((dx, dy) in dirs){
            val nx = x + dx
            val ny = y + dy

            if(nx in 0 until m && ny in 0 until n && graph[ny][nx]){
                graph[ny][nx] = false
                queue.add(Node(nx, ny))
            }
        }
    }
}


private fun solution(m: Int, n: Int, graph: Array<BooleanArray>): Int {
    var count = 0
    for (y in 0 until n) {
        for (x in 0 until m) {
            if(graph[y][x]){
                bfs(m, n, graph, x, y)
                count += 1
            }
        }
    }
    return count
}


private fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val t = readLine().toInt()
    repeat(t) {
        var tokenizer = StringTokenizer(readLine())
        val m = tokenizer.nextToken().toInt()
        val n = tokenizer.nextToken().toInt()
        val k = tokenizer.nextToken().toInt()
        val graph = Array(n) {
            BooleanArray(m) { false }
        }

        repeat(k) {
            tokenizer = StringTokenizer(readLine())
            val x = tokenizer.nextToken().toInt()
            val y = tokenizer.nextToken().toInt()
            graph[y][x] = true
        }
        bw.write("${solution(m, n, graph)}\n")
    }
    bw.flush()
}