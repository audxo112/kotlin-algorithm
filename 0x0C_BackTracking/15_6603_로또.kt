// https://www.acmicpc.net/problem/6603
package solution6603

import java.util.StringTokenizer


private fun permutation(N: Int, M: Int, n: Int, m: Int, numArr: Array<String>, order: IntArray, sb: StringBuilder) {
    if(M == m){
        order.forEach {
            sb.append(numArr[it]).append(' ')
        }
        sb.append('\n')
        return
    }

    for(i in n until N){
        order[m] = i
        permutation(N, M, i + 1, m + 1, numArr, order, sb)
    }
}


private fun solution(n: Int, numArr: Array<String>): String {
    val sb = StringBuilder()
    val order = IntArray(6)

    permutation(n, 6, 0, 0, numArr, order, sb)

    return sb.toString()
}

private fun main() = with(System.`in`.bufferedReader()) {
    do {
        val tokenizer = StringTokenizer(readLine(), " ")
        val n = tokenizer.nextToken().toInt()
        val numArr = Array(n) {
            tokenizer.nextToken()
        }
        numArr.sortBy {
            it.toInt()
        }
        println(solution(n, numArr))
    } while (n != 0)
}