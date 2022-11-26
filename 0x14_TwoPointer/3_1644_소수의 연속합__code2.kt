// https://www.acmicpc.net/problem/1644
package solution1644__code2


private fun solution(n: Int): Int{
    val visited = BooleanArray(n + 1) { true }
    for (i in 2..n / 2) {
        if (!visited[i]) {
            continue
        }
        var j = 2
        while (i * j <= n) {
            visited[i * j] = false
            j++
        }
    }

    var left = 2
    var right = 2
    var sum = 2
    var count = 0

    while (right <= n) {
        if (sum <= n) {
            if (sum == n) {
                count += 1
            }
            do {
                right += 1
                if(right > n){
                    return count
                }
            }while (!visited[right])
            sum += right
        } else {
            sum -= left
            do {
                left += 1
                if(left > right){
                    return count
                }
            } while(!visited[left])
        }
    }
    return count
}


private fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    println(solution(n))
}