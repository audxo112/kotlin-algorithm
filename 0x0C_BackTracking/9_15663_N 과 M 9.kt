//https://www.acmicpc.net/problem/15663
package solution15663


import java.util.StringTokenizer


private fun permutation(N:Int, M:Int, m:Int, numArray: Array<String>, order:IntArray, visited:BooleanArray, sb:StringBuilder) {
    if(m == M){
        order.forEach {
            sb.append(numArray[it]).append(' ')
        }
        sb.append('\n')
        return
    }

    var before = ""
    for(i in 0 until N){
        if(numArray[i] == before || visited[i]){
            continue
        }
        before = numArray[i]
        visited[i] = true
        order[m] = i
        permutation(N, M, m + 1, numArray, order, visited, sb)
        visited[i] = false
    }
}


private fun solution(n:Int, m:Int, numArray:Array<String>) : String{
    val sb = StringBuilder()
    val order = IntArray(m)
    val visited = BooleanArray(n)

    permutation(n, m, 0, numArray, order, visited, sb)

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