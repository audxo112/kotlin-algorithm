// https://www.acmicpc.net/problem/18870
package solution18870__code2

import java.util.StringTokenizer

private fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val tokenizer = StringTokenizer(readLine(), " ")
    val arr = IntArray(n){
        tokenizer.nextToken().toInt()
    }
    val sorted = arr.sorted()
    val map = hashMapOf<Int, Int>()

    var order = 0
    sorted.forEach {
        if(!map.containsKey(it)){
            map[it] = order
            order += 1
        }
    }
    val sb = StringBuilder()
    arr.forEach {
        sb.append(map[it]).append(" ")
    }
    println(sb.toString())
}