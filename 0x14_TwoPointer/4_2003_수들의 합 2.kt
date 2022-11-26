// https://www.acmicpc.net/problem/2003
package solution2003

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val arr = IntArray(n) { input() }

    var left = 0
    var right = 0
    var sum = 0
    var count = 0

    while(left < n){
        if(right < n && sum < m){
            sum += arr[right++]
        } else{
            if(sum == m){
                count ++
            }
            sum -= arr[left++]
        }
    }

    println(count)
}