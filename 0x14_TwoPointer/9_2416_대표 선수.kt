package `kotlin-algorithm`.`0x14_TwoPointer`

import java.io.StreamTokenizer
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }

    val n = input().toInt()
    val m = input().toInt()

    val studentsList = Array(n * m) { idx ->
        val group = idx / m
        Student(input(), group)
    }

    studentsList.sortByDescending { -it.stat }

    val visited = IntArray(n)

    var start = 0
    var end = 0

    var answer = Int.MAX_VALUE.toLong()

    while (end < n * m) {
//        if (visited.count { it < 1 } != 0) {
        if (visited.contains(0)) {
            visited[studentsList[end].group]++
            end++
        } else {
            answer = min(answer, studentsList[end - 1].stat - studentsList[start].stat)
            visited[studentsList[start].group]--
            start++
        }
    }

    println(answer)
}

data class Student(val stat: Long, val group: Int)