package `kotlin-algorithm`.`0x13_BinarySearch`

import java.util.StringTokenizer
import kotlin.math.absoluteValue

fun main() {
    val n = readln().toInt()

    val st = StringTokenizer(readln())
    val solution = LongArray(n) {
        st.nextToken().toLong()
    }

    var left = 0
    var right = n - 1

    var answer = -1 to -1
    var mix = Long.MAX_VALUE
    while (left < right) {
        val nowMix = (solution[left] + solution[right])
        if (mix >= nowMix.absoluteValue) {
            mix = nowMix.absoluteValue
            answer = left to right

        }
        if (nowMix < 0) {
            left += 1
        } else {
            right -= 1
        }
    }
    println("${solution[answer.first]} ${solution[answer.second]}")
}