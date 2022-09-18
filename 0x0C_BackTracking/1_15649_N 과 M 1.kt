//https://www.acmicpc.net/problem/15649
package solution15649

import java.util.*


private fun backTracking(N:Int, M:Int, m:Int, order:IntArray, visited:BooleanArray, builder:StringBuilder){
    if(M == m){
        order.forEach{
            builder.append(it)
            builder.append(' ')
        }
        builder.appendLine()
        return
    }

    for (n in 1 .. N){
        if(visited[n]){
            continue
        }
        visited[n] = true
        order[m] = n
        backTracking(N, M, m + 1, order, visited, builder)
        visited[n] = false
    }
}

private fun solution(n:Int, m:Int) : String{
    val builder = StringBuilder()
    val order = IntArray(m)
    val visited = BooleanArray(n + 1)

    backTracking(n, m, 0, order, visited, builder)

    return builder.toString()
}


private fun main() = with(System.`in`.bufferedReader()){
    val tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    print(solution(n, m))
}