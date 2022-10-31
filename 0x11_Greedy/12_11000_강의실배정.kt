package `kotlin-algorithm`.`0x11_Greedy`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

data class Lecture(val start: Int, val end: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val arr = Array(n) {
        val st = StringTokenizer(br.readLine())
        Lecture(st.nextToken().toInt(), st.nextToken().toInt())
    }

    arr.sortWith(compareBy({ it.start }, { it.end }))

    val que = PriorityQueue<Int>()
    que.offer(arr[0].end)

    for (i in 1 until arr.size) {
        if (que.peek() <= arr[i].start) {
            que.poll()
        }
        que.offer(arr[i].end)
    }

    println(que.size)
}