//https://www.acmicpc.net/problem/15666
package solution15666

import java.util.StringTokenizer


private fun combination(N:Int, M:Int, n:Int, m:Int, numList:List<String>, order:IntArray, sb:StringBuilder) {
    if(m == M){
        order.forEach {
            sb.append(numList[it]).append(' ')
        }
        sb.append('\n')
        return
    }

    for(i in n until N){
        order[m] = i
        combination(N, M, i, m + 1, numList, order, sb)
    }
}


private fun solution(n:Int, m:Int, numList:List<String>) : String{
    val sb = StringBuilder()
    val order = IntArray(m)

    combination(n, m, 0, 0, numList, order, sb)

    return sb.toString()
}

private fun main() = with(System.`in`.bufferedReader()){
    var tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    tokenizer = StringTokenizer(readLine(), " ")
    val numSet = mutableSetOf<String>()
    repeat(n){
        numSet.add(tokenizer.nextToken())
    }
    val numList = numSet.sortedBy {
        it.toInt()
    }

    println(solution(numList.size, m, numList))
}