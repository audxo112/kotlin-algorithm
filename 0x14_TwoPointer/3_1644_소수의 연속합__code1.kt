// https://www.acmicpc.net/problem/1644
package solution1644__code1

private fun prime(n: Int): List<Int> {
    val visited = BooleanArray(n + 1) { true }
    for (i in 2..n / 2) {
        var j = i * 2
        if (!visited[i]) {
            continue
        }
        while (j <= n) {
            visited[j] = false
            j += i
        }
    }

    val list = ArrayList<Int>(n)
    for (i in 2..n) {
        if (visited[i]) {
            list.add(i)
        }
    }
    return list
}

private fun solution(m: Int): Int {
    if(m == 1){
        return 0
    }

    val arr = prime(m)

    val n = arr.size
    var left = 0
    var right = 0
    var sum = 0
    var count = 0

    while (left < n) {
        if(right < n && sum < m){
            sum += arr[right++]
        } else{
            if(sum == m){
                count ++
            }
            sum -= arr[left++]
        }
    }

    return count
}

private fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    println(solution(n))
}