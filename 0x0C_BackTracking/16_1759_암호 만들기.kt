// https://www.acmicpc.net/problem/1759
package solution1759

import java.util.StringTokenizer


private fun combination(N:Int, M:Int, n:Int, m:Int, vc:Int, wordArr:CharArray, vowels:IntArray, order:CharArray, sb:StringBuilder){
    if(M == m){
        if(vc >= 1 && M - vc >= 2){
            order.forEach {
                sb.append(it)
            }
            sb.append('\n')
        }
        return
    }

    for(i in n until N){
        order[m] = wordArr[i]
        combination(N, M, i + 1, m + 1, vc + vowels[i], wordArr, vowels, order, sb)
    }
}


private fun solution(L:Int, C:Int, wordArr:CharArray) : String {
    val vowelSet = setOf('a', 'e', 'i', 'o', 'u')
    val sb = StringBuilder()
    val order = CharArray(L)
    val vowels = IntArray(C){
        if(wordArr[it] in vowelSet) 1 else 0
    }
    combination(C, L, 0, 0, 0, wordArr, vowels, order, sb)

    return sb.toString()
}


private fun main() = with(System.`in`.bufferedReader()){
    var tokenizer = StringTokenizer(readLine(), " ")
    val l = tokenizer.nextToken().toInt()
    val c = tokenizer.nextToken().toInt()
    tokenizer = StringTokenizer(readLine(), " ")
    val wordArr = CharArray(c){
        tokenizer.nextToken()[0]
    }
    wordArr.sort()
    println(solution(l, c, wordArr))
}