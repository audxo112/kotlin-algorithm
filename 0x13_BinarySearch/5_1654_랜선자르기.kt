package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val lan = LongArray(n) {
        br.readLine().toLong()
    }

    var start = 1L
    var end = lan.maxOrNull() ?: -1L
    var mid: Long

    while (start <= end) {
        mid = (start + end) / 2
        val count = cutLan(lan, mid)

        if (count >= k) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }

    println(start - 1)
}

private fun cutLan(lan: LongArray, mid: Long): Long {
    var count = 0L
    lan.forEach {
        count += it / mid
    }
    return count
}