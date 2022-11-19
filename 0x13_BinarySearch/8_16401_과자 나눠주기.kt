package `kotlin-algorithm`.`0x13_BinarySearch`

import java.util.StringTokenizer

fun main() {
    var st = StringTokenizer(readln())

    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    st = StringTokenizer(readln())

    var min = 1
    var max = 1
    val snackLength = IntArray(n) {
        val length = st.nextToken().toInt()
        if (max < length) {
            max = length
        }
        length
    }
    while (min <= max) {
        var cnt = 0
        val mid = (min + max) / 2

        for (l in snackLength) {
            if (l >= mid) {
                cnt += l / mid
            }
        }

        if (cnt >= m) {
            min = mid + 1
        } else {
            max = mid - 1
        }
    }
    println(max)

}