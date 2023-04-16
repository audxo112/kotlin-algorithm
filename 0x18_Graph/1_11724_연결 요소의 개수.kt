// https://www.acmicpc.net/problem/11724
package solution11724

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    val lines = IntArray(n + 1) {it}
    val visited = BooleanArray(n + 1)

    fun find(value: Int): Int{
        if(lines[value] == value){
            return value
        }
        lines[value] = find(lines[value])
        return lines[value]
    }

    fun merge(x: Int, y: Int){
        val fx = find(x)
        val fy = find(y)
        if(fx == fy){
            return
        }
        lines[fy] = fx
    }

    repeat(m){
        merge(input(), input())
    }

    var count = 0
    for(i in 1 .. n){
        if(visited[find(lines[i])]){
            continue
        }
        visited[find(lines[i])] = true
        count += 1
    }

    println(count)
}