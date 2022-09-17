//https://www.acmicpc.net/problem/1012
package solution1012


import java.util.*


private class Node(val x:Int, val y:Int)

private val dx = intArrayOf(0, 1, 0, -1)
private val dy = intArrayOf(1, 0, -1, 0)
private fun bfs(m:Int, n:Int, graph: Array<BooleanArray>, sx:Int, sy:Int){
    val queue:Queue<Node> = LinkedList()
    queue.add(Node(sx, sy))
    graph[sy][sx] = false

    while(queue.isNotEmpty()){
        val cur = queue.poll()

        for(dir in 0 .. 3){
            val nx = cur.x + dx[dir]
            val ny = cur.y + dy[dir]

            if(nx < 0 || nx  >= m || ny < 0 || ny >= n || !graph[ny][nx]){
                continue
            }

            graph[ny][nx] = false
            queue.add(Node(nx, ny))
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
    val t = readLine().toInt()
    repeat(t) {
        var tokenizer = StringTokenizer(readLine(), " ")
        val m = tokenizer.nextToken().toInt()
        val n = tokenizer.nextToken().toInt()
        val k = tokenizer.nextToken().toInt()
        val graph = Array(n) {
            BooleanArray(m)
        }

        repeat(k) {
            tokenizer = StringTokenizer(readLine(), " ")
            val x = tokenizer.nextToken().toInt()
            val y = tokenizer.nextToken().toInt()
            graph[y][x] = true
        }
        println(solution(m, n, graph))
    }
}