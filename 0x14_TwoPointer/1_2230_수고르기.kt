package `kotlin-algorithm`.`0x14_TwoPointer`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toLong()
    val arr = LongArray(n) {
        br.readLine().toLong()
    }

    arr.sort()

    var end = 0
    var min = Long.MAX_VALUE
    var sub = 0L

    for (i in arr.indices) {
        while (end < n && sub < m) {
            sub = abs(arr[end] - arr[i])
            if (end == n - 1) break
            end++
        }

        if (sub == m) {
            min = sub
            break
        } else if (sub > m && sub < min) {
            min = sub
        }

        if (i == n - 1) break
        sub = abs(arr[end] - arr[i + 1])
    }

    println(min)
}