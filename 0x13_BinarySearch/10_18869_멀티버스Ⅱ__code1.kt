// https://www.acmicpc.net/problem/18869
package solution18869__code1

import java.io.StreamTokenizer

private fun searchLower(arr:List<Int>, value:Int) : Int{
    var left = 0
    var right = arr.size - 1

    while(left <= right){
        val mid = (left + right) / 2
        if(arr[mid] < value){
            left = mid + 1
        }
        else{
            right = mid - 1
        }
    }
    return left
}

private fun searchUpper(arr:List<Int>, value:Int) : Int{
    var left = 0
    var right = arr.size - 1

    while(left <= right){
        val mid = (left + right) / 2
        if(arr[mid] <= value){
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
        val map = hashMapOf<Int, Int>()
        var count = 0
        IntArray(n){ input() }.also{arr ->
            val sorted = arr.sorted()
            for(i in arr.indices){
                val step = map[arr[i]]
                if(step != null){
                    arr[i] = step
                    continue
                }

                val lower = searchLower(sorted, arr[i])
                val upper = searchUpper(sorted, arr[i])
                val value = lower - count

                map[arr[i]] = value
                arr[i] = value
                count += upper - lower - 1
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