//https://www.acmicpc.net/problem/2457
package solution2457

import java.util.StringTokenizer


private fun solution(S:Int, E:Int, flowers:IntArray) : Int{
    var cur = S
    var max = S
    var count = 0

    for(i in S until E){
        if(flowers[i] == 0){
            continue
        }

        if(i > max){
            return 0
        }

        if(i > cur){
            cur = max
            count += 1
        }

        if(flowers[i] > max){
            max = flowers[i]
            if(max == E){
                return count + 1
            }
        }
    }

    return 0
}


private fun main() = with(System.`in`.bufferedReader()){
    val dates = listOf(0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
    val N = readLine().toInt()
    val S = dates[3] + 1
    val E = dates[12] + 1

    val flowers = IntArray(366)
    repeat(N){
        val tokenizer = StringTokenizer(readLine(), " ")
        val sm = tokenizer.nextToken().toInt()
        val sd = tokenizer.nextToken().toInt()
        val em = tokenizer.nextToken().toInt()
        val ed = tokenizer.nextToken().toInt()
        val start = if (sm < 3) S else dates[sm] + sd
        val end = if(em > 11) E else dates[em] + ed

        if(sm > 11 || em < 3 || start == end){
            return@repeat
        }

        flowers[start] = flowers[start].coerceAtLeast(end)
    }

    println(solution(S, E, flowers))
}


