package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val time = IntArray(n) {
        st.nextToken().toInt()
    }

    time.sort()

    var wait = 0
    var total = 0
    time.forEach {
        wait += it
        total += wait
    }

    println(total)
}