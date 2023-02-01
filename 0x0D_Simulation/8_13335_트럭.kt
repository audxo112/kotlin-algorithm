package solve_13335

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.Queue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val w = input()
    val l = input()

    val truck: Queue<Int> = LinkedList()
    val bridge: Queue<Int> = LinkedList()

    repeat(n) {
        truck.add(input())
    }
    repeat(w) {
        bridge.add(0)
    }

    var truckSum = 0
    var answer = w
    while(truck.isNotEmpty()) {
        val newTruck = truck.first()
        val oldTruck = bridge.poll()
        truckSum = truckSum + newTruck - oldTruck
        if(truckSum <= l) {
            bridge.add(truck.poll())
        } else {
            truckSum -= newTruck
            bridge.add(0)
        }
        answer +=1
    }

    println(answer)
}