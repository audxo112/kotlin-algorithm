package `kotlin-algorithm`.`0x13_BinarySearch`

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()

    var st = StringTokenizer(readln())

    val arr = IntArray(n) {
        st.nextToken().toInt()
    }

    val m = readln().toInt()

    st = StringTokenizer(readln())
    arr.sort()

    val sb = StringBuilder()

    while (st.hasMoreTokens()) {
        val target = st.nextToken().toInt()

        val s = getLowerBound(arr,target)
        val e = getUpperBound(arr,target)
        sb.append("${e-s} ")

    }
    println(sb.toString())

}
private fun getLowerBound(arr: IntArray, target: Int): Int {
    var left = 0
    var right = arr.size

    while(left < right) {
        val mid = (left + right) / 2
        if(arr[mid] >= target){
            right = mid
        } else {
            left = mid + 1
        }
    }
    return left
}

private fun getUpperBound(arr: IntArray, target: Int): Int {
    var left = 0
    var right = arr.size

    while(left < right) {
        val mid = (left + right) / 2
        if(arr[mid] > target){
            right = mid
        } else {
            left = mid + 1
        }
    }
    return left
}