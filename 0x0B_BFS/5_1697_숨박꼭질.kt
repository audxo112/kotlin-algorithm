import java.util.*

/**
 * 풀이 과정 - 메모리 부족
 * 1. n위치부터 그래프 그리기
 *  1-1. n-1, n+1, n*2를 자식으로 갖는다. (큐에 추가)
 *  1-2. 이미 나온 값은 큐에 추가하지 않음
 * 2. k가 나올때 까지 반복 -> k 값 노드의 depth를 출력
 *
 */

/**
 * 풀이 과정 2
 * 1. 0으로 초기화 된 배열 만들기
 * 2. n에서 시작해서 배열에 depth를 기록하기
 *  2-1. n to 0부터 시작
 *  2-2. (n-1), (n+1), (n*2) to d+1 큐에 추가
 *  2-3. 반복
 * 3. k가 나오면 loop나오기
 * 4. array[k] 출력
 */

/**
 * 의문점
 * class Node(val value: Int) {}
 * class Node(value: Int) {} - 인스턴스에서 value를 사용하지 못함
 * 뭐가 다른가
 */

private data class Node(val value: Int, val depth: Int)

fun main() = with(System.`in`.bufferedReader()){
    val bw = System.out.bufferedWriter()
    val (n, k) = readLine().split(" ").map { it.toInt() }

/*
    val queue: Queue<Int> = LinkedList()
    val visited = BooleanArray(100001)
    var count = 0

    queue.add(n)
    top@while (queue.isNotEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val cur = queue.remove()
            if (cur==k) {
                break@top
            }
            if (cur - 1 >= 0 && !visited[cur - 1]) {
                visited[cur - 1] = true
                queue.add(cur - 1)
            }
            if (cur + 1 < 100001 && !visited[cur + 1]) {
                visited[cur + 1] = true
                queue.add(cur + 1)
            }
            if (cur * 2 < 100001 && !visited[cur * 2]) {
                visited[cur * 2] = true
                queue.add(cur * 2)
            }
        }
        count++
    }
    bw.write("$count")
*/

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    val array = IntArray(100001)

    queue.add(n to 0)
    while (queue.isNotEmpty()) {
        val (i, d) = queue.remove() // index, depth

        array[i] = d
        if (i == k) {
            break
        }

        if (i - 1 >= 0 && array[i - 1] == 0) {
            queue.add(i - 1 to d + 1)
        }
        if (i + 1 < 100001 && array[i + 1] == 0) {
            queue.add(i + 1 to d + 1)
        }
        if (i * 2 in 0..100001 && array[i * 2] == 0) {
            queue.add(i * 2 to d + 1)
        }
    }

    bw.write("${array[k]}")
    bw.flush()
    bw.close()
}