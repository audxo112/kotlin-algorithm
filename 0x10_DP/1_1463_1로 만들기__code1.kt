// https://www.acmicpc.net/problem/1463
package solution1463

import java.util.*


private fun solution(N:Int) : Int {
    val visited = BooleanArray(N)
    val queue: Queue<Int> = LinkedList()
    queue.add(N)

    var dist = 0
    while(queue.isNotEmpty()){
        repeat(queue.size){
            val n = queue.poll()
            if(n == 1){
                return dist
            }

            if(n % 3 == 0 && visited[n / 3].not()){
                visited[n / 3] = true
                queue.add(n / 3)
            }
            if(n % 2 == 0 && visited[n / 2].not()){
                visited[n / 2] = true
                queue.add(n / 2)
            }
            if(visited[n - 1].not()){
                visited[n - 1] = true
                queue.add(n - 1)
            }
        }
        dist += 1
    }

    return 0
}


private fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    println(solution(n))
}