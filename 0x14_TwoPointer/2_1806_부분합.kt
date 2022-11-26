package `kotlin-algorithm`.`0x14_TwoPointer`

import java.util.StringTokenizer
import kotlin.math.min

fun main() {
    var st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val s = st.nextToken().toInt()

    st = StringTokenizer(readln())

    val numArray = IntArray(n) {
        st.nextToken().toInt()
    }
    var partialSum = 0L
    val sumArray = LongArray(n + 1)

    //한 수로 완성이 될 수 있으니 0부터 세야 한다
    sumArray[0] = 0
    for (i in 1..numArray.size) {
        partialSum += numArray[i - 1]
        sumArray[i] = partialSum
    }

    var left = 0
    var right = 0

    var answer = Int.MAX_VALUE

    // sumArray의 길이까지 가야한다..
    while (left < n && right < n) {
        val diff = sumArray[right] - sumArray[left]
        if (diff < s) {
            right++
        } else {
            answer = min(answer, right - left)
            left++
        }
    }
    if (answer == Int.MAX_VALUE) {
        answer = 0
    }
    println(answer)
}