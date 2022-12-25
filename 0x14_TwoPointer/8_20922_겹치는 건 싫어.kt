// https://www.acmicpc.net/problem/20922
package solution20922

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val k = input()

    val arr = IntArray(n) { input() }
    val num = IntArray(100_001)

    var left = 0
    var right = 0
    var max = 0

    while(right < n){
        while(num[arr[right]] + 1 > k){
            num[arr[left]] -= 1
            left += 1
        }
        val count = right - left + 1
        num[arr[right]] += 1
        if(max < count){
            max = count
        }
        right += 1
    }
    println(max)
}