import java.io.BufferedWriter
import java.util.*


private var output = IntArray(6)

fun main() = with(System.`in`.bufferedReader()) {

    val bw = System.out.bufferedWriter()

    var inputStr = readLine()
    while (inputStr.trim() != "0") {
        var st = StringTokenizer(inputStr, " ")
        val n = st.nextToken().toInt()

        val inputArr = IntArray(n)

        for (i in 0 until n) {
            inputArr[i] = st.nextToken().toInt()
        }
        back(0, 0, n, 6, inputArr, bw)

        bw.write("\n")
        inputStr = readLine()
    }
    bw.flush()
    bw.close()
}

private fun back(depth: Int, i: Int, n: Int, m: Int, input: IntArray, bw: BufferedWriter) {
    if (depth == m) {
        output.forEach {
            bw.write("$it ")
        }
        bw.write("\n")
        return
    }

    for (k in i until n) {
        output[depth] = input[k]
        back(depth + 1, k + 1, n, m, input, bw)
    }
}
