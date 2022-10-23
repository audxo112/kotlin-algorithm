//https://www.acmicpc.net/problem/1931
package solution1931__code1

/**
 * sortedWith 에서 compareBy 를 사용하면 메모리가 너무 많이 사용됨
 */

import java.util.StringTokenizer


private data class Meeting(
    val start:Int,
    val end:Int,
)


private fun solution(meetings:List<Meeting>) : Int{
    var count = 0
    var bEnd = -1
    for(meeting in meetings){
        if(bEnd <= meeting.start){
            bEnd = meeting.end
            count += 1
        }
        else if(bEnd > meeting.end){
            bEnd = meeting.end
        }
    }
    return count
}


private fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val arr = Array(N){
        val tokenizer = StringTokenizer(readLine(), " ")
        Meeting(
            tokenizer.nextToken().toInt(),
            tokenizer.nextToken().toInt()
        )
    }

    val meetings = arr.sortedWith(
        compareBy({it.start}, {it.end})
    )
    println(solution(meetings))
}
