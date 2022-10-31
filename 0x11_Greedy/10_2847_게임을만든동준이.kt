package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val arr = IntArray(n) { br.readLine().toInt() }

    var sum = 0
    var last = arr.last()
    for(i in arr.lastIndex - 1 downTo  0) {
        if (last <= arr[i]) {
            last -= 1
            sum += arr[i] - last
        } else {
            last = arr[i]
        }
    }

    println(sum)
}