//https://www.acmicpc.net/problem/1541
package solution1541
/**
 *  - 가 나오는 시점이 중요했던 문제
 * */


private fun main(){
    var input:Int
    var cur = 0

    var minus = false
    var sum = 0

    while(System.`in`.read().also { input = it } != 10){
        when(input){
            43 -> {
                sum = if(minus) sum - cur else sum + cur
                cur = 0
            }
            45 -> {
                sum = if(minus) sum - cur else sum + cur
                minus = true
                cur = 0
            }
            else -> {
                cur =  (cur shl 3) + (cur shl 1) + (input and 15)
            }
        }
    }
    sum = if(minus) sum - cur else sum + cur

    println(sum)
}