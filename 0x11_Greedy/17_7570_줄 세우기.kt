// https://www.acmicpc.net/problem/7570
package solution7570

/**
 * 숫자의 순서를 기준으로 배열을 만들고 배열의 값이 점점 증가한다면 숫자가 커지는 순서가 됨
 */

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()

    val location = IntArray(n + 1)
    repeat(n){
        location[input()] = it + 1
    }

    var maxCount = 1
    var count = 1
    for (i in 1 until n){
        if(location[i + 1] > location[i]){
            count += 1
            maxCount = count.coerceAtLeast(maxCount)
        }
        else{
            count = 1
        }
    }

    println(n - maxCount)
}
