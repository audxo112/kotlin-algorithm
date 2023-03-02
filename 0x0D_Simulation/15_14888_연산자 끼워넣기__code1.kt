// https://www.acmicpc.net/problem/14888
package solution14888__code1

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val num = Array(n){ input() }

    val op = IntArray(n - 1)
    var cur = 0
    // 직렬로 변경하여 visited 를 이용해서 사용여부 확인
    repeat(4){operator ->
        repeat(input()){
            op[cur++] = operator
        }
    }

    val visited = BooleanArray(n - 1)

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
        for(i in 0 until n - 1){
            if(visited[i]){
                continue
            }

            visited[i] = true
            backTracking(N + 1, operate(value, num[N + 1], op[i]))
            visited[i] = false
        }
    }

    backTracking(0, num[0])
    println(maxVal)
    println(minVal)
}