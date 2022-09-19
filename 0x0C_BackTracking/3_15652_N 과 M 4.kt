// https://www.acmicpc.net/problem/15652
package solution15652

import java.util.StringTokenizer

private fun backTracking(N:Int, M:Int, n:Int, m:Int, order:IntArray, builder:StringBuilder){
    if(m == M){
        order.forEach {
            builder.append(it)
            builder.append(' ')
        }
        builder.appendLine()
        return
    }

    for(nn in n .. N){
        order[m] = nn
        backTracking(N, M, nn, m + 1, order, builder)
    }
}

private fun solution(n:Int, m:Int) : String{
    val order = IntArray(m)
    val builder = StringBuilder()
    backTracking(n, m, 1, 0, order, builder)

    return builder.toString()
}


private fun main() = with(System.`in`.bufferedReader()){
    val tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    println(solution(n, m))
}