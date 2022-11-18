package `kotlin-algorithm`.`0x13_BinarySearch`

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()

    val st = StringTokenizer(readln())
    val arr = IntArray(n) {
        st.nextToken().toInt()
    }

    val sortedArray = arr.toSet().toIntArray().sortedArray()
    val sb = StringBuilder()

    for(num in arr){
        sb.append(getLowerBound(sortedArray,num).toString() + " ")
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