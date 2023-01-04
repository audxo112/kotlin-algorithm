package `kotlin-algorithm`.`0x14_TwoPointer`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

data class Student(var rank: Int = 0, var room: Int = 0)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    // 한 배열에 다 담기
    var room = 0
    val arr = Array(n * m) {
        if (it % m == 0) {
            room = it / m
            st = StringTokenizer(br.readLine())
        }
        Student(rank = st.nextToken().toInt(), room = room)
    }
    // 능력순으로 정렬
    arr.sortBy { it.rank }

    var end = 0
    var min = Int.MAX_VALUE
    val numOfStudent = IntArray(n) // 범위안에 속하는 반별 학생 수
    for (i in arr.indices) {
        while (end < arr.size && numOfStudent.contains(0)) {
            numOfStudent[arr[end].room]++
            end++
        }

        if (numOfStudent.contains(0).not()) {
            val sub = arr[end - 1].rank - arr[i].rank
            min = min.coerceAtMost(sub)
        }

        numOfStudent[arr[i].room]--
    }

    println(min)
}