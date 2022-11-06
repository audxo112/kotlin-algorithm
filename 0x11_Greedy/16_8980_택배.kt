// https://www.acmicpc.net/problem/8980
package solution8980

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

    val truck = IntArray(N + 1)
    var total = 0

    for(delivery in deliveries){
        var boxCnt = 0
        for(i in delivery.start until delivery.end){
            boxCnt = boxCnt.coerceAtLeast(truck[i])
        }
        val amount = delivery.amount.coerceAtMost(C - boxCnt)
        total += amount
        for(i in delivery.start until delivery.end)
            truck[i] += amount
    }
    println(total)
}