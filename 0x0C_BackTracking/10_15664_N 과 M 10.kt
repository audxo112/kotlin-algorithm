//https://www.acmicpc.net/problem/15664
package solution15664

import java.util.StringTokenizer


private fun combination(N:Int, M:Int, n:Int, m:Int, numArray: Array<String>, order:IntArray, visited:BooleanArray, sb:StringBuilder) {
    if(m == M){
        order.forEach {
            sb.append(numArray[it]).append(' ')
        }
        sb.append('\n')
        return
    }

    var before = ""
    for(i in n until N){
        if(numArray[i] == before){
            continue
        }
        before = numArray[i]
        order[m] = i
        combination(N, M, i + 1, m + 1, numArray, order, visited, sb)
    }
}


private fun solution(n:Int, m:Int, numArray:Array<String>) : String{
    val sb = StringBuilder()
    val order = IntArray(m)
    val visited = BooleanArray(n)

    combination(n, m, 0, 0, numArray, order, visited, sb)

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