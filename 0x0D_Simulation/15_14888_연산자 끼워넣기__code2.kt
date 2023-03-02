// https://www.acmicpc.net/problem/14888
package solution14888__code2

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val num = Array(n){ input() }

    // visited 를 사용하는게 오히려더 손해
    // 각각 개수를 이용해서 0이 될때까지 사용하는 방식으로 변경
    val op = IntArray(4){
        input()
    }

    var minVal = Int.MAX_VALUE
    var maxVal = Int.MIN_VALUE

    fun operate(num1: Int, num2: Int, operator: Int): Int{
        return when(operator){
            0 -> num1 + num2
            1 -> num1 - num2
            2 -> num1 * num2
            else -> num1 / num2
        }
    }

    fun backTracking(N: Int, value: Int){
        if(N == n - 1){
            minVal = value.coerceAtMost(minVal)
            maxVal = value.coerceAtLeast(maxVal)

            return
        }
        repeat(4){ i ->
            if(op[i] <= 0){
                return@repeat
            }
            op[i] -= 1
            backTracking(N + 1, operate(value, num[N + 1], i))
            op[i] += 1
        }
    }

    backTracking(0, num[0])
    println(maxVal)
    println(minVal)
}