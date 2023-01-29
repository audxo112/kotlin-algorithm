// https://www.acmicpc.net/problem/13335
package solution13335

import java.io.StreamTokenizer

// 각 트럭의 무게와 언제 다리에 올라갔는지 저장한다
private class Truck(
    val weight: Int,
    var time: Int = 0
)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val w = input()
    val L = input()
    val trucks = Array(n){ Truck(input()) }

    var time = 1
    var totalWeight = 0

    // 다리를 건너간 트럭 Index
    var completeIndex = 0
    // 다리를 건너고 있는 트럭 Index
    var runningIndex = 0

    while(completeIndex < trucks.size){
        // 트럭이 다리에 올라가 있는지 여부
        if(runningIndex < trucks.size &&
            runningIndex - completeIndex < w &&
            totalWeight + trucks[runningIndex].weight <= L){
            totalWeight += trucks[runningIndex].weight
            trucks[runningIndex].time = time
            runningIndex += 1
            time += 1
        } else{
            // 다리 + 트럭이 올라간 시간이 해당 트럭이 전부 건넜을때 시간이다
            // 만약 트럭을 적재하면서 이미 지나갔다면 시간은 조절하지 않는다
            if(time - trucks[completeIndex].time < w){
                time = w + trucks[completeIndex].time
            }
            totalWeight -= trucks[completeIndex].weight
            completeIndex += 1
        }
    }
    println(time)
}