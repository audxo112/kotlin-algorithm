package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val str = br.readLine()
    val exps = str.split("-")

    var sum = 0
    for (i in exps.indices) {
        if (i == 0) {
         sum = exps[i].split("+").sumOf { it.toInt() }
        } else {
            sum -= exps[i].split("+").sumOf { it.toInt() }
        }
    }

    println(sum)
}
