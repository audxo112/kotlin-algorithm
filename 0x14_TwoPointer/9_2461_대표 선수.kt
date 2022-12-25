// https://www.acmicpc.net/problem/2461
package solution2461

import java.io.StreamTokenizer
import java.util.PriorityQueue

data class Data(
    val power: Int,
    val group: Int,
    val index: Int
)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val students = Array(n) { IntArray(m) { input() }.apply { sort() } }

    val maxList = PriorityQueue<Data> { data1, data2 ->
        data2.power.compareTo(data1.power)
    }
    val minList = PriorityQueue<Data> { data1, data2 ->
        data1.power.compareTo(data2.power)
    }

    for (i in 0 until n){
        maxList.offer(Data(students[i][0], i, 0))
        minList.offer(Data(students[i][0], i, 0))
    }

    var left = minList.peek()
    var right = maxList.peek()
    var min = Int.MAX_VALUE

    while (left.index < m && right.index < m) {
        if(min > right.power - left.power){
            min = right.power - left.power
        }

        val minData = minList.poll()
        if(minData.index + 1 >= m){
            break
        }
        val newData = Data(students[minData.group][minData.index + 1], minData.group, minData.index + 1)
        maxList.add(newData)
        minList.add(newData)
        left = minList.peek()
        right = maxList.peek()
    }
    println(min)
}