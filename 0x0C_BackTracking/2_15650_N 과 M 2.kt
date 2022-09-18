//https://www.acmicpc.net/problem/15649
package solution15650

import java.util.*


private fun backTracking(N:Int, M:Int, n:Int, m:Int, order:IntArray, visited:BooleanArray, builder:StringBuilder){
    if(M == m){
        order.forEach{
            builder.append(it)
            builder.append(' ')
        }
        builder.appendLine()
        return
    }

    for (nn in n .. N){
        if(visited[nn]){
            continue
        }
        visited[nn] = true
        order[m] = nn
        backTracking(N, M, nn + 1, m + 1, order, visited, builder)
        visited[nn] = false
    }
}

private fun solution(n:Int, m:Int) : String{
    val builder = StringBuilder()
    val order = IntArray(m)
    val visited = BooleanArray(n + 1)

    backTracking(n, m, 1, 0, order, visited, builder)

    return builder.toString()
}


private fun main() = with(System.`in`.bufferedReader()){
    val tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    print(solution(n, m))
}