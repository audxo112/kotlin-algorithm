//https://www.acmicpc.net/problem/11501
package solution11501

import java.util.StringTokenizer

private fun main() = with(System.`in`.bufferedReader()){
    val T = readLine().toInt()
    val prices = IntArray(10001)

    repeat(T) {
        val N = readLine().toInt()
        val tokenizer = StringTokenizer(readLine(), " ")
        val arr = IntArray(N){
            val value = tokenizer.nextToken().toInt()
            prices[value] += 1
            value
        }

        var mi = 10000
        var sum = 0L
        for(i in 0 until N){
            while(prices[mi] == 0){
                mi -= 1
            }
            if(arr[i] < mi){
                sum += mi - arr[i]
            }
            prices[arr[i]] -= 1
        }
        println(sum)
    }
}