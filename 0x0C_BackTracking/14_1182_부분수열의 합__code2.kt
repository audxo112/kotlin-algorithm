//https://www.acmicpc.net/problem/1182
package solution1182

/**
 * count 변수를 외부에 두고 조건에 맞을때만 더하면 더 빠를듯
 */

import java.util.StringTokenizer


private fun combination(N:Int, S:Int, n:Int, numArr: IntArray, sum:Int) : Int{
    if(N == n){
        return if(S == sum) 1 else 0
    }

    var count = 0
    count += combination(N, S, n + 1, numArr, sum)
    count += combination(N, S, n + 1, numArr, sum + numArr[n])

    return count
}


private fun solution(n:Int, s:Int, numArr:IntArray) : Int{
    return if(s == 0){
        combination(n, s, 0, numArr, 0) - 1
    }
    else{
        combination(n, s, 0, numArr, 0)
    }
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