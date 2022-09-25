//https://www.acmicpc.net/problem/15656
package solution15656

import java.util.StringTokenizer


private fun permutation(N:Int, M:Int, m:Int, numArray: Array<String>, order:IntArray, sb:StringBuilder){
    if(m == M){
        order.forEach {
            sb.append(numArray[it]).append(' ')
        }
        sb.append('\n')
        return
    }

    for(n in 0 until N){
        order[m] = n
        permutation(N, M, m + 1, numArray, order, sb)
    }
}


private fun solution(n:Int, m:Int, numArray:Array<String>) : String{
    val sb = StringBuilder()
    val order = IntArray(m)
    permutation(n, m, 0, numArray, order, sb)

    return sb.toString()
}


private fun main() = with(System.`in`.bufferedReader()){
    var tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    tokenizer = StringTokenizer(readLine(), " ")
    val numArray = Array(n){
        tokenizer.nextToken()
    }
    numArray.sortBy {
        it.toInt()
    }

    println(solution(n, m, numArray))
}