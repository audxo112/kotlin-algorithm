package `kotlin-algorithm`.`0x11_Greedy`

import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()
    val lineList = PriorityQueue<Line>(n)

    for (i in 0 until n){
        val st = StringTokenizer(readln())
        val point1 = st.nextToken().toInt()
        val point2 = st.nextToken().toInt()
        if(point1 < point2){
            lineList.add(Line(point1,point2))
        } else {
            lineList.add(Line(point2,point1))
        }
    }

    var nowLine = lineList.poll()
    var answer = 0
    while(lineList.size > 0){
        val line = lineList.poll()
        if(nowLine.start <= line.start && nowLine.end >= line.start){
            if(nowLine.end <= line.end){
                nowLine = Line(nowLine.start, line.end)
            }
        } else if(line.end >= nowLine.start && line.end <= nowLine.end){
            if(line.start <= nowLine.start){
                nowLine = Line(line.start, nowLine.end)
            }
        } else if (line.start <= nowLine.start && line.end >= nowLine.start){
            nowLine = line
        }
        else{
            answer += nowLine.end - nowLine.start
            nowLine = line
        }
    }
    answer += nowLine.end - nowLine.start

    println(answer)
}

data class Line(val start: Int, val end: Int) : Comparable<Line> {
    override fun compareTo(other: Line): Int =
        compareValuesBy(this,other,{it.start},{it.end})
}
