// https://www.acmicpc.net/problem/2531
package solution2531

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val d = input()
    val k = input()
    val c = input()
    val N = n + k - 1

    val sushiArr = IntArray(N)
    for(i in 0 until n){
        sushiArr[i] = input()
    }
    for(i in n until N){
        sushiArr[i] = sushiArr[i - n]
    }

    val eat = IntArray(d + 1)
    eat[c] += 1

    var left = 0
    var right = 0
    var count = 1
    var max = 1

    while(right < N){
        if(right - left >= k){
            eat[sushiArr[left]] -= 1
            if(eat[sushiArr[left]] == 0){
                count -= 1
            }
            left += 1
        }
        if(eat[sushiArr[right]] == 0){
            count += 1
            if(max < count){
                max = count
                if(max == k + 1){
                    break
                }
            }
        }
        eat[sushiArr[right]] += 1
        right += 1
    }
    println(max)
}