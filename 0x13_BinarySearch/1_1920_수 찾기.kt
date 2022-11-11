package `kotlin-algorithm`.`0x13_BinarySearch`

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()
    var st = StringTokenizer(readln())
    val arr = IntArray(n) {
        st.nextToken().toInt()
    }
    arr.sort()
    val m = readln().toInt()

    st = StringTokenizer(readln())

    while (st.hasMoreTokens()) {
        val target = st.nextToken().toInt()
        println(binarySearchRecursion(arr, target, 0, n))
    }
}

private fun binarySearchRecursion(arr: IntArray, target: Int, start: Int, end: Int): Int {
    val mid = (start + end) / 2
    return if (arr[mid] == target) {
        1
    } else {
        if (start + 1 == end) {
            0
        } else if (arr[mid] < target) {
            binarySearchRecursion(arr, target, mid, end)
        } else {
            binarySearchRecursion(arr, target, start, mid)
        }
    }
}


