//https://www.acmicpc.net/problem/9663
package solution9663

import java.util.StringTokenizer


private fun permutation(N:Int, y:Int, col:BooleanArray, plus:BooleanArray, minus:BooleanArray) : Int{
    if(N == y){
        return 1
    }

    var count = 0
    for(x in 0 until N){
        if(col[x] || plus[y + x] || minus[y - x + N - 1]){
            continue
        }

        col[x] = true
        plus[y + x] = true
        minus[y - x + N - 1] = true
        count += permutation(N, y + 1, col, plus, minus)
        col[x] = false
        plus[y + x] = false
        minus[y - x + N - 1] = false
    }
    return count
}


private fun solution(n:Int) : Int{
    val col = BooleanArray(n)
    val plus = BooleanArray(2 * n + 1)
    val minus = BooleanArray(2 * n + 1)

    return permutation(n, 0, col, plus, minus)
}


private fun main() = with(System.`in`.bufferedReader()){
    val tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    println(solution(n))
}