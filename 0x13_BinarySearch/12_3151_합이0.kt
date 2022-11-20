package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n) {
        st.nextToken().toInt()
    }

    arr.sort()

    var count = 0L  // long이여야 한다.
    for (i in 0 until n - 2) {
        for (j in i + 1 until  n - 1) {
            val target = -(arr[i] + arr[j])
            count += upperBound(arr, j + 1, target) - lowerBound(arr, j + 1, target)
        }
    }

    println(count)
}

private fun lowerBound(arr: IntArray, startAt: Int, num: Int): Int {
    var start = startAt
    var end = arr.lastIndex
    var mid: Int

    while (start <= end) {
        mid = (start + end) / 2
        if (arr[mid] >= num) {
            end = mid - 1
        } else {
            start = mid + 1
        }
    }

    return start
}

private fun upperBound(arr: IntArray, startAt: Int, num: Int): Int {
    var start = startAt
    var end = arr.lastIndex
    var mid: Int

    while (start <= end) {
        mid = (start + end) / 2
        if (arr[mid] <= num) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }

    return start
}