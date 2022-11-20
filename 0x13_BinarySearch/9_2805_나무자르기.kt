package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt() // 나무 개수
    val m = st.nextToken().toInt() // 필요한 나무 높이의 합

    var start = 1L
    var end = Long.MIN_VALUE
    var mid: Long

    st = StringTokenizer(br.readLine())
    val trees = LongArray(n) {
        st.nextToken().toLong().also { num ->
            end = end.coerceAtLeast(num)
        }
    }

    while (start <= end) {
        mid = (start + end) / 2

        if (isEnoughTreeLength(trees, mid, m)) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }

    println(start - 1)
}

private fun isEnoughTreeLength(trees: LongArray, mid: Long, m: Int): Boolean {
    var sum = 0L
    for (tree in trees) {
        if (tree > mid) {
            sum += tree - mid
            if (sum >= m) {
                return true
            }
        }
    }
    return false
}