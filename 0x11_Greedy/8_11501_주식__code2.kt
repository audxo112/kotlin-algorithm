//https://www.acmicpc.net/problem/11501
package solution11501

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val T = input()
    val sb = StringBuilder()

    repeat(T) {
        val N = input()
        val scores = IntArray(N){ input() }

        var sum = 0L
        var max = 0
        for (i in scores.indices.reversed()){
            if(scores[i] > max){
                max = scores[i]
            }
            else{
                sum += (max - scores[i])
            }
        }
        sb.appendLine(sum)
    }
    println(sb.toString())
}