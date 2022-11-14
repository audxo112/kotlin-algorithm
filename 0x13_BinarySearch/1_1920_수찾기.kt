package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())
    val a = IntArray(n) {
        st.nextToken().toInt()
    }

    val m = br.readLine().toInt()
    st = StringTokenizer(br.readLine())

    a.sort()

    val sb = StringBuilder()
    repeat(m) {
        val num = st.nextToken().toInt()
        var start = 0
        var end = a.lastIndex
        var mid = 0
        var isFound = false
        while (start <= end) {
            mid = (start + end) / 2
            when {
                num == a[mid]-> {
                    isFound = true
                    break
                }
                num < a[mid] -> {
                    end = mid - 1
                }
                else -> {
                    start = mid + 1
                }
            }
        }
        sb.appendLine(if (isFound) 1 else 0)
    }

    println(sb.toString())
}