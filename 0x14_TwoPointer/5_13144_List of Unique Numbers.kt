// https://www.acmicpc.net/problem/13144
package solution13144

import java.io.StreamTokenizer

// 범위 안에 같은 수가 없는 수의 조합을 구해보자

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val arr = IntArray(n) {input()}
    // 존재할 수 있는 수 만큼 미리 배열을 만들어 존재를 확인
    val visited = BooleanArray(100001)
    var count = 0L

    var right = 0
    for(left in 0 until n){
        // 존재하는 수가 나올때까지 진행
        while(right < n && !visited[arr[right]]){
            visited[arr[right++]] = true
        }
        // 만약 끝까지 진행했다면 지금 존재하는 값의 조합을 구함
        if(right >= n){
            val len = right - left
            count += len.toLong() * (len + 1) / 2
            break
        }
        // left 가 포함된 경우의 수를 더함
        count += right - left
        visited[arr[left]] = false
    }
    println(count)
}