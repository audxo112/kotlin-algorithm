//https://www.acmicpc.net/problem/1931
package solution1931__code2

/**
 * 일찍 끝나고 일찍 시작하는 회의를 우선적으로 선택
 * 회의가 끝나야 다음 회의가 시작할 수 있다는점 생각
 */

import java.util.StringTokenizer

data class Meet(val start:Int, val end:Int)


private fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val meetings = Array(N){
        val tokenizer = StringTokenizer(readLine(), " ")
        Meet(
            tokenizer.nextToken().toInt(),
            tokenizer.nextToken().toInt()
        )
    }
    meetings.sortWith{ a, b->
        if(a.end == b.end) a.start - b.start else a.end - b.end
    }

    var count = 0
    var end = -1
    repeat(N){i ->
        if(end <= meetings[i].start){
            end = meetings[i].end
            count += 1
        }
    }
    println(count)
}