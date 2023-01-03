//https://www.acmicpc.net/problem/10026
package solution10026__code3

fun main() = System.`in`.bufferedReader().run {
    val n = readLine().toInt()
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, -1, 0, 1)

    // 색약의 경우 배열을 만들때 애초에 R,G 를 R로 치환해서 시작
    val sGraph = Array(n){ CharArray(n) }
    val nGraph = Array(n){y ->
        readLine().toCharArray().also{ arr ->
            for(x in 0 until n){
                sGraph[y][x] = if(arr[x] == 'B') 'B' else 'R'
            }
        }
    }

    // 배열을 기준으로 dfs
    fun Array<CharArray>.dfs(x: Int, y: Int){
        val color = this[y][x]
        this[y][x] = ' '
        for(i in 0 until 4){
            val nx = x + dx[i]
            val ny = y + dy[i]

            if(nx !in 0 until n || ny !in 0 until n || this[ny][nx] == ' '){
                continue
            }

            if (color == this[ny][nx]) {
                dfs(nx, ny)
            }
        }
    }

    var nCount = 0
    var sCount = 0

    for (y in 0 until n) for (x in 0 until n) {
        if (nGraph[y][x] != ' ') {
            nCount += 1
            nGraph.dfs(x, y)
        }

        if (sGraph[y][x] != ' ') {
            sCount += 1
            sGraph.dfs(x, y)
        }
    }
    println("$nCount $sCount")
}