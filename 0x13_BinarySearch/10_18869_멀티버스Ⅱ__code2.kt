// https://www.acmicpc.net/problem/18869
package solution18869__code2

/*
 * Lower, Upper를 이용하여 Index 를 계산 하는 방법이 시간 초과가 걸려서
 * 좀 더 깍아봐야할듯
 * 주영님 풀이
 */

import java.io.StreamTokenizer

private fun binarySearch(arr:List<Int>, value:Int) : Int{
    var left = 0
    var right = arr.size - 1

    while(left <= right){
        val mid = (left + right) / 2
        if(arr[mid] == value){
            return mid
        }

        if(arr[mid] < value){
            left = mid + 1
        }
        else{
            right = mid - 1
        }
    }
    return left
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }

    val m = input()
    val n = input()
    val list = Array(m){
        IntArray(n){ input() }.also{arr ->
            val sorted = arr.toSortedSet().toList()

            for(i in arr.indices){
                arr[i] = binarySearch(sorted, arr[i])
            }
        }
    }

    var count = 0
    for(i in list.indices){
        for(j in i + 1 until m){
            if(list[i].contentEquals(list[j])){
                count += 1
            }
        }
    }
    println(count)
}