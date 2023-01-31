package solve_14499

import java.io.StreamTokenizer

const val TOP = 0
const val BOTTOM = 1
const val LEFT = 2
const val RIGHT = 3
const val FRONT = 4
const val BACK = 5

private val changePlanes = arrayOf(
    intArrayOf(),
    intArrayOf(TOP, LEFT, BOTTOM, RIGHT),//동쪽으로 구르기, 전 후 제외 시계방향
    intArrayOf(TOP, RIGHT, BOTTOM, LEFT),//서쪽으로 구르기, 전 후 제외 반시계방향
    intArrayOf(TOP, FRONT, BOTTOM, BACK),//북쪽으로 구르기, 좌 우 제외 시계방향
    intArrayOf(TOP, BACK, BOTTOM, FRONT),//남쪽으로 구르기, 좌 우 제외 반시계방향
)

private class Position(var y: Int, var x: Int) {
    fun move(dir: Int): Boolean {
        var nx = x
        var ny = y
        when (dir) {
            1 -> nx += 1
            2 -> nx -= 1
            3 -> ny -= 1
            4 -> ny += 1
        }

        return if (nx in 0..map[0].lastIndex && ny in 0..map.lastIndex) {
            x = nx
            y = ny
            true
        } else false
    }
}

private lateinit var map: Array<IntArray>

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val dice = IntArray(6)
    val now = Position(input(), input())
    val k = input()

    map = Array(n) {
        IntArray(m) {
            input()
        }
    }

    repeat(k) {
        val dir = input()
        if (now.move(dir)) {
            dice.roll(dir)
            if (map[now.y][now.x] == 0) {
                map[now.y][now.x] = dice[BOTTOM]
            } else {
                dice[BOTTOM] = map[now.y][now.x]
                map[now.y][now.x] = 0
            }
            println(dice[TOP])
        }
    }

}

fun IntArray.roll(dir: Int) {
    val temp = this[changePlanes[dir][0]]
    for (i in 0 until changePlanes[dir].lastIndex) {
        this[changePlanes[dir][i]] = this[changePlanes[dir][i + 1]]
    }
    this[changePlanes[dir].last()] = temp
}
