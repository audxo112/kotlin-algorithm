package `kotlin-algorithm`.`0x14_TwoPointer`

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val s = input()

    val arr = IntArray(n) { input() % 2 }

    var start = 0
    var end = 0

    var cntEven = 0
    var cntOdd = 0

    var answer = 0

    while (start < n && end < n) {
        if (cntOdd <= s) {
            if (arr[end] == 1) {
                cntOdd++
            } else {
                cntEven++
            }
            end++
        } else {
            if (arr[start] == 1) {
                cntOdd--
            } else {
                cntEven--
            }
            start++
        }
        answer = max(answer, cntEven)
    }

    println(answer)

}