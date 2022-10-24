package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    var k = st.nextToken().toInt()
    val coin = IntArray(n) {
        br.readLine().toInt()
    }

    var count = 0
    for (i in coin.size - 1 downTo 0) {
        if (k >= coin[i]) {
            count += k / coin[i]
            k %= coin[i]

            if (k == 0) break
        }
    }

    println(count)
}