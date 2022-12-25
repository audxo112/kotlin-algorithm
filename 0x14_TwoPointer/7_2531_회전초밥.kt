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
    // 회전 초밥이기 때문에 맨 앞의 k - 1 개를 맨뒤에 붙여준다
    for(i in n until N){
        sushiArr[i] = sushiArr[i - n]
    }

    // 먹은 초밥의 개수를 저장
    val eat = IntArray(d + 1)
    // 쿠폰이 있다면 무조건 먹기 때문에 미리 추가
    eat[c] += 1

    var left = 0
    var right = 0
    var count = 1
    var max = 1

    while(right < N){
        // 최대 k 개 까지 먹을 수 있으므로 이 경우 left 증가
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
                // k + 1 개 이상은 먹을 수 없으므로 break
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