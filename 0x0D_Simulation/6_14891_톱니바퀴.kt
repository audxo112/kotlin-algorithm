package solve_14891

import java.util.LinkedList
import java.util.Queue

private const val CLOCK = 1
private const val COUNTER_CLOCK = -1

private lateinit var gears: Array<CharArray>

private class GearRotate(val gearNum: Int, val dir: Int)

private var rotateQueue: Queue<GearRotate> = LinkedList()
private val scores = arrayOf(0, 1, 2, 4, 8)

fun main() = with(System.`in`.bufferedReader()) {
    gears = Array(5) { idx ->
        if (idx == 0) {
            CharArray(0)
        } else {
            readLine().toCharArray()
        }
    }

    val k = readLine().toInt()

    repeat(k) {
        val (num, dir) = readLine().split(" ").map { it.toInt() }
        rotateQueue.add(GearRotate(num, dir))
        var nowDir = dir
        for (i in num until gears.lastIndex) {
            if (gears[i + 1][6] != gears[i][2]) {
                rotateQueue.add(GearRotate(i + 1, -nowDir))
                nowDir = -nowDir
            } else {
                break
            }
        }

        nowDir = dir
        for (i in num downTo 2) {
            if (gears[i][6] != gears[i - 1][2]) {
                rotateQueue.add(GearRotate(i - 1, -nowDir))
                nowDir = -nowDir

            } else {
                break
            }
        }
        rotate()
    }

    var answer = 0
    for (i in 1..gears.lastIndex) {
        if (gears[i][0] - '0' == 1) {
            answer += scores[i]
        }
    }
    println(answer)
}

private fun rotate() {
    while (rotateQueue.isNotEmpty()) {
        val cur = rotateQueue.poll()
        val gearNum = cur.gearNum
        val dir = cur.dir
        val nowGear = gears[gearNum]

        when (dir) {
            CLOCK -> {
                val temp = nowGear.last()
                for (i in nowGear.lastIndex downTo 1) {
                    nowGear[i] = nowGear[i - 1]
                }
                nowGear[0] = temp
            }
            COUNTER_CLOCK -> {
                val temp = nowGear.first()
                for (i in 0 until nowGear.lastIndex) {
                    nowGear[i] = nowGear[i + 1]
                }
                nowGear[nowGear.lastIndex] = temp
            }
        }
    }
}