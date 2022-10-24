package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val time = Array(n) {
        val st = StringTokenizer(br.readLine())
        IntArray(2) {
            st.nextToken().toInt()
        }
    }

    time.sortWith(compareBy<IntArray> {
        it[1]
    }.thenBy {
        it[0]
    })

    var tmp = time[0][1]
    var count = 1

    for (i in 1 until time.size) {
        if (time[i][0] >= tmp) {
            tmp = time[i][1]
            count++
        }
    }

    println(count)
}