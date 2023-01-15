//https://www.acmicpc.net/problem/2583
package solution2583__code1

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

private class Node(val x: Int, val y: Int)

private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, -1, 0, 1)

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val m = input()
    val n = input()
    val k = input()

    val map = Array(m) { IntArray(n) }

    repeat(k) {
        // 꼭지점 4개에서 1, -1 값을 저장
        val sx = input()
        val sy = input()
        val ex = input()
        val ey = input()

        map[sy][sx] += 1
        if(ey < m){
            map[ey][sx] -= 1
        }
        if(ex < n){
            map[sy][ex] -= 1
        }
        if(ex < n && ey < m){
            map[ey][ex] += 1
        }
    }

    // 저장된 값을 이용하여 누산으로 영역을 그림
    for (y in 0 until m) for (x in 1 until n) {
        map[y][x] += map[y][x - 1]
    }

    for (x in 0 until n) for (y in 1 until m) {
        map[y][x] += map[y - 1][x]
    }

    fun bfs(sx: Int, sy: Int): Int{
        val queue: Queue<Node> = LinkedList()
        queue.add(Node(sx, sy))
        map[sy][sx] = 1

        var count = 1
        while(queue.isNotEmpty()){
            val cur = queue.poll()
            for(i in 0 until 4){
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]

                if(nx !in 0 until n || ny !in 0 until m || map[ny][nx] != 0){
                    continue
                }

                count += 1
                map[ny][nx] = 1
                queue.add(Node(nx, ny))
            }
        }
        return count
    }

    var count = 0
    val area = PriorityQueue<Int>()
    for (y in 0 until m) for (x in 0 until n) {
        if(map[y][x] == 0){
            count += 1
            // 항상 최소값을 넣기 위해 우선순위 큐에 영역의 개수를 저장
            area.add(bfs(x, y))
        }
    }
    val sb = StringBuilder()
    while(area.isNotEmpty()){
        // 숫자와 " " 을 따로 append 하여 String Intern 영역에 추가되는 String 을 최소화
        // 메모리 사용량 13856KB -> 13560KB
        // 시간 112ms -> 108ms
        sb.append(area.poll()).append(" ")
    }
    println(count)
    println(sb.toString())
}