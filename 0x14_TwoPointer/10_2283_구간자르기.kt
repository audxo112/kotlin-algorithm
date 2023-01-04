package `kotlin-algorithm`.`0x14_TwoPointer`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    // 위치 x에서 시작되는 선, 끝나는 선 정보
    val info = IntArray(1000001)
    repeat(n) {
        st = StringTokenizer(br.readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt()
        info[s]++
        info[e]--
        min = min.coerceAtMost(s)
        max = max.coerceAtLeast(e)
    }
    // 위치 x에서 속한 선 수
    val numberOfLine = IntArray(1000001)
    numberOfLine[min] = info[min]
    for (i in min until max) {
        numberOfLine[i + 1] = numberOfLine[i] + info[i + 1]
    }

    var start = 0
    var end = 0
    var sum = 0

    while (end <= max && start <= end) {
        when {
            sum == k -> {
                println("$start $end")
                return
            }
            sum < k -> {
                sum += numberOfLine[end++]
            }
            else -> {
                sum -= numberOfLine[start++]
            }
        }
    }

    println("0 0")
}