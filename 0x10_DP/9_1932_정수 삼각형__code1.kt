// https://www.acmicpc.net/problem/1932
package solution1932


/**
 * Array.max() 는 사용할 수 없으나 Array.maxOrNull() 은 사용가능
 * 하지만 시간이 너무 오래걸림
 */


import java.util.StringTokenizer


private fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val triangle = Array(n){
        IntArray(it + 1)
    }
    triangle[0][0] = readLine().toInt()
    for (i in 1 until n){
        val tokenizer = StringTokenizer(readLine(), " ")
        for (j in 0 .. i){
            triangle[i][j] = tokenizer.nextToken().toInt() + when(j){
                0 -> triangle[i - 1][0]
                i -> triangle[i - 1][i - 1]
                else -> triangle[i - 1][j - 1].coerceAtLeast(triangle[i - 1][j])
            }
        }
    }
    var maxValue = triangle[n - 1][0]
    for (i in 1 until n){
        maxValue = triangle[n - 1][i].coerceAtLeast(maxValue)
    }
    println(maxValue)
}
