// https://www.acmicpc.net/problem/1463
package solution1463

import java.lang.Integer.min

/**
 * 신기한 코드라 분석중
 */

private fun solution(n:Int) : Int {
    if(n < 2){
        return 0
    }
    return min(solution(n / 2) + n % 2, solution(n / 3) + n % 3) + 1
}


private fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    println(solution(n))
}