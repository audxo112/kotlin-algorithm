// https://www.acmicpc.net/problem/14500
package solution14500__code1

import java.io.StreamTokenizer

private class Node(val x: Int, val y: Int)

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

    var max = 0

    val vBlock = arrayOf(
            arrayOf(Node(0, 0), Node(0, 1), Node(0, 2), Node(1, 2)),
            arrayOf(Node(0, 0), Node(1, 0), Node(1, 1), Node(1, 2)),
            arrayOf(Node(1, 0), Node(1, 1), Node(0, 2), Node(1, 2)),
            arrayOf(Node(0, 0), Node(1, 0), Node(0, 1), Node(0, 2)),
            arrayOf(Node(0, 0), Node(0, 1), Node(1, 1), Node(1, 2)),
            arrayOf(Node(1, 0), Node(0, 1), Node(1, 1), Node(0, 2)),
            arrayOf(Node(0, 0), Node(0, 1), Node(1, 1), Node(0, 2)),
            arrayOf(Node(1, 0), Node(0, 1), Node(1, 1), Node(1, 2)),
    )

    for (y in 0 until n - 2) {
        for (x in 0 until m - 1) {
            for (blocks in vBlock) {
                val cur = blocks.fold(0) { acc, node ->
                    acc + map[y + node.y][x + node.x]
                }
                if (max < cur) {
                    max = cur
                }
            }
        }
    }

    val hBlock = arrayOf(
            arrayOf(Node(0, 0), Node(1, 0), Node(2, 0), Node(2, 1)),
            arrayOf(Node(0, 0), Node(0, 1), Node(1, 1), Node(2, 1)),
            arrayOf(Node(0, 0), Node(1, 0), Node(2, 0), Node(0, 1)),
            arrayOf(Node(0, 1), Node(1, 1), Node(2, 1), Node(2, 0)),
            arrayOf(Node(0, 0), Node(1, 0), Node(1, 1), Node(2, 1)),
            arrayOf(Node(0, 1), Node(1, 1), Node(1, 0), Node(2, 0)),
            arrayOf(Node(1, 0), Node(0, 1), Node(1, 1), Node(2, 1)),
            arrayOf(Node(0, 0), Node(1, 0), Node(2, 0), Node(1, 1)),
    )

    for (y in 0 until n - 1) {
        for (x in 0 until m - 2) {
            for (blocks in hBlock) {
                val cur = blocks.fold(0) { acc, node ->
                    acc + map[y + node.y][x + node.x]
                }
                if (max < cur) {
                    max = cur
                }
            }
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