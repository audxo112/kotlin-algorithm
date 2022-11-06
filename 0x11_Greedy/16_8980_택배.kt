package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val m = br.readLine().toInt()
    val arr = Array(m) {
        st = StringTokenizer(br.readLine())
        IntArray(3) {
            st.nextToken().toInt()
        }
    }

    arr.sortWith(compareBy({ it[1] }, { it[0] }))

    val truck = IntArray(n + 1) { c } // 각 마을별 실을 수 있는 용량들
    var total = 0
    arr.forEach {
        val (s, e, box) = it

        var min = box
        for (i in s until e) {
            min = min.coerceAtMost(truck[i])
        }

        for (i in s until e) {
            truck[i] -= min
        }
        total += min
    }

    println(total)
}