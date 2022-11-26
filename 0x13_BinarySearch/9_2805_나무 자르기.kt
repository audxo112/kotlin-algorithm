package `kotlin-algorithm`.`0x13_BinarySearch`

import java.util.StringTokenizer

fun main() {
    var st = StringTokenizer(readln())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    st = StringTokenizer(readln())

    var min = 0L
    var max = Int.MAX_VALUE.toLong()

    val trees = IntArray(n) {
        st.nextToken().toInt()
    }



    while (min <= max) {
        val mid = (min + max) / 2

        var total = 0L

        for (t in trees) {
            if (t > mid) {
                total += t - mid
            }
        }

        if (total >= m) {
            min = mid + 1
        } else {
            max = mid - 1
        }
    }
    println(max)
}