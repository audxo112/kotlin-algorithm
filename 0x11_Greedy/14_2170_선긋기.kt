package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val arr = Array(n) { arr ->
        val st = StringTokenizer(br.readLine())
        IntArray(2) {
            st.nextToken().toInt()
        }
    }

    arr.sortBy { it[0] }

    var (start, end) = arr[0]
    var len = end - start
    for (i in 1 until n) {
        val (s, e) = arr[i]
        if (e <= end) {
            continue
        } else if (s < end) {
            len += e - end
            end = e
        } else {
            len += e - s
            start = s
            end = e
        }
    }
    println(len)
}