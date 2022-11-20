// https://www.acmicpc.net/problem/2467
package solution2467__code1

import java.io.StreamTokenizer
import kotlin.math.abs

/*
 * 가지치기를 통해 투포인터 풀이와 비슷한 시간이 나옴
 */

private fun maxBinarySearch(arr:IntArray) : Int{
    var left = 0
    var right = arr.size - 1

    while(left <= right){
        val mid = (left + right) / 2
        if(arr[mid] == 0){
            return mid
        }
        if(arr[mid] > 0){
            right = mid - 1
        }
        else{
            left = mid + 1
        }
    }
    return left.coerceAtMost(arr.size - 1)
}


private fun binarySearch(arr:IntArray, min:Int, value:Int): Int{
    var left = min
    var right = arr.size - 1

    while(left <= right){
        val mid = (left + right) / 2
        if(value == arr[mid]){
            return mid
        }
        if(value > arr[mid]){
            left = mid + 1
        }
        else{
            right = mid - 1
        }
    }

    // left 가 arr.size 이상인 경우, right == arr.size - 1 이므로 가독성을 위해 arr.size - 1로 작성
    if(left >= arr.size){
        return arr.size - 1
    }
    // right 가 min 미만인 경우, left == min 이므로 가독성을 위해 min 으로 작성
    if(right < min){
        return min
    }
    // 현재 값이 -arr[i] 이므로 찾은 값을 빼준값, 0 에 더 가까운 값을 찾아서 넘긴다
    return if(abs(value - arr[left]) < abs(value - arr[right])) left else right
}


private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val arr = IntArray(input()){ input() }
    // maxBinary 는 0이거나 영보다 큰 인덱스를 찾는다
    val maxIdx = maxBinarySearch(arr)
    // 0 보다 큰 index 가 없다는 의미 이므로
    // 마지막 2개의 합이 0과 가깝다
    if(arr[maxIdx] < 0){
        println("${arr[arr.lastIndex - 1]} ${arr[arr.lastIndex]}")
    }
    // maxIdx 가 0 인 경우
    // 전부다 0보다 큰 값이므로 처음 2개의 합이 0과 가깝다
    else if(maxIdx == 0){
        println("${arr[0]} ${arr[1]}")
    }
    else{
        var minLeft = arr[0]
        var minRight = arr[arr.lastIndex]
        var minValue = Int.MAX_VALUE

        // maxIdx 를 넘어가게되면 0에서 멀어지므로 제외한다
        for(i in 0 until  maxIdx){
            // -arr[i] 와 최대한 유사한 값을 찾는다
            val idx = binarySearch(arr, i + 1, -arr[i])
            val absValue = abs(arr[i] + arr[idx])
            if(absValue < minValue){
                minLeft = arr[i]
                minRight = arr[idx]
                minValue = absValue
                if(minValue == 0){
                    break
                }
            }
        }

        println("$minLeft $minRight")
    }
}