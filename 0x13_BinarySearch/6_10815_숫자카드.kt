package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())
    val cards = IntArray(n) {
        st.nextToken().toInt()
    }

    val m = br.readLine().toInt()
    val sb = StringBuilder()
    st = StringTokenizer(br.readLine())

    cards.sort()

    repeat(m) {
        val num = st.nextToken().toInt()
        sb.append(if (cards.binarySearch(num) >= 0) 1 else 0).append(" ")
    }

    println(sb.toString())
}
