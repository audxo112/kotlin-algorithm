//https://www.acmicpc.net/problem/7562
package solution7562

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.Queue

private val dx = intArrayOf(1, 2, 2, 1, -1, -2, -2, -1)
private val dy = intArrayOf(2, 1, -1, -2, -2, -1, 1, 2)

private class Point(val x: Int, val y: Int)

private fun bfs(l: Int, sx: Int, sy: Int, ex: Int, ey: Int): Int{
    // 불필요한 이동범위를 줄이기 위해 범위 지정
    val SX = (ex - 7).coerceAtLeast(0)
    val EX = (ex + 7).coerceAtMost(l)
    val SY = (ey - 7).coerceAtLeast(0)
    val EY = (ey + 7).coerceAtMost(l)
    val visited = Array(l){ IntArray(l) }

    val queue: Queue<Point> = LinkedList()
    queue.add(Point(sx, sy))

    while(queue.isNotEmpty()){
        val cur = queue.poll()
        if(cur.x == ex && cur.y == ey){
            return visited[cur.y][cur.x]
        }

        for(i in 0 until 8){
            val nx = cur.x + dx[i]
            val ny = cur.y + dy[i]

            if(nx !in SX until EX || ny !in SY until EY || visited[ny][nx] > 0){
                continue
            }

            visited[ny][nx] = visited[cur.y][cur.x] + 1
            queue.add(Point(nx, ny))
        }
    }
    return 0
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {

    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    repeat(input()){
        val l = input()
        var sx = input()
        var sy = input()
        var ex = input()
        var ey = input()
        var len = 0
        // ex, ey 값이 무조건 크게 뒤집기
        if(sx > ex){
            sx = l - 1 - sx
            ex = l - 1 - ex
        }
        if(sy > ey){
            sy = l - 1 - sy
            ey = l - 1 - ey
        }
        var dx = ex - sx
        var dy = ey - sy
        // dx, dy 가 둘다 5보다 작을 때지 이동하면서 가지치기
        while(dx > 5 || dy > 5){
            if(dx > dy){
                sx += 2
                if(ey > sy || sy - 1 < 0){
                    sy += 1
                } else {
                    sy -= 1
                }
            } else{
                if(ex > sx || sx - 1 < 0){
                    sx += 1
                } else{
                    sx -= 1
                }
                sy += 2
            }
            dx = ex - sx
            dy = ey - sy
            len += 1
        }
        println(len + bfs(l, sx, sy, ex, ey))
    }
}
