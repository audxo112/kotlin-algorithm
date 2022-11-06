package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val arr = IntArray(k) {
        st.nextToken().toInt()
    }

    var count = 0
    val multi = hashSetOf<Int>()
    for (i in 0 until k) {
        if (!multi.contains(arr[i])) {
            if (multi.size < n) {
                multi.add(arr[i])
            } else {
                val useAfter = mutableListOf<Int>() // 나중에 사용되는 콘센트
                for (j in i until k) {
                    if (multi.contains(arr[j]) && !useAfter.contains(arr[j])) {
                        useAfter.add(arr[j])
                    }
                }

                // 현재 꽂혀 있는 콘센트가 모두 다시 사용됨
                if (useAfter.size == n) {
                    multi.remove(useAfter.last()) // 가장 나중에 사용되는 것 제거
                } else {
                    for (item in multi) {
                        if (!useAfter.contains(item)) {
                            multi.remove(item)
                            break
                        }
                    }
                }

                multi.add(arr[i])
                count++
            }
        }
    }
    println(count)
}