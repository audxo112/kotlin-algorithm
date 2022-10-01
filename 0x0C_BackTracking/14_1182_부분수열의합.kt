import java.util.*
//https://www.acmicpc.net/problem/1182

private var result = 0
private var output = IntArray(21)
private var used = BooleanArray(21)

fun main() = with(System.`in`.bufferedReader()) {

    var st = StringTokenizer(readLine(), " ")
    val n = st.nextToken().toInt()
    val s = st.nextToken().toInt()

    val input = IntArray(n)

    st = StringTokenizer(readLine(), " ")
    for (i in 0 until n) {
        input[i] = st.nextToken().toInt()
    }
    input.sort()

    for (m in 1..n) {
        output = IntArray(21)
        used = BooleanArray(21)
        back(0, 0, n, m, s, input)
    }


    println(result)
}

private fun back(depth: Int, i: Int, n: Int, m: Int, s: Int, input: IntArray) {
    if (depth == m) {
        if (output.sum() == s) {
            result++
        }
        return
    }

    for (k in i until n) {
        output[depth] = input[k]
        back(depth + 1, k + 1, n, m, s, input)
    }
}