package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val arr = IntArray(n) {
        br.readLine().toInt()
    }

    arr.sort()

    var max = Integer.MIN_VALUE
    for (i in arr.indices) {
        max = max.coerceAtLeast(arr[i] * (n - i))
    }

    println(max)
}