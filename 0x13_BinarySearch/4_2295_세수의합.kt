package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val arr = IntArray(n) {
        br.readLine().toInt()
    }

    arr.sort()

    val sumList = mutableListOf<Int>() // x + y = k - z 에서 x + y 에 대한 리스트
    for (i in arr.indices) {
        for (j in i until arr.size) {
            sumList.add(arr[i] + arr[j])
        }
    }

    sumList.sort()

    // k번째 수가 최대가 되도록이므로 뒤에서 부터 탐색
    for (i in arr.lastIndex downTo 0) {
        for (j in 0 until i) {
            // k - z 에 해당하는 x + y가 있는지 탐색
            if (sumList.binarySearch(arr[i] - arr[j]) >= 0) {
                println(arr[i])
                return
            }
        }
    }
}

private fun search(sumList: List<Int>, find: Int): Boolean {
    var start = 0
    var end = sumList.lastIndex
    var mid: Int

    while (start <= end) {
        mid = (start + end) / 2

        when {
            find == sumList[mid] -> {
                return true
            }
            find < sumList[mid] -> {
                end = mid - 1
            }
            else -> {
                start = mid + 1
            }
        }
    }

    return false
}
