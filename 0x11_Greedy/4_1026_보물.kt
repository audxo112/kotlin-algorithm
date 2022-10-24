package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())
    val a = IntArray(n) {
        st.nextToken().toInt()
    }
    st = StringTokenizer(br.readLine())
    val b = IntArray(n) {
        st.nextToken().toInt()
    }

    a.sort()
    b.sortDescending()

    var sum = 0
    for (i in 0 until n) {
        sum += a[i] * b[i]
    }

    println(sum)
}