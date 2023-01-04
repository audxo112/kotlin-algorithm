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
    var length = 0
    var maxLength = Int.MIN_VALUE
    var removeCnt = 0

    for (i in arr.indices) {
        while (end < n && removeCnt <= k) {
            if (arr[end] % 2 == 0) {
                length++
            } else {
                if (removeCnt == k) break // 최대로 제거 됐어도 다음 수가 짝 수 일수 있다.
                removeCnt++
            }
            end++
        }

        if (length > maxLength) {
            maxLength = length
        }

        if (arr[i] % 2 == 0) {
            length--
        } else {
           removeCnt--
        }
    }

    println(maxLength)
}