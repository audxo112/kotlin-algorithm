// https://www.acmicpc.net/problem/8980
package solution8980__code2

/**
 * 택배의 남은 용량으로 계산 하는 방법
 */

import java.io.StreamTokenizer

private class Box(val start:Int, val end:Int, val amount:Int)

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val C = input()
    val M = input()

    val deliveries = Array(M) {
        Box(input(), input(), input())
    }
    deliveries.sortWith{ box1, box2 ->
        if(box1.end == box2.end) box1.start - box2.start else box1.end - box2.end
    }

    val truck = IntArray(N + 1){ C }
    var total = 0

    for(delivery in deliveries){
        var amount = delivery.amount
        for(i in delivery.start until delivery.end){
            amount = amount.coerceAtMost(truck[i])
        }
        for(i in delivery.start until delivery.end) {
            truck[i] -= amount
        }
        total += amount
    }
    println(total)
}