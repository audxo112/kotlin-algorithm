//https://www.acmicpc.net/problem/1182
package solution1182

import java.util.StringTokenizer


private fun combination(N:Int, M:Int, S:Int, n:Int, m:Int, numArr: IntArray, sum:Int) : Int{
    if(M == m){
        return if(S == sum) 1 else 0
    }

    var count = 0
    for(i in n until N){
        count += combination(N, M, S, i + 1, m + 1, numArr, sum + numArr[i])
    }
    return count
}


private fun solution(n:Int, s:Int, numArr:IntArray) : Int{
    var count = 0
    for (i in 1 .. n){
        count += combination(n, i, s, 0, 0, numArr, 0)
    }

    return count
}


private fun main() = with(System.`in`.bufferedReader()){
    var tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val s = tokenizer.nextToken().toInt()
    tokenizer = StringTokenizer(readLine(), " ")
    val numArr = IntArray(n){
        tokenizer.nextToken().toInt()
    }

    println(solution(n, s, numArr))
}