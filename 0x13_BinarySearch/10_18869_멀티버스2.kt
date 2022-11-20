package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
    정렬된 중복제거 된 리스트, 이분탐색으로 인덱스 구하려고 만듬
    sorted().distinct()는 시간 초과
    upperBound, lowerBound 시간 초과
    map 사용 시간 초과 (key: 값, value: 인덱스)
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val m = st.nextToken().toInt() // 우주의 개수
    val n = st.nextToken().toInt()  // 각 우주의 행성 개수

    val inputs = Array(m) { idx ->
        st = StringTokenizer(br.readLine())
        IntArray(n) {
            st.nextToken().toInt()
        }
    }

    // 각 원소가 몇 번째로 큰 지 구하기 -> 정렬된 리스트에서의 index
    val indices = Array(m) { idx ->
        val list = inputs[idx].toSortedSet().toList()
        IntArray(n) {
            list.binarySearch(inputs[idx][it]) // 원래 값 넣어준다
        }
    }

    // index 구성이 같다면 count++
    var count = 0
    for (i in 0 until m) {
        for (j in i + 1 until m) {
            if (indices[i].contentEquals(indices[j])) {
                count++
            }
        }
    }

    println(count)
}

// 상한, 하한 둘 중 하나 사용해서 구해도 되는듯
private fun lowerBound(list: List<Int>, num: Int): Int {
    var start = 0
    var end = list.lastIndex
    var mid: Int

    while (start <= end) {
        mid = (start + end) / 2

        if (list[mid] >= num) {
            end = mid - 1
        } else {
            start = mid + 1
        }
    }

    return start
}

private fun upperBound(list: List<Int>, num: Int): Int {
    var start = 0
    var end = list.lastIndex
    var mid: Int

    while (start <= end) {
        mid = (start + end) / 2

        if (list[mid] <= num) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }

    return start
}