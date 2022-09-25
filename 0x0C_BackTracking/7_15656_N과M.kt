import java.util.*


fun main() = with(System.`in`.bufferedReader()) {

    val bw = System.out.bufferedWriter()
    val sb = StringBuilder(100)

    var st = StringTokenizer(readLine(), " ")
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val input = IntArray(n)

    st = StringTokenizer(readLine(), " ")
    for (i in 0 until n) {
        input[i] = st.nextToken().toInt()
    }
    input.sort()

    val output = IntArray(m)
    val used = BooleanArray(9)
    backtracking3(0, n, m, input, output, used, sb)

    bw.write(sb.toString())
    bw.flush()
    bw.close()
}

private fun backtracking3(
    cur: Int,
    n: Int,
    m: Int,
    input: IntArray,
    output: IntArray,
    used: BooleanArray,
    sb: StringBuilder
) {
    if (m == cur) {
        output.forEach { sb.append("$it ") }
        sb.append("\n")
        return
    }

    for (i in input.indices) {
        if (!used[i + 1]) {
            output[cur] = input[i]
            backtracking3(cur + 1, n, m, input, output, used, sb)
        }
    }
}