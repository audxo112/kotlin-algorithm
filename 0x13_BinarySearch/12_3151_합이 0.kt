// https://www.acmicpc.net/problem/3151
package solution3151

import java.io.StreamTokenizer

/*
 * 일단 수의 범위가 작기 때문에 이분 탐색이 아닌 투포인터를 이용
 */

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    // 각각 값의 개수를 카운트, 범위가 -10000 부터 이므로 10000을 더한다
    val counts = IntArray(20001)
    val arr = IntArray(input()) {
        input().also { value ->
            counts[value + 10000] += 1
        }
    }.apply { sort() }
    var count = 0L
    // 0이 3개 이상인 경우, 0을 3개 선택한 경우의 수를 더해준다
    if(counts[10000] >= 3){
        count += counts[10000].toLong() * (counts[10000] - 1) * (counts[10000] - 2) / 6
    }
    var i = 0
    while(i < arr.size - 2) {
        if(arr[i] >= 0){
            break
        }

        val c = counts[arr[i] + 10000]
        // 현재 값이 2개 이상이고, 나머지 1개를 선택했을때 0이 되는경우
        if(c > 1 && arr[i] * -2 + 10000 in counts.indices){
            val mod = counts[arr[i] * -2 + 10000]
            if(mod > 0){
                count += c.toLong() * (c - 1) / 2 * mod
            }
        }
        var left = (i + c)
        var right = arr.size - 1

        while(left < right){
            val l = counts[arr[left] + 10000]
            val r = counts[arr[right] + 10000]

            if(arr[i] + arr[left] + arr[right] == 0){
                // 왼쪽과 오른쪽의 값이 같은경우, 더이상 탐색할 필요가 없음
                if(arr[left] == arr[right]){
                    count += c.toLong() * l * (l - 1) / 2
                    break
                }
                else{
                    // 아직 더 존재 할 수 있으므로 더 탐색
                    count += c.toLong() * l * r
                    left += l
                    right -= r
                }
            }
            else if(arr[i] + arr[left] + arr[right] > 0){
                right -= r
            }
            else{
                left += l
            }
        }
        i += c
    }
    println(count)
}