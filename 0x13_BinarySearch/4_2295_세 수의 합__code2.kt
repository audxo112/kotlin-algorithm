// https://www.acmicpc.net/problem/2295
package solution2295__code2

import java.io.StreamTokenizer


private fun solution(arr:List<Int>) : Int{
    val set = hashSetOf<Int>()
    for (i in arr.indices){
        for(j in i until arr.size){
            set.add(arr[i] + arr[j])
        }
    }

    for (big in arr.reversed()){
        for(small in arr){
            if(big - small in set){
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

    println(solution(arr.sorted()))
}