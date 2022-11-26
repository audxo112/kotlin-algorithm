package `kotlin-algorithm`.`0x13_BinarySearch`

import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {

    val n = readLine().toInt()

    val st = StringTokenizer(readLine())
    val students = IntArray(n) {
        st.nextToken().toInt()
    }
    students.sort()

    var answer = 0L
    for (i in students.indices) {
        val target = students[i]

        var left = i + 1
        var right = n - 1

        while (left < right) {
            val sum = students[left] + students[right]
            if (sum < -target) {
                left += 1
            } else if (sum > -target) {
                right -= 1
            } else {
                if (students[left] == students[right]) {
                    val c = (right - left + 1)
                    answer += c * (c-1) / 2
                    break
                } else {
                    var leftCnt = 1
                    var rightCnt = 1
                    while (students[left] == students[left + 1]) {
                        leftCnt += 1
                        left += 1
                    }
                    while (students[right] == students[right - 1]) {
                        rightCnt += 1
                        right -= 1
                    }
                    answer += leftCnt * rightCnt
                    left += 1
                    right -= 1
                }
            }
        }
    }
    println(answer)
}