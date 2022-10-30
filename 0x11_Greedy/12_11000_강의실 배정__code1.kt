// https://www.acmicpc.net/problem/11000
package solution11000

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    val map = mutableMapOf<Int, Int>()
    repeat(N){
        val start = input()
        val end = input()

        map[start] = map.getOrDefault(start, 0) + 1
        map[end] = map.getOrDefault(end, 0) - 1
    }

    val sortedMap = map.toSortedMap()
    var max = 0
    var cur = 0
    for(value in sortedMap.values){
        cur += value
        max = max.coerceAtLeast(cur)
    }
    println(max)
}