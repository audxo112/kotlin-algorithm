// https://www.acmicpc.net/problem/1806
package solution1806

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val s = input()

    val arr = IntArray(n){ input() }

    var left = 0
    var right = 0
    var sum = arr[0]
    var len = Int.MAX_VALUE

    while(true){
        if(sum < s){
            right += 1
            if(right >= n){
                break
            }
            sum += arr[right]
        }
        else{
            len = (right - left + 1).coerceAtMost(len)
            sum -= arr[left++]
            if(left > right){
                break
            }
        }
    }

    if(len == Int.MAX_VALUE){
        len = 0
    }
    println(len)
}