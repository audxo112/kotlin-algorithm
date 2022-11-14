package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n) {
        val num = st.nextToken().toInt()
        num
    }
    
    val set = arr.sorted().distinct()

    val sb = StringBuilder()
    arr.forEach {
        var start = 0
        var end = set.lastIndex
        var mid: Int

        while (start <= end) {
            mid = (start + end) / 2

            when  {
                it == set[mid] -> {
                    sb.append("$mid ")
                    break
                }
                it < set[mid] -> {
                    end = mid - 1
                }
                else -> {
                    start = mid + 1
                }
            }
        }
    }

    println(sb.toString())
}
