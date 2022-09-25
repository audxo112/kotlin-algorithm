import java.util.*


fun main() = with(System.`in`.bufferedReader()) {

    val bw = System.out.bufferedWriter()
    val sb = StringBuilder(100)

    val st = StringTokenizer(readLine(), " ")
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val arr = IntArray(m)
    val used = BooleanArray(9)
    backtracking3(0, n, m, arr, used, sb)

//    for (i in 1..n) {
//        for (j in i..n) {
//            println("$i $j")
//        }
//    }

    bw.write(sb.toString())
    bw.flush()
    bw.close()
}

private fun backtracking3(cur: Int, n: Int, m: Int, arr: IntArray, used: BooleanArray, sb: StringBuilder) {
    if (m == cur) {
        arr.forEach { sb.append("$it ") }
        sb.append("\n")
        return
    }

    for (i in 1..n) {
        if (!used[i]) {
            arr[cur] = i
            backtracking3(cur + 1, n, m, arr, used, sb)
            used[i] = true
        }
        if (used[n]) {
            for (j in i + 1..n) {
                used[j] = false
            }
        }
    }
}