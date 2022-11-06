//https://www.acmicpc.net/problem/11501
package solution11501

/**
 *
 */

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val T = input()
    val sb = StringBuilder()
    val prices = IntArray(10001)

    repeat(T) {
        val N = input()
        val arr = IntArray(N){
            val value = input()
            prices[value] += 1
            value
        }

        var mi = 10000
        var sum = 0L
        for(i in 0 until N){
            while(prices[mi] == 0){
                mi -= 1
            }
            if(arr[i] < mi){
                sum += mi - arr[i]
            }
            prices[arr[i]] -= 1
        }
        sb.appendLine(sum)
    }
    println(sb.toString())
}