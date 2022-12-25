// https://www.acmicpc.net/problem/20366
package solution20366__code1

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

    // 엘자가 눈덩이를 선택할 수 있는 모든 경우의 수를 선택한다
    for(eljaLeft in 0 until n - 1){
        for(eljaRight in eljaLeft + 1 until n){
            val eljaHeight = arr[eljaLeft] + arr[eljaRight]
            var left = 0
            var right = n - 1
            while(left < right){
                // 엘자의 눈과 겹치지 않는 것을 선택한다
                if(left == eljaLeft || left == eljaRight){
                    left += 1
                    continue
                }
                if(right == eljaLeft || right == eljaRight){
                    right -= 1
                    continue
                }

                val height = arr[left] + arr[right]
                if(height < eljaHeight){
                    left += 1
                } else if(height > eljaHeight){
                    right -= 1
                } else {
                    // 0 인 경우 더 이상 검색할 이유가 없다
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