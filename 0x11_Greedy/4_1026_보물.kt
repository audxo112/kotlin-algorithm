package `kotlin-algorithm`.`0x11_Greedy`

import java.util.*

fun main() {
    var st = StringTokenizer(readln())
    val n = st.nextToken().toInt()

    st = StringTokenizer(readln())
    val array1 = IntArray(n) {
        st.nextToken().toInt()
    }

    st = StringTokenizer(readln())
    val array2 = IntArray(n) {
        st.nextToken().toInt()
    }

    array1.sort()
    array2.sortDescending()

    var answer = 0
    for (i in array1.indices) {
        answer += array1[i] * array2[i]
    }

    println(answer)
}