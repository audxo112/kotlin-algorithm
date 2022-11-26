// https://www.acmicpc.net/problem/13144
package solution13144

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val arr = IntArray(n) {input()}
    val visited = BooleanArray(100001)
    var count = 0L

    var right = 0
    for(left in 0 until n){
        while(right + 1 <= n && !visited[arr[right]]){
            visited[arr[right++]] = true
        }
        if(right >= n){
            val len = right - left
            count += len.toLong() * (len + 1) / 2
            break
        }
        count += right - left
        visited[arr[left]] = false
    }
    println(count)
}