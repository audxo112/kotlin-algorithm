//https://www.acmicpc.net/problem/11399
package solution11399

import java.util.StringTokenizer


private fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val times = IntArray(1001)
    val tokenizer = StringTokenizer(readLine(), " ")

    repeat(N){
        times[tokenizer.nextToken().toInt()] += 1
    }

    var current = 0
    var sum = 0
    var i = 0
    var n = 0

    while(n < N){
        i += 1
        if(times[i] == 0){
            continue
        }

        sum += current * times[i] + i * (times[i] * (times[i] + 1)) / 2
        current += i * times[i]
        n += times[i]
    }
    println(sum)
}