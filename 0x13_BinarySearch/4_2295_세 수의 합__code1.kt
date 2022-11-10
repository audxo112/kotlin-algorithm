// https://www.acmicpc.net/problem/2295
package solution2295__code1

import java.io.StreamTokenizer


private fun binarySearch(arr:List<Int>, num:Int) : Boolean {
    var left = 0
    var right = arr.size

    while(left < right){
        val mid = left + (right - left).div(2)
        if(num == arr[mid]){
            return true
        }
        else if(num > arr[mid]){
            left = mid + 1
        }
        else{
            right = mid
        }
    }
    return false
}


private fun solution(n:Int, arr:List<Int>) : Int{
    val nums = IntArray(n * n){
        arr[it % n] + arr[it.div(n)]
    }.sorted()

    for (big in arr.reversed()){
        for(small in arr){
            if(binarySearch(nums, big - small)){
                return big
            }
        }
    }

    return 0
}


private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val arr = IntArray(n){ input() }

    println(solution(n, arr.sorted()))
}