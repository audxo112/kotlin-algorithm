package `kotlin-algorithm`.`0x11_Greedy`

import java.util.StringTokenizer

fun main() {
    var st = StringTokenizer(readln())
    val n = st.nextToken().toInt()

    st = StringTokenizer(readln())
    val arr = IntArray(n) {
        st.nextToken().toInt()
    }
    arr.sort()
    for (i in 1..arr.lastIndex) {
        arr[i] += arr[i - 1]
    }

    println(arr.sum())
}