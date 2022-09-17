import java.util.*

/**
 * 풀이과정 - 메모리 초과
 * 1. 지훈이 위치 기록
 * 2. F에서 번질 수 있는 공간으로 번지고 번진 위치만 큐에 넣기
 *  2-1. 원래 불씨는 다시 고려할 필요가 없음
 * 3. 지훈이 위치에서 갈 수 있는 방향으로 옮기기
 *  3-1. 모든 경우의 수 고려 -> 어떻게 해야하지?
 *  3-2. 원래 지훈이의 위치가 F가 아니라면 (불이 번지지 않았다면) .으로 바꿔두기
 * 4. 지훈이가 가장자리 갈 때까지 진행
 *  4-1. 가장자리가 아닌데 진행할 수 없다면 -1반환
 *  4-2. 가장자리면 count 반환
 * 5. 리턴값 출력
 */

/**
 * 풀이과정 2
 * 1. 불이 이동하는 시간 - fTime[][] 만들기
 *  1-1. 입력 그래프에서 #이나 F면 -1로 채우기
 * 2. F기준으로 BFS
 *  2-1. 시간 + 1 해가면서 ftime 채우기
 * 3. J기준으로 BFS
 *  3-1. 지훈의 현재 시간보다 ftime이 더 크면 이동 가능
 *  3-2. -1위치는 이동 불가능
 */
private val offset = arrayOf(
    arrayOf(1, 0),
    arrayOf(0, 1),
    arrayOf(-1, 0),
    arrayOf(0, -1)
)
fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val (r, c) = readLine().split(" ").map { it.toInt()}

    val maze = Array(r) { CharArray(c) }
    val fTime = Array(r) { IntArray(c) }

    var jihun = 0 to 0

    for (i in 0 until r) {
        val row = readLine()
        for (j in 0 until c) {
            maze[i][j] = row[j]
            when (row[j]) {
                'J' -> jihun = i to j
                '#','F' -> fTime[i][j] = -1
            }
        }
    }
    fBfs(maze, fTime)

    when(val result = jBfs(maze, fTime, jihun)) {
        -1 -> bw.write("IMPOSSIBLE")
        else -> bw.write("$result")
    }

    bw.flush()
    bw.close()
}
private fun fBfs(maze: Array<CharArray>, fTime: Array<IntArray>) {
    val q: Queue<Pair<Int, Int>> = LinkedList()
    for (i in maze.indices) {
        for (j in maze[i].indices) {
            if (maze[i][j] == 'F') {
                q.add(i to j)
            }
        }
    }
    var time = 0
    while (q.isNotEmpty()) {
        val size = q.size
        for (i in 0 until size) {
            val (x, y) = q.remove()
            for (off in offset) {
                val nx = x + off[0]
                val ny = y + off[1]
                if (nx in maze.indices && ny in maze[0].indices && maze[nx][ny] != '#' && maze[nx][ny] != 'F') {
                    maze[nx][ny] = 'F'
                    fTime[nx][ny] = time + 1
                    q.add(nx to ny)
                }
            }
        }
        time++
    }

}
private fun jBfs(maze: Array<CharArray>, fTime: Array<IntArray>, jihun: Pair<Int, Int>): Int{
    val q: Queue<Pair<Int, Int>> = LinkedList()
    q.add(jihun)
    val visited = Array(maze.size) { BooleanArray(maze[0].size)}

    var time = 1
    while (q.isNotEmpty()) {
        val fSize = q.size
        visited[jihun.first][jihun.second] = true
        for (i in 0 until fSize) {
            val (x, y) = q.remove()
            if (x == -1 || x == maze.size - 1 || y == 0 || y == maze[0].size - 1) {
                return time
            }

            for (off in offset) {
                val nx = x + off[0]
                val ny = y + off[1]
                if (nx in maze.indices && ny in maze[0].indices && (fTime[nx][ny] == 0 || fTime[nx][ny] > time) && !visited[nx][ny]) {
                    q.add(nx to ny)
                    visited[nx][ny] = true
                }
            }
        }
        time++
    }
    return -1
}
