package `kotlin-algorithm`.`0x14_TwoPointer`

import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val numArray = IntArray(n){
        readln().toInt()
    }
    numArray.sort()

    var left = 0
    var right = 0

    var answer = Int.MAX_VALUE

    while (left < n && right < n){
        val diff = numArray[right] - numArray[left]
        if(diff < m) {
            right += 1
        } else {
            if(answer > diff) {
                answer = diff
            }
            left += 1
        }
    }
    println(answer)
}