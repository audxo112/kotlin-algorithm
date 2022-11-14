// https://www.acmicpc.net/problem/1822
package solution1822__code3

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }

    val nA = input()
    val nB = input()

    val aArr = IntArray(nA){ input() }
    aArr.sort()

    val bArr = ArrayList<Int>(nB)
    repeat(nB){
        bArr.add(input())
    }
    bArr.sort()

    val sb = StringBuilder()
    var count = 0

    for(num in aArr){
        if(bArr.binarySearch(num) < 0){
            sb.append(num).append(' ')
            count += 1
        }
    }

    println(count)
    println(sb.toString())
}
