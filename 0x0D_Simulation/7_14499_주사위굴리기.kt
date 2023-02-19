// https://www.acmicpc.net/problem/14499

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

private val dx = arrayOf(0, 0, -1, 1)
private val dy = arrayOf(1, -1, 0, 0)
private lateinit var arr: Array<IntArray>
private lateinit var dirs: IntArray
private lateinit var diceX: MutableList<Int>
private lateinit var diceY: MutableList<Int>
private lateinit var sb: StringBuilder

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val x = st.nextToken().toInt()
    val y = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    arr = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(m) {
            st.nextToken().toInt()
        }
    }
    st = StringTokenizer(br.readLine())
    dirs = IntArray(k) {
        st.nextToken().toInt()
    }
    diceX = mutableListOf(0, 0, 0) // 전개도 가로
    diceY = mutableListOf(0, 0, 0, 0) // 전개도 세로
    sb = StringBuilder()

    bfs(Node7(x, y))

    println(sb.toString())
}

private fun bfs(first: Node7) {
    val que: Queue<Node7> = LinkedList()
    que.add(first)
    sb = StringBuilder()

    for (dir in dirs) {
        val node = que.poll()
        val xx = node.x + dx[dir - 1]
        val yy = node.y + dy[dir - 1]

        if (xx !in arr.indices || yy !in arr[0].indices) {
            que.add(node)
            continue
        }

        // 주사위 돌리기
        rollDice(dir)

        if (arr[xx][yy] == 0) {
            // 주사위 -> 칸
            arr[xx][yy] = diceY[3]
        } else {
            // 칸 -> 주사위
            diceY[3] = arr[xx][yy]
            arr[xx][yy] = 0
        }

        sb.appendLine(diceX[1])
        que.add(Node7(xx, yy))
    }
}

private fun rollDice(dir: Int) {
    when(dir) {
        1 -> {
            // 동
            val last = diceX.removeLast()
            diceX.add(0, diceY.last())
            diceY[1] = diceX[1]
            diceY[3] = last
        }
        2 -> {
            // 서
            val first = diceX.removeFirst()
            diceX.add(diceY.last())
            diceY[1] = diceX[1]
            diceY[3] = first
        }
        3 -> {
            // 북
            val first = diceY.removeFirst()
            diceY.add(first)
            diceX[1] = diceY[1] // 가로, 세로 겹치는(주사위 1) 부분 반영
        }
        4 -> {
            // 남
            val last = diceY.removeLast()
            diceY.add(0, last)
            diceX[1] = diceY[1]
        }
    }
}

data class Node7(val x: Int, val y: Int)