//https://www.acmicpc.net/problem/2217
package solution2217

/**
 * sortDescending 이 내부적으로 sort, reverse 를 진행하는 것을 확인
 */

private fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val lopes = IntArray(N){
        readLine().toInt()
    }
    lopes.sort()
    var max = lopes[N - 1]
    for(i in (N - 2) downTo 0){
        val cur = lopes[i] * (N - i)
        if(max < cur){
            max = cur
        }
    }
    println(max)
}