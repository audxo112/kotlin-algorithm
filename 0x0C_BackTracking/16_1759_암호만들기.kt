import java.io.BufferedWriter
import java.util.*


fun main() = with(System.`in`.bufferedReader()) {

    val bw = System.out.bufferedWriter()

    var st = StringTokenizer(readLine(), " ")
    val l = st.nextToken().toInt()
    val c = st.nextToken().toInt()

    val input = CharArray(c)
    val output = CharArray(l)

    st = StringTokenizer(readLine(), " ")
    for (i in 0 until c) {
        input[i] = st.nextToken().toCharArray()[0]
    }
    input.sort()

    back(0, 0, c, l, input, output, bw)

    bw.flush()
    bw.close()
}

private fun back(depth: Int, i: Int, n: Int, m: Int, input: CharArray, output: CharArray, bw: BufferedWriter) {
    if (depth == m) {
        if (isValid(output)) {
            output.forEach {
                bw.write("$it")
            }
            bw.write("\n")
        }
        return
    }

    for (k in i until n) {
        output[depth] = input[k]
        back(depth + 1, k + 1, n, m, input, output, bw)
    }
}

private fun isValid(output: CharArray): Boolean {
    val count = output.count { (it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u') }

    if (count < 1) {
        return false
    }

    if (output.size - count < 2) {
        return false
    }
    return true
}
