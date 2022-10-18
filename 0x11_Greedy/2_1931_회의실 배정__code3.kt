//https://www.acmicpc.net/problem/1931
package solution1931__code3

/**
 * sort 의 시간 복잡도가 O(NlogN) 이라는 점에서
 * map을 이용하여 의미 없는 값을 제거 후 정렬
 */

import java.util.StringTokenizer


private data class Meet(val start:Int, val end:Int)


private fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val map = HashMap<Int, Int>()
    val zeroSet = HashSet<Int>()
    var zeroCount = 0
    repeat(N){
        val tokenizer = StringTokenizer(readLine(), " ")
        val start = tokenizer.nextToken().toInt()
        val end = tokenizer.nextToken().toInt()
        if(start == end){
            zeroCount += 1
            zeroSet.add(start)
        }
        else{
            map[start] = map[start]?.coerceAtMost(end) ?: end
        }
    }
    for(zero in zeroSet){
        if(zero !in map){
            zeroCount -= 1
            map[zero] = zero
        }
    }

    val meetings = map.map{
        Meet(it.key, it.value)
    }.toList().sortedWith{ a, b->
        if(a.end == b.end) a.start - b.start else a.end - b.end
    }

    var count = 0
    var end = -1
    repeat(meetings.size){i ->
        if(end <= meetings[i].start){
            end = meetings[i].end
            count += 1
        }
    }
    println(count + zeroCount)
}