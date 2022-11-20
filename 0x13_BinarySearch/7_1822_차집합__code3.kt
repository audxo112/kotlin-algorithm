// https://www.acmicpc.net/problem/1822
package solution1822__code3

import java.io.StreamTokenizer

/*
 * ArrayList의 binarySearch를 사용
 *
 * 아마 인덱스를 찾기위해 끝까지 Search를 해서 더 느린것으로 판단
 */
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
