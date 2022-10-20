import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    var k = st.nextToken().toInt()
    var answer = 0

    val coin = IntArray(n) {
        readln().toInt()
    }

    for (c in coin.reversed()) {
        if (k >= c) {
            answer += k / c
            k %= c
        }
    }
    println(answer)
}