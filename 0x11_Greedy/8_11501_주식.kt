package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()

    repeat(br.readLine().toInt()) {
        val day = br.readLine().toInt()
        val st = StringTokenizer(br.readLine())
        val stocks = LongArray(day) { st.nextToken().toLong() }

        var sum: Long = 0
        var max: Long = 0

        for (i in stocks.lastIndex downTo 0) {
            if (stocks[i] > max) {
                max = stocks[i]
            } else {
                sum += max - stocks[i]
            }
        }

        sb.appendLine(sum)
    }

    println(sb.toString())
}