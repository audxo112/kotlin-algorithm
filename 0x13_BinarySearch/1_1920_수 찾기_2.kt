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
        println(binarySearchIteration(target,arr))

    }
}

private fun binarySearchIteration(target: Int, arr: IntArray): Int {
    var start = 0
    var end = arr.size - 1

    while (start <= end) {
        val mid = (start + end) / 2

        if (arr[mid] == target) {
            return 1
        }
        else if (arr[mid] < target) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }
    return 0
}