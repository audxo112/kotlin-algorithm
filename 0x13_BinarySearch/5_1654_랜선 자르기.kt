package `kotlin-algorithm`.`0x13_BinarySearch`

import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())
    val k = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    val line = LongArray(k)
    var max = 1L
    var min = 1L
    for (i in 0 until k) {
        line[i] = readln().toLong()
        if(line[i] > max) {
            max = line[i]
        }
    }


    while (max >= min) {
        var cnt = 0L
        val mid = (max + min) / 2

        for (l in line){
            cnt += l / mid
        }

        if(cnt >= n) {
            min = mid + 1
        } else if (cnt < n) {
            max = mid - 1
        }
    }
    println(max)
}
