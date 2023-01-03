package `kotlin-algorithm`.`0x14_TwoPointer`

import java.io.StreamTokenizer
import kotlin.math.abs
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()

    val arr = IntArray(n) {
        input()
    }
    arr.sort()

    var answer = Int.MAX_VALUE
    for (i in 0 until n - 3) {
        for (j in i + 3 until n) {
            var start = i + 1
            var end = j - 1

            val firstSnowman = arr[i] + arr[j]
            while (start < end) {
                val secSnowman = arr[start] + arr[end]
                answer = min(answer, abs(firstSnowman - secSnowman))

                if (firstSnowman > secSnowman) {
                    start++
                } else {
                    end--
                }
            }
        }
    }

    println(answer)
}