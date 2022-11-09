// https://www.acmicpc.net/problem/10816
package solution10816

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }
    val nArr = IntArray(20_000_001)
    repeat(input()){
        nArr[input() + 10_000_000] += 1
    }
    val sb = StringBuilder()
    repeat(input()){
        sb.append(nArr[input() + 10_000_000]).append(" ")
    }
    println(sb.toString())
}

