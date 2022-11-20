package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine()) // 조카 수
    val m = st.nextToken().toInt() // 조카 수
    val n = st.nextToken().toInt() // 과자 수

    st = StringTokenizer(br.readLine())
    var start = 1L
    var end = Long.MIN_VALUE
    var mid: Long

    val snacks = LongArray(n) {
        st.nextToken().toLong().also { num ->
            end = end.coerceAtLeast(num)
        }
    }

    while (start <= end) {
        mid = (start + end) / 2
        if (isEnoughToShare(snacks, mid, m)) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }

    println(start - 1)
}

private fun isEnoughToShare(snacks: LongArray, mid: Long, m: Int): Boolean {
    var sum = 0L
    for (snack in snacks) {
        if (snack >= mid) {
            sum += (snack / mid)

            if (sum >= m) {
                return true
            }
        }
    }
    return false
}