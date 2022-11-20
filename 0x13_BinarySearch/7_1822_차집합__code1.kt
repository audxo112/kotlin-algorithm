// https://www.acmicpc.net/problem/1822
package solution1822

import java.io.StreamTokenizer
import java.util.PriorityQueue

/*
 * 증가 하는 순서로 출력이기 때문에
 * A배열과 B배역을 둘다 정렬을 해야한다
 *
 * A배열에 PriorityQueue 를 적용
 * B 에 존재 하지 않는 경우만 추가한다
 */
private fun binarySearch(arr:IntArray, value:Int) : Boolean{
    var left = 0
    var right = arr.size

    while(left < right){
        val mid = (left + right).div(2)
        if(arr[mid] == value){
            return true
        }
        else if(arr[mid] > value){
            right = mid
        }
        else{
            left = mid + 1
        }
    }
    return false
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }

    val nA = input()
    val nB = input()

    val aArr = PriorityQueue<Int>()
    repeat(nA){
        aArr.add(input())
    }

    val bArr = IntArray(nB){ input() }
    bArr.sort()

    val sb = StringBuilder()
    var count = 0

    while(aArr.isNotEmpty()){
        val num = aArr.poll()
        if(!binarySearch(bArr, num)){
            sb.append(num).append(' ')
            count += 1
        }
    }

    println(count)
    println(sb.toString())
}
