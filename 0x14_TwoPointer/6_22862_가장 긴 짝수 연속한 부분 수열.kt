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

    // 연속된 짝수의 갯수를 찾으면 되기 때문에 짝수 여부만 저장
    val oddArr = BooleanArray(n){ input() % 2 == 0 }

    var left = 0
    var right = 0
    var odd = 0
    var even = 0
    var max = 0

    while(right < n){
        // s 개수 까지 홀수가 존재가능 하므로 s - even 이 0 보다 작아지면 left 이용하여 홀수 제거
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
            if(max < odd){
                max = odd
            }
        } else{
            even += 1
        }
        right += 1
    }
    println(max)
}