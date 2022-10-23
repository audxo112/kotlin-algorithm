//https://www.acmicpc.net/problem/11047
package solution11047

import java.util.StringTokenizer


private fun solution(N:Int, K:Int, coins:IntArray) : Int{
    var k = K
    var count = 0
    for (i in N - 1 downTo 0){
        if(k >= coins[i]){
            count += k / coins[i]
            k %= coins[i]

            if(k == 0){
                break
            }
        }
    }

    return count
}


private fun main() = with(System.`in`.bufferedReader()){
    val tokenizer = StringTokenizer(readLine(), " ")
    val N = tokenizer.nextToken().toInt()
    val K = tokenizer.nextToken().toInt()

    val coins = IntArray(N){ readLine().toInt() }

    println(solution(N, K, coins))
}