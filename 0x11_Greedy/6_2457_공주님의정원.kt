package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val flowers = Array(n) {
        val st = StringTokenizer(br.readLine())
        arrayOf(
            st.nextToken().toInt() * 100 + st.nextToken().toInt(),
            st.nextToken().toInt() * 100 + st.nextToken().toInt()
        )
    }

    flowers.sortWith(compareBy<Array<Int>> {
        it[0]
    }.thenByDescending {
        it[1]
    })

    val end = 1201
    var tmp = 301
    var count = 0
    var idx = 0
    var max = 0

    while (tmp < end) {
        var next = false

        for (i in idx until n) {
            if (flowers[i][0] > tmp) break

            if (flowers[i][1] > max) {
                next = true
                max = flowers[i][1]
                idx = i + 1
            }
        }

        if (next) {
            tmp = max
            count++
        } else {
            break
        }
    }

    println(if (tmp < end) 0 else count)
}