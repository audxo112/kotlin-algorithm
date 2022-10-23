//https://www.acmicpc.net/problem/1026
package solution1026

import java.util.StringTokenizer


fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val arrA = IntArray(101)
    val arrB = IntArray(101)
    val tokenA = StringTokenizer(readLine(), " ")
    val tokenB = StringTokenizer(readLine(), " ")
    repeat(N){
        arrA[tokenA.nextToken().toInt()] += 1
        arrB[tokenB.nextToken().toInt()] += 1
    }

    var count = N
    var ai = 0
    var bi = 100
    var sum = 0
    while(count > 0){
        while(arrA[ai] == 0){
            ai += 1
        }
        while(arrB[bi] == 0){
            bi -= 1
        }

        val inter = arrA[ai].coerceAtMost(arrB[bi])
        count -= inter
        arrA[ai] -= inter
        arrB[bi] -= inter
        sum += ai * bi * inter
    }
    println(sum)
}