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
        if(arr.binarySearch(target) >= 0){
            sb.append("1 ")
        } else {
            sb.append("0 ")
        }

    }
    println(sb.toString())
}