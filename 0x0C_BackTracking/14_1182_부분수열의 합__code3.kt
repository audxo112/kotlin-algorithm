//https://www.acmicpc.net/problem/1182
package solution1182

/**
 * 분할 정복
 *
 * 반으로 나눠서 각각 선택하는 경우
 * left * right = all
 *
 * 이 점을 생각해서 각각의 경우의 수를 만들고 계산해준다
 */

import java.util.StringTokenizer


private fun combination(N:Int, S:Int, n:Int, numArr: IntArray, sum:Int, map:HashMap<Int, Int>){
    if(N == n){
        map[sum] = map[sum]?.plus(1) ?: 1
        return
    }

    combination(N, S, n + 1, numArr, sum, map)
    combination(N, S, n + 1, numArr, sum + numArr[n], map)
}


private fun solution(n:Int, s:Int, numArr:IntArray) : Int{
    val left = hashMapOf<Int, Int>()
    val right = hashMapOf<Int, Int>()
    val mid = n.div(2)

    left[0] = -1
    right[0] = -1

    combination(mid, s, 0, numArr, 0, left)
    combination(n, s, mid, numArr, 0, right)

    var count = left.getOrDefault(s, 0) + right.getOrDefault(s, 0)
    for (key in left.keys){
        count += left.getOrDefault(key, 0) * right.getOrDefault(s - key, 0)
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