// https://www.acmicpc.net/problem/2283
package solution2283

import java.io.StreamTokenizer

// 왜 left 와 right 를 0 부터 시작해야 36% 에서 통과하는지 이해는 하지 못했다
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val k = input()

    val arr = IntArray(1_000_002)
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    for(i in 0 until n){
        val a = input()
        val b = input()

        // 누산을 진행할 최소값
        if(min > a){
            min = a
        }
        // 누산을 진행할 최대값
        if(max < b){
            max = b
        }
        arr[a + 1] += 1
        arr[b + 1] -= 1
    }

    // 각각 구간에 몇개의 선이 존재하는지 계산
    for(i in min + 1 .. max){
        arr[i] += arr[i - 1]
    }

    var left = 0
    var right = 0
    var sum = 0
    while(right < max + 1){
        if(sum > k){
            left += 1
            sum -= arr[left]
        } else if(sum < k){
            right += 1
            sum += arr[right]
        } else{
            break
        }
    }
    if(right >= max + 1){
        println("0 0")
    } else{
        println("$left $right")
    }
}