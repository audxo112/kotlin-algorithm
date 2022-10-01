//https://www.acmicpc.net/problem/9663
package solution9663

/**
 * Visited 가 크지 않기 때문에 bit 연산자를 사용하는 방법
 */

import java.util.StringTokenizer


private fun permutation(N:Int, y:Int, col:Int, plus:Int, minus:Int) : Int{
    if(N == y){
        return 1
    }

    var count = 0
    for(i in 0 until N){
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
    return permutation(n, 0, 0, 0, 0)
}


private fun main() = with(System.`in`.bufferedReader()){
    val tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    println(solution(n))
}