// https://www.acmicpc.net/problem/14500
package solution14500__code2

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    val map = Array(n) {
        IntArray(m) {
            input()
        }
    }

    // 기존의 연산수가 4 X 8 + 8 = 40
    // 3 + 3
    // 3 + 3
    // 2 + 2 + 2 + 2
    // 4 => 24회
    var max = 0
    for (y in 0 until n - 2) {
        for (x in 0 until m - 1) {
            max = maxOf(max,
                    map[y][x] + map[y + 1][x] + map[y + 2][x] + maxOf(map[y][x + 1], map[y + 1][x + 1], map[y + 2][x + 1]),
                    map[y][x + 1] + map[y + 1][x + 1] + map[y + 2][x + 1] + maxOf(map[y][x], map[y + 1][x], map[y + 2][x]),
                    map[y + 1][x] + map[y + 1][x + 1] + maxOf(map[y][x] + map[y + 2][x + 1], map[y][x + 1] + map[y + 2][x])
            )
        }
    }
    for (y in 0 until n - 1) {
        for (x in 0 until m - 2) {
            max = maxOf(max,
                    map[y][x] + map[y][x + 1] + map[y][x + 2] + maxOf(map[y + 1][x], map[y + 1][x + 1], map[y + 1][x + 2]),
                    map[y + 1][x] + map[y + 1][x + 1] + map[y + 1][x + 2] + maxOf(map[y][x], map[y][x + 1], map[y][x + 2]),
                    map[y][x + 1] + map[y + 1][x + 1] + maxOf(map[y][x] + map[y + 1][x + 2], map[y + 1][x] + map[y][x + 2])
            )
        }
    }

    for (y in 0 until n - 1) {
        for (x in 0 until m - 1) {
            val cur = map[y][x] + map[y][x + 1] + map[y + 1][x] + map[y + 1][x + 1]
            if (max < cur) {
                max = cur
            }
        }
    }

    for (y in 0 until n - 3) {
        for (x in 0 until m) {
            val cur = map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 3][x]
            if (max < cur) {
                max = cur
            }
        }
    }

    for (y in 0 until n) {
        for (x in 0 until m - 3) {
            val cur = map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y][x + 3]
            if (max < cur) {
                max = cur
            }
        }
    }

    println(max)
}