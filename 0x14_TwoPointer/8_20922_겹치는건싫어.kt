package `kotlin-algorithm`.`0x14_TwoPointer`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val arr = IntArray(n) {
        st.nextToken().toInt()
    }

    var end = 0
    var max = Int.MIN_VALUE
    val map = mutableMapOf<Int, Int>()

    for (i in arr.indices) {
        while (end < n) {
            val count = map[arr[end]] ?: 0
            if (count == k) {
                break
            }
            map[arr[end]] = count + 1
            end++
        }

        if (max < end - i) {
            max = end - i
        }

        val count = map[arr[i]]
        if (count != null) {
            map[arr[i]] = count - 1
        }
    }

    println(max)
}