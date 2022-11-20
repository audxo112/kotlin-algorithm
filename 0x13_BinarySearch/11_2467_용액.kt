package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = LongArray(n) {
        st.nextToken().toLong()
    }

    var start = 0
    var end = arr.lastIndex
    var min = Long.MAX_VALUE
    var res = LongArray(2)

    while (start < end) {
        val sum = arr[start] + arr[end]
        // 절댓값으로 비교
        if (abs(sum) < min) {
            min = abs(sum)
            res[0] = arr[start]
            res[1] = arr[end]
        }

        when {
            sum < 0 -> start++
            sum > 0 -> end--
            else -> break
        }
    }

    println("${res[0]}  ${res[1]}")
}