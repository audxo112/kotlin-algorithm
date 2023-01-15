//https://www.acmicpc.net/problem/5427
package solution5427

import java.util.Deque
import java.util.LinkedList
import java.util.StringTokenizer

private class Node(val x: Int, val y: Int, val person: Boolean)

private val dx = intArrayOf(1, 0 , -1, 0)
private val dy = intArrayOf(0, -1 , 0, 1)

private fun solution(w: Int, h: Int, map: Array<IntArray>, queue: Deque<Node>){
    var date = 0
    while(queue.isNotEmpty()){
        date += 1
        repeat(queue.size){
            val cur = queue.poll()
            if(cur.person && (cur.x == 0 || cur.x == w - 1 || cur.y == 0 || cur.y == h - 1)){
                println(date)
                return
            }

            for(i in 0 until 4){
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]

                if(nx !in 0 until w || ny !in 0 until h){
                    continue
                }

                if(cur.person){
                    if(map[ny][nx] != 0){
                        continue
                    }
                    // 상근이가 지나간 자리를 불은 지나갈 수 있으므로 1로 처리해준다
                    map[ny][nx] = 1
                } else{
                    if(map[ny][nx] == -1){
                        continue
                    }
                    map[ny][nx] = -1
                }

                queue.add(Node(nx, ny, cur.person))
            }
        }
    }
    println("IMPOSSIBLE")
}

private fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        val tokenizer = StringTokenizer(readLine(), " ")
        val w = tokenizer.nextToken().toInt()
        val h = tokenizer.nextToken().toInt()
        val queue: Deque<Node> = LinkedList()

        // 불이 붙은곳에 상근이가 이동할 수 없으므로 불 -> 상근
        // 불을 먼저 옮기고 상근이를 옮겨야 하기 때문에 Deque를 이용해 추가 위치를 정한다
        val map = Array(h) {y ->
            val line = readLine()
            IntArray(w) { x ->
                // 벽이나 불이 지나간 자리는 그 누구도 지나갈 수 없어서 -1
                // 땅이나 상근이 자리는 누구나 갈 수 있으니 0
                when(line[x]){
                    '#' -> -1
                    '*' -> {
                        queue.addFirst(Node(x, y, false))
                        -1
                    }
                    '.' -> 0
                    '@' -> {
                        queue.addLast(Node(x, y, true))
                        0
                    }
                    else -> -1
                }
            }
        }
        solution(w, h, map, queue)
    }
}