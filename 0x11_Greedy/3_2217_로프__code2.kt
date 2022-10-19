//https://www.acmicpc.net/problem/2217
package solution2217

/**
 * Kotlin 의 sort 는 TimSort (= Insertion Sort + Merge Sort) 를 사용하기 때문에
 * sort 의 시간 복잡도는
 * Best = O(N)
 * Average = O(NlogN)
 * Worst = O(NlogN)
 *
 * 그래서 sort 없이 순차적으로 접근해봄
 */

private fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val lopes = IntArray(10001)
    repeat(N){
        lopes[readLine().toInt()] += 1
    }

    var max = 0
    var count = 0
    for(i in 10000 downTo 1){
        if(lopes[i] == 0){
            continue
        }
        count += lopes[i]
        max = max.coerceAtLeast(i * count)
    }
    println(max)
}