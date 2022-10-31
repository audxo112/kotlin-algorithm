package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val str = br.readLine()
    var zero = 0
    var one = 0
    var prev = ' '

    str.forEach {
        if (it != prev) {
            prev = it
            if (it == '0') {
                zero++
            } else {
                one++
            }
        }
    }

    println(minOf(zero, one))
}