//https://www.acmicpc.net/problem/9663
package solution9663

/**
 * 행의 크기가 짝수 인경우,
 * 1행만 절반을 수행하고 결과 값을 * 2를 한다
 *
 * 홀수 인경우,
 * 1행만 절반을 수행하고 결과를 * 2 진행후
 * 1행에서 가운데를 선택하고 진행한 경우를 더한다
 */

import java.util.StringTokenizer


private fun permutation(N:Int, y:Int, col:Int, plus:Int, minus:Int) : Int{
    if(N == y){
        return 1
    }

    var count = 0
    for(i in 0 until if(y > 0) N else N.div(2)){
        val x = 1 shl i
        val p = 1 shl i + y
        val m = 1 shl y - i + N - 1

        if(col and x == x || plus and p == p || minus and m == m){
            continue
        }

        count += permutation(N, y + 1, col or x, plus or p, minus or m)
    }
    return count
}


private fun solution(n:Int) : Int{
    val answer = permutation(n, 0, 0, 0, 0) * 2
    return if(n % 2 == 0){
        answer
    }
    else {
        val col = n.div(2)
        answer + permutation(n, 1, 1 shl col, 1 shl col, 1 shl n - col - 1)
    }
}


private fun main() = with(System.`in`.bufferedReader()){
    val tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    println(solution(n))
}