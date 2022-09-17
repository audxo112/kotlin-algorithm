//https://www.acmicpc.net/problem/1697
package solution1697

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer


private fun solution(n:Int, k:Int) : Int{
    if(n >= k){
        return n - k
    }
    val K = Math.min(k * 2 + 1, 100001)
    val visited = BooleanArray(K)
    visited[n] = true

    val deque:Queue<Int> = LinkedList()
    deque.add(n)

    var time = 0
    while(deque.isNotEmpty()){
        repeat(deque.size){
            val c = deque.poll()
            if(c == k){
                return time
            }

            var nn = c - 1
            if(nn >= 0 && !visited[nn]){
                deque.add(nn)
                visited[nn] = true
            }

            nn = c + 1
            if(nn <= k && !visited[nn]){
                deque.add(nn)
                visited[nn] = true
            }

            nn = c * 2
            if(nn < K && !visited[nn]){
                deque.add(nn)
                visited[nn] = true
            }
        }
        time += 1
    }
    return -1
}


private fun main() = with(System.`in`.bufferedReader()){
    val tokenizer = StringTokenizer(readLine(), " ")
    val n = tokenizer.nextToken().toInt()
    val k = tokenizer.nextToken().toInt()

    println(solution(n, k))
}