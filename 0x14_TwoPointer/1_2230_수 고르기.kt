// https://www.acmicpc.net/problem/2230
package solution2230

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    val arr = IntArray(n) { input() }.apply { sort() }

    var left = 0
    var right = 0
    var min = Int.MAX_VALUE

    while(left < n && right < n){
        val value = arr[right] - arr[left]
        if(value < m){
            right++
        }
        else{
            if(min > value){
                min = value
                if(min == m){
                    break
                }
            }
            left++
        }
    }
    println(min)
}
