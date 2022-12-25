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

    // right 값을 구하기 위한 우선 순위 큐
    val maxList = PriorityQueue<Data> { data1, data2 ->
        data2.power.compareTo(data1.power)
    }
    // Left 값을 구하기 위한 우선 순위 큐
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

    // left 나 right 의 index 가 m 과 같거나 커지면 종료한다
    while (left.index < m && right.index < m) {
        if(min > right.power - left.power){
            min = right.power - left.power
        }

        // left을 poll 한다면 right 값은 변할 이유가 없다
        val minData = minList.poll()
        if(minData.index + 1 >= m){
            break
        }
        // 새로운데이터가 추가 되면 left, right 는 둘다 변동 될 수 있다
        val newData = Data(students[minData.group][minData.index + 1], minData.group, minData.index + 1)
        maxList.add(newData)
        minList.add(newData)
        left = minList.peek()
        right = maxList.peek()
    }
    println(min)
}