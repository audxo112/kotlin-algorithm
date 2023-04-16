// https://www.acmicpc.net/problem/2606
package solution2606

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val c = input()
    val v = input()

    val computers = IntArray(c + 1){ it }
    fun find(x: Int): Int{
        if(computers[x] == x){
            return x
        }
        computers[x] = find(computers[x])
        return computers[x]
    }

    fun merge(x: Int, y: Int){
        val fx = find(x)
        val fy = find(y)
        if(fx == fy){
            return
        }
        computers[fx] = fy
    }

    repeat(v){
        merge(input(), input())
    }
    var count = 0
    val first = find(1)

    for(i in 2 .. c){
        if(first == find(i)){
            count += 1
        }
    }

    println(count)
}