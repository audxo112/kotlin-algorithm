// https://www.acmicpc.net/problem/14891

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private const val S = '1'
private lateinit var gears: Array<MutableList<Char>>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    gears = Array(4) {
        br.readLine().toMutableList()
    }

    repeat(br.readLine().toInt()) {
        val st = StringTokenizer(br.readLine())
        val idx = st.nextToken().toInt() - 1
        val direction = st.nextToken().toInt()

        val leftPole = gears[idx][6]    // 9시 방향 극
        val rightPole = gears[idx][2]   // 3시 방향 극

        // 반시계
        if (direction == -1) {
            val first = gears[idx].removeFirst()
            gears[idx].add(first)
        } else {
            val last = gears[idx].removeLast()
            gears[idx].add(0, last)
        }

        rotateLeftGears(idx - 1, direction, leftPole)
        rotateRightGears(idx + 1, direction, rightPole)
    }

    var two = 1
    var sum = 0
    for (i in gears.indices) {
        if (gears[i][0] == S) {
            sum += two
        }
        two = two.shl(1)
    }
    println(sum)
}

private fun rotateLeftGears(idx: Int, nextDir: Int, nextPole: Char) {
    if (idx == -1 || nextPole == gears[idx][2]) {
        return
    }
    // 9시 방향 극 넘김
    val pole = gears[idx][6]

    // 이전 방향이 반시계 방향이면 시계 방향으로
    if (nextDir == -1) {
        val last = gears[idx].removeLast()
        gears[idx].add(0, last)
        rotateLeftGears(idx - 1, 1, pole)
    } else {
        val first = gears[idx].removeFirst()
        gears[idx].add(first)
        rotateLeftGears(idx - 1, -1, pole)
    }
}

private fun rotateRightGears(idx: Int, prevDir: Int, prevPole: Char) {
    if (idx == gears.size || prevPole == gears[idx][6]) {
        return
    }
    // 세시 방향 극 넘김
    val pole = gears[idx][2]

    // 이전 방향이 반시계 방향이면 시계 방향으로
    if (prevDir == -1) {
        val last = gears[idx].removeLast()
        gears[idx].add(0, last)
        rotateRightGears(idx + 1, 1, pole)
    } else {
        val first = gears[idx].removeFirst()
        gears[idx].add(first)
        rotateRightGears(idx + 1, -1, pole)
    }
}