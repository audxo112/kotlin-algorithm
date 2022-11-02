package `kotlin-algorithm`.`0x11_Greedy`

import java.util.*

fun main() {
    val n = readln().toInt()

    val array = Array(n){
        val st = StringTokenizer(readln())
        Assignment(st.nextToken().toInt(),st.nextToken().toInt())
    }

    array.sortWith (
        compareBy({ it.s },{it.t})
    )

    val room = PriorityQueue<Int>()
    room.add(array[0].t)

    for (i in 1 until array.size) {
        if(array[i].s < room.peek()) {
            room.add((array[i].t))
        } else {
            room.poll()
            room.add(array[i].t)
        }
    }
    println(room.size)
}

data class Assignment(
    val s: Int,
    var t: Int
    )