// https://www.acmicpc.net/problem/15651
package solution15651

import java.util.StringTokenizer


private fun permutationWithRepetition(N:Int, M:Int, m:Int, order:IntArray, builder:StringBuilder){
    if(m == M){
        order.forEach {
            builder.append(it)
            builder.append(' ')
        }
        builder.appendLine()
        return
    }

    for(n in 1 .. N){
        order[m] = n
        permutationWithRepetition(N, M, m + 1, order, builder)
    }
}


private fun solution(n:Int, m:Int) : String{
    val order = IntArray(m)
    val builder = StringBuilder()
    permutationWithRepetition(n, m, 0, order, builder)

    return builder.toString()
}


private fun main() = with(System.`in`.bufferedReader()){
    val tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    println(solution(n, m))
}