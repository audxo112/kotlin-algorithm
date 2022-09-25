//https://www.acmicpc.net/problem/15657
package solution15657

import java.util.StringTokenizer


private fun permutation(N:Int, M:Int, n:Int, m:Int, numArray: Array<String>, order:IntArray, sb:StringBuilder) {
    if(m == M){
        order.forEach {
            sb.append(numArray[it]).append(' ')
        }
        sb.append('\n')
        return
    }

    for(i in n until N){
        order[m] = i
        permutation(N, M, i, m + 1, numArray, order, sb)
    }
}


private fun solution(n:Int, m:Int, numArray:Array<String>) : String{
    val sb = StringBuilder()
    val order = IntArray(m)

    permutation(n, m, 0, 0, numArray, order, sb)

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