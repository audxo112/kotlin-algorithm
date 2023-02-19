// https://www.acmicpc.net/problem/13335

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val w = st.nextToken().toInt()
    val l = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val trucks = IntArray(n) {
        st.nextToken().toInt()
    }

    val que: Queue<Truck> = LinkedList() // 다리
    que.add(Truck(trucks[0], 1))
    var time = 1
    var sum = trucks[0]
    var p = 1 // 올라가야할 트럭의 인덱스

    // p가 끝이고 큐에 트럭이 없을 때까지
    while (que.isNotEmpty() || p != trucks.size) {
        time++
        // 트럭 빼기
        if (que.peek().start + w == time) {
            val truck = que.poll()
            sum -= truck.weight
        }
        // 트럭 올리기
        if (p < trucks.size && que.size < w && sum + trucks[p] <= l) {
            que.add(Truck(trucks[p], time))
            sum += trucks[p]
            p++
        }
    }

    println(time)
}

data class Truck(val weight: Int, val start: Int)