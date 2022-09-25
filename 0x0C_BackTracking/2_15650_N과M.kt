import java.util.*

fun main() = with(System.`in`.bufferedReader()) {

    val bw = System.out.bufferedWriter()
    val sb = StringBuilder(100)

    val st = StringTokenizer(readLine(), " ")
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val arr = IntArray(m)
    val used = BooleanArray(n)
    backtracking1(0, n, m, arr, used, sb)

    bw.write(sb.toString())
    bw.flush()
    bw.close()
}

private fun backtracking1(cur: Int, n: Int, m: Int, arr: IntArray, used: BooleanArray, sb: StringBuilder) {
    if (m == cur) {
        arr.forEach { sb.append("$it ") }
        sb.append("\n")
        return
    }

    for (i in 1..n) {
        if (!used[i - 1]) {
            arr[cur] = i
            for (j in 0 until i) {
                used[j] = true
            }
            backtracking1(cur + 1, n, m, arr, used, sb)
            for (j in 0 until i) {
                used[j] = false
            }
        }
    }
}