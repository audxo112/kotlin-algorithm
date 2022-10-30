//https://www.acmicpc.net/problem/2217
package solution2217

/**
 * 아무래도 10000개의 배열을 무조건 순회하는 것보다
 * Heap을 이용하여 계속 최대 값으로 탐색하는 방법을 생각
 *
 * 생각을 해보니 매번 가장 높은 수를 찾기 위해 정렬을 하게 되니 손해 ..
 */

import java.util.PriorityQueue

private fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val maxHeap = PriorityQueue<Int>{o1, o2 -> o2 - o1 }
    val lopes = IntArray(10001)
    repeat(N){
        val lope = readLine().toInt()
        if(lopes[lope] == 0){
            maxHeap.offer(lope)
        }
        lopes[lope] += 1
    }

    var max = 0
    var count = 0
    while (maxHeap.isNotEmpty()){
        val cur = maxHeap.poll()
        count += lopes[cur]
        max = max.coerceAtLeast(cur * count)
    }
    println(max)
}