package solve_14503

import java.io.StreamTokenizer

private data class Node(var x: Int, var y: Int, var dirIdx: Int)
private data class Dir(val dx: Int, val dy: Int)


private val dirList = arrayOf(
    Dir(-1,0), //북
    Dir(0,1), // 동
    Dir(1,0), //남
    Dir(0,-1), //서
)

private lateinit var room: Array<IntArray>
private var answer = 0

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    val start = Node(input(), input(), input())
    room = Array(n) {
        IntArray(m) {
            input()
        }
    }
    checkRoom(start)
    println(answer)
}

private fun checkRoom(now: Node) {
    while(true) {
        var (x,y,d) = now
        if(room[x][y] == 0){
            cleanRoom(x,y)
        }

        var find = false

        for(i in dirList.indices) {
            d = rotate(d)
            val nx = dirList[d].dx + x
            val ny = dirList[d].dy + y

            if(room[nx][ny] != 0) continue

            find = true
            now.x = nx
            now.y = ny
            now.dirIdx = d
            break
        }

        if(find.not()) {
            now.x = x - dirList[now.dirIdx].dx
            now.y = y - dirList[now.dirIdx].dy

            if(room[now.x][now.y] == 1) break
        }
    }
}

private fun cleanRoom(x: Int, y: Int) {
    room[x][y] = 2
    answer += 1
}


private fun rotate(dirIdx: Int) : Int {
    var nowDirIdx = dirIdx - 1
    if(nowDirIdx < 0) {
        nowDirIdx = 3
    }
    return nowDirIdx
}