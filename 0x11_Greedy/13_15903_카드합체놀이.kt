package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val que = PriorityQueue<Long>()
    repeat(n) {
        que.add(st.nextToken().toLong())
    }

    repeat(m) {
        val sum = que.poll() + que.poll()
        que.offer(sum)
        que.offer(sum)
    }

    println(que.sum())
}