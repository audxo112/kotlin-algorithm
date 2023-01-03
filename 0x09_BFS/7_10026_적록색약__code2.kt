//https://www.acmicpc.net/problem/10026
package solution10026__code2

fun main() = System.`in`.bufferedReader().run {
    val n = readLine().toInt()
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, -1, 0, 1)

    val graph = List(n) {
        readLine().toCharArray()
    }
    val nVisited = List(n) { BooleanArray(n) }
    val sVisited = List(n) { BooleanArray(n) }

    fun List<BooleanArray>.dfs(x: Int, y: Int, flag: Boolean){
        val color = graph[y][x]
        this[y][x] = true
        for(i in 0 until 4){
            val nx = x + dx[i]
            val ny = y + dy[i]

            if(nx !in 0 until n || ny !in 0 until n || this[ny][nx]){
                continue
            }

            if (color == graph[ny][nx] || (flag && ((color == 'R' && graph[ny][nx] == 'G') || (color == 'G' && graph[ny][nx] == 'R')))) {
                dfs(nx, ny, flag)
            }
        }
    }

    var nCount = 0
    var sCount = 0

    for (y in 0 until n) for (x in 0 until n) {
        if (!nVisited[y][x]) {
            nCount += 1
            nVisited.dfs(x, y, false)
        }

        if (!sVisited[y][x]) {
            sCount += 1
            sVisited.dfs(x, y, true)
        }
    }
    println("$nCount $sCount")
}