//https://www.acmicpc.net/problem/15650
package solution15650

import java.util.*


private fun combination(N:Int, M:Int, n:Int, m:Int, order:IntArray, builder:StringBuilder){
    if(M == m){
        order.forEach{
            builder.append(it)
            builder.append(' ')
        }
        builder.appendLine()
        return
    }

    for (nn in n .. N){
        order[m] = nn
        combination(N, M, nn + 1, m + 1, order, builder)
    }
}

private fun solution(n:Int, m:Int) : String{
    val builder = StringBuilder()
    val order = IntArray(m)

    combination(n, m, 1, 0, order, builder)

    return builder.toString()
}


private fun main() = with(System.`in`.bufferedReader()){
    val tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    print(solution(n, m))
}