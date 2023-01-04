package `kotlin-algorithm`.`0x14_TwoPointer`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Math.abs
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val snows = IntArray(n) {
        st.nextToken().toInt()
    }

    snows.sort()

    var min = Int.MAX_VALUE
    for (i in 0 ..snows.size - 3) {
        for (j in i + 3 until snows.size) {
            val firstSnowMan = snows[i] + snows[j]
            var left = i + 1
            var right = j - 1
            while (left < right) {
                var secondSnowMan = snows[left] + snows[right]
                val diff = abs(secondSnowMan - firstSnowMan)

                if (diff == 0) {
                    println(0)
                    return
                } else if (diff < min) {
                    min = diff
                }

                if (secondSnowMan > firstSnowMan) {
                    right--
                } else {
                    left++
                }
            }
        }
    }

    println(min)
}
