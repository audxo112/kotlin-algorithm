// https://www.acmicpc.net/problem/3190

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

private val dx = arrayOf(0, 1, 0, -1)
private val dy = arrayOf(1, 0, -1, 0)
private var dir = 100

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val k = br.readLine().toInt()
    val arr = Array(n) { IntArray(n) }
    repeat(k) {
        val st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt() - 1
        val y = st.nextToken().toInt() - 1
        arr[x][y] = 1
    }

    val l = br.readLine().toInt()
    val directions: Queue<Dir> = LinkedList()
    repeat(l) {
        val st = StringTokenizer(br.readLine())
        val time = st.nextToken().toInt()
        val d = if (st.nextToken()[0] == 'L') -1 else 1
        directions.add(Dir(time, d))
    }

    var count = 0
    val snake = LinkedList<Node11>()
    snake.addFirst(Node11(0, 0))
    arr[0][0] = -1

    while (true) {
        count++

        val (x, y) = snake.peek()
        val xx = x + dx[dir % 4]
        val yy = y + dy[dir % 4]

        if (xx !in 0 until n || yy !in 0 until n || arr[xx][yy] == -1) break

        val node = Node11(xx, yy)
        snake.addFirst(node)

        if (arr[xx][yy] != 1) {
            val tail = snake.pollLast()
            arr[tail.x][tail.y] = 0
        }

        arr[xx][yy] = -1

        if (directions.peek()?.time == count) {
            val d = directions.poll().d
            dir += d
        }
    }

    println(count)
}

private data class Dir(val time: Int, val d: Int)

private data class Node11(val x: Int, val y: Int)