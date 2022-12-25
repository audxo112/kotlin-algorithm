// https://www.acmicpc.net/problem/22862
package solution22862

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val s = input()

    val oddArr = BooleanArray(n){ input() % 2 == 0 }

    var left = 0
    var right = 0
    var odd = 0
    var even = 0
    var max = 0

    while(right < n){
        while(s - even < 0){
            if(oddArr[left]){
                odd -= 1
            } else{
                even -= 1
            }
            left += 1
        }
        if(oddArr[right]){
            odd += 1
        } else{
            even += 1
        }
        if(max < odd){
            max = odd
        }
        right += 1
    }
    println(max)
}