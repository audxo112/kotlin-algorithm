//https://www.acmicpc.net/problem/11501
package solution11501

import java.util.StringTokenizer

private fun main() = with(System.`in`.bufferedReader()){
    val T = readLine().toInt()

    repeat(T) {
        val N = readLine().toInt()
        val tokenizer = StringTokenizer(readLine(), " ")
        val scores = IntArray(N){
            tokenizer.nextToken().toInt()
        }

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
        println(sum)
    }
}