// https://www.acmicpc.net/problem/3190
package solution3190

import java.io.StreamTokenizer
import java.util.*

private const val TOP = 0
private const val RIGHT = 1
private const val BOTTOM = 2
private const val LEFT = 3

private class Node(val x: Int, val y: Int)

// 뱀을 회전하여 어디방향으로 이동할지 예측 및 사라질 꼬리의 위치를 반환
private class Snake {
    var x: Int = 1
    var y: Int = 1
    var dir: Int = RIGHT
    private val tail: Queue<Node> = LinkedList()

    fun rotate(rDir: String) {
        dir = when (rDir) {
            "D" -> (dir + 1) % 4
            "L" -> (dir + 3) % 4
            else -> dir
        }
    }

    fun nextX() = when(dir){
        LEFT -> x - 1
        RIGHT -> x + 1
        else -> x
    }

    fun nextY() = when(dir){
        TOP -> y - 1
        BOTTOM -> y + 1
        else -> y
    }

    fun move(nx: Int, ny: Int, eat: Boolean): Node?{
        tail.add(Node(x, y))

        x = nx
        y = ny

        return if(eat) null else tail.poll()
    }
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nInput(): Int {
        nextToken()
        return nval.toInt()
    }

    fun sInput(): String {
        nextToken()
        return sval
    }

    val n = nInput()
    val map = Array(n + 2){
        IntArray(n + 2)
    }
    // 가장자리 4개는 도착할일 이없으므로 1로 바꾸지 않아도된다
    for(i in 1 until n + 1){
        map[0][i] = 1
        map[n + 1][i] = 1
        map[i][0] = 1
        map[i][n + 1] = 1
    }
    repeat(nInput()){
        map[nInput()][nInput()] = 2
    }
    map[1][1] = 1

    val l = nInput()
    var runOrder = 0
    var x = nInput()
    var c = sInput()

    val snake = Snake()
    var curTime = 0
    while(true){
        curTime += 1

        val nx = snake.nextX()
        val ny = snake.nextY()

        // 몸이든 벽이든 1
        if(map[ny][nx] == 1){
            break
        }
        // 사과를 먹었다면 null 을 반환해서 0으로 변경하지 않음
        val node = snake.move(nx, ny, map[ny][nx] == 2)
        if(node != null){
            map[node.y][node.x] = 0
        }
        map[ny][nx] = 1

        // 명령을 못받고 끝날 수 도 있기 때문에 필요할때마다 명령을 받는다
        if(curTime == x){
            snake.rotate(c)
            runOrder += 1
            if(runOrder < l){
                x = nInput()
                c = sInput()
            }
        }
    }
    println(curTime)
}