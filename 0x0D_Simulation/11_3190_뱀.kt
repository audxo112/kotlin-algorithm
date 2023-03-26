package solve_3190

import java.io.*
import java.util.*

private data class Node(val x: Int, val y: Int)
private data class Dir(val dx: Int, val dy: Int)
private data class Snake(
    var head: Node,
    var body: Queue<Node>,
    var dirIdx: Int,
) {

    fun rotate(dirString: String) {
        dirIdx = when (dirString) {
            "L" -> {
                (dirIdx + 3) % 4
            }
            "D" -> {
                (dirIdx + 1) % 4
            }
            else -> dirIdx
        }
    }
}

private const val TOP = 0
private const val RIGHT = 1
private const val BOTTOM = 2
private const val LEFT = 3

private val dirList = arrayOf(
    Dir(0, -1),
    Dir(1, 0),
    Dir(0, 1),
    Dir(-1, 0)
)

private lateinit var map: Array<IntArray>

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun intInput(): Int {
        nextToken()
        return nval.toInt()
    }

    fun stringInput(): String {
        nextToken()
        return sval
    }

    val n = intInput()
    val k = intInput()

    map = Array(n+2) {
        IntArray(n+2)
    }

    repeat(k) {
        val c = intInput()
        val r = intInput()
        map[c][r] = 1
    }

    val l = intInput()

    val snake = Snake(Node(1, 1), LinkedList(), RIGHT)
    map[1][1] = 2
    var time = 0
    var gameEndFlag = false


    var inputCnt = 0
    while (gameEndFlag.not()) {
        var x = Int.MAX_VALUE
        var c = "S"
        if(inputCnt < l) {
            x = intInput()
            c = stringInput()
            inputCnt+=1
        }
        while(time != x) {
            time +=1
            gameEndFlag = move(snake, "S")
            if(gameEndFlag) break
        }

        if(gameEndFlag) break
        gameEndFlag = move(snake, c)
        time += 1
    }

    println(time)

}


private fun move(snake: Snake, c: String): Boolean {
    snake.rotate(c)
    val (head, body, dirIdx) = snake
    val newHead = Node(head.x + dirList[dirIdx].dx, head.y + dirList[dirIdx].dy)

    if (newHead.x < 1 || newHead.x > map[0].lastIndex - 1
        || newHead.y < 1 || newHead.y > map.lastIndex - 1
        || map[newHead.y][newHead.x] == 2
    ) return true

    body.add(snake.head)
    snake.head = newHead
    if (map[newHead.y][newHead.x] == 1) {
        map[newHead.y][newHead.x] = 2
    } else {
        val (x, y) = body.poll()
        map[y][x] = 0
        map[newHead.y][newHead.x] = 2
    }
    return false
}