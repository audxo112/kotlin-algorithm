// https://www.acmicpc.net/problem/1932
package solution1932


/**
 * 삼각형 만큼 배열을 만드는게 아닌 2줄의 배열만 만들어서 사용
 */


import java.util.StringTokenizer


private fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val triangle = Array(2){
        IntArray(n)
    }
    triangle[0][0] = readLine().toInt()
    var cur = 0
    var before:Int
    for (i in 1 until n){
        cur = 1 - cur
        before = 1 - cur

        val tokenizer = StringTokenizer(readLine(), " ")
        for (j in 0 .. i){
            triangle[cur][j] = tokenizer.nextToken().toInt() + when(j){
                0 -> triangle[before][0]
                i -> triangle[before][i - 1]
                else -> triangle[before][j - 1].coerceAtLeast(triangle[before][j])
            }
        }
    }

    var maxValue = triangle[cur][0]
    for (i in 1 until n){
        maxValue = triangle[cur][i].coerceAtLeast(maxValue)
    }
    println(maxValue)
}