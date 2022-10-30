//https://www.acmicpc.net/problem/1541
package solution1541


private fun main(){
    var minus = false
    var cur = ""
    var sum = 0
    for(c in System.`in`.bufferedReader().readLine()){
        when(c){
            '+' -> {
                sum = if(minus) sum - cur.toInt() else sum + cur.toInt()
                cur = ""
            }
            '-' -> {
                sum = if(minus) sum - cur.toInt() else sum + cur.toInt()
                minus = true
                cur = ""
            }
            else -> cur += c
        }
    }
    sum = if(minus) sum - cur.toInt() else sum + cur.toInt()
    println(sum)
}