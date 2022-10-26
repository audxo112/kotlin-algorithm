// https://www.acmicpc.net/problem/1439
package solution1439

private fun main() = with(System.`in`.bufferedReader()){
    var one = 0
    var zero = 0

    val arr = readLine()
    if(arr[0] == '0') {
        zero += 1
    } else{
        one += 1
    }

    for(i in 1 until arr.length){
        if(arr[i] == arr[i - 1]){
            continue
        }
        if(arr[i] == '0'){
            zero += 1
        }
        else{
            one += 1
        }
    }
    println(one.coerceAtMost(zero))
}