// https://www.acmicpc.net/problem/14888
package solution14888__code2

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val nn = n - 1
    val num = Array(n){ input() }

    var minVal = Int.MAX_VALUE
    var maxVal = Int.MIN_VALUE

    fun backTracking(N: Int, value: Int, add: Int, sub: Int, mul: Int, div: Int){
        if(N == nn){
            minVal = value.coerceAtMost(minVal)
            maxVal = value.coerceAtLeast(maxVal)
            return
        }
        if(add > 0){
            backTracking(N + 1, value + num[N + 1], add - 1, sub, mul, div)
        }
        if(sub > 0){
            backTracking(N + 1, value - num[N + 1], add, sub - 1, mul, div)
        }
        if(mul > 0){
            backTracking(N + 1, value * num[N + 1], add, sub, mul - 1, div)
        }
        if(div > 0){
            backTracking(N + 1, value / num[N + 1], add, sub, mul, div - 1)
        }
    }

    backTracking(0, num[0], input(), input(), input(), input())

    println(maxVal)
    println(minVal)
}