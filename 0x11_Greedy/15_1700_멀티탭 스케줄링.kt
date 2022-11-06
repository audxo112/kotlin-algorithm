// https://www.acmicpc.net/problem/1700
package solution1700

/**
 * 각각 종류에 따른 LinkedList를 이용하여 언제 쓰이는지 확인
 * 1. 지금 들어온 기기가 이미 멀티탭에 꽂혀있는지 확인
 * 2. 빈공간이 있는지 확인
 * 3. 이미 꽂혀있는 것들중 앞으로 쓰이지 않을 기기와 교체 (교체수 +)
 * 4. 꾳혀있는 것중 가장 나중에 쓸 것을 교체 (교체수 +)
 */

import java.io.StreamTokenizer
import java.util.LinkedList

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val multi = IntArray(N)

    val K = input()
    val waitOrders = Array(K + 1) { LinkedList<Int>() }
    val orders = IntArray(K) { i ->
        input().also { value ->
            waitOrders[value].add(i)
        }
    }

    var zero = 0
    var count = 0
    repeat(K){orderIndex ->
        val order = orders[orderIndex]
        for (i in 0 until zero){
            if(multi[i] == order){
                waitOrders[order].poll()
                return@repeat
            }
        }

        if(zero < N){
            waitOrders[order].poll()
            multi[zero] = order
            zero += 1
            return@repeat
        }

        for (i in 0 until N){
            if(waitOrders[multi[i]].isEmpty()){
                waitOrders[order].poll()
                multi[i] = order
                count += 1
                return@repeat
            }
        }

        var lastWaitOrder = waitOrders[multi[0]].peek()
        var minIndex = 0
        for(i in 1 until N){
            if(waitOrders[multi[i]].peek() > lastWaitOrder){
                lastWaitOrder = waitOrders[multi[i]].peek()
                minIndex = i
            }
        }
        waitOrders[order].poll()
        multi[minIndex] = order
        count += 1
    }
    println(count)
}