// https://www.acmicpc.net/problem/2847
package solution2847

private fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val scores = IntArray(N){
        readLine().toInt()
    }

    var sum = 0
    for(i in N - 1 downTo 1){
        if(scores[i] <= scores[i - 1]){
            sum += scores[i - 1] - scores[i] + 1
            scores[i - 1] = scores[i] - 1
        }
    }
    println(sum)
}