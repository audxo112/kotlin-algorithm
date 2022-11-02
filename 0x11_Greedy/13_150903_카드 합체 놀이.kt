package `kotlin-algorithm`.`0x11_Greedy`

import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() {
    var st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    st = StringTokenizer(readln())
    val queue = PriorityQueue<Int>()

    while (st.hasMoreTokens()){
        queue.add(st.nextToken().toInt())
    }
    for (i in 0 until m){
        val a = queue.poll()
        val b = queue.poll()
        queue.add(a+b)
        queue.add(a+b)
    }
    println(queue.sum())
}
