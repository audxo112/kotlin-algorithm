// https://www.acmicpc.net/problem/20366
package solution20366__code2

import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val arr = IntArray(n) { input() }.apply { sort() }

    var min = Int.MAX_VALUE

    // 모든 경우의 수를 하면 중복되는 경우가 존재한다
    // 그러므로 엘자가 선택한 눈덩이 내부만 안나가 선택하게 변경한다
    for(eljaLeft in 0 until n - 3){
        for(eljaRight in eljaLeft + 3 until n){
            val eljaHeight = arr[eljaLeft] + arr[eljaRight]
            var left = eljaLeft + 1
            var right = eljaRight - 1
            while(left < right){
                val height = arr[left] + arr[right]
                if(height < eljaHeight){
                    left += 1
                } else if(height > eljaHeight){
                    right -= 1
                } else {
                    println("0")
                    return@run
                }
                val diff = abs(eljaHeight - height)
                if(min > diff){
                    min = diff
                }
            }
        }
    }
    println(min)
}