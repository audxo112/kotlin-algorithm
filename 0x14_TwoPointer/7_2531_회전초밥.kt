package `kotlin-algorithm`.`0x14_TwoPointer`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt() // 접시 수
    val d = st.nextToken().toInt() // 초밥 가짓수
    val k = st.nextToken().toInt() // 연속해서 먹는 접시 수
    val c = st.nextToken().toInt() // 쿠폰 번호
    val arr = IntArray(N) {
        br.readLine().toInt()
    }

    var end = 0
    var max = Int.MIN_VALUE
    var count = 0
    val visited = IntArray(d + 1)
    for (i in arr.indices) {
        while (end - i < k) {
            if (visited[arr[end % N]] == 0) {
                count++
            }
            visited[arr[end % N]]++
            end++
        }

        val res = if (visited[c] == 0) {
            count + 1
        } else {
            count
        }

        max = max.coerceAtLeast(res)
        visited[arr[i % N]]--
        if (visited[arr[i % N]] == 0) {
            count--
        }
    }

    println(max)
}