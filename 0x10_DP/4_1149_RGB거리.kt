package `kotlin-algorithm`.`0x10_DP`

import kotlin.math.min


fun main(){
    val n = readln().toInt()

    val arr = Array(n+1){
        IntArray(3)
    }
    val red = IntArray(1001)
    val green = IntArray(1001)
    val blue = IntArray(1001)

    for(i in 1 .. n){
        arr[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }
    red[1] = arr[1][0]
    green[1] = arr[1][1]
    blue[1] = arr[1][2]

    for (i in 2..n){
        red[i] = min(blue[i-1],green[i-1]) + arr[i][0]
        green[i] = min(blue[i-1],red[i-1]) + arr[i][1]
        blue[i] = min(red[i-1],green[i-1]) + arr[i][2]
    }
    println(minOf(red[n],green[n],blue[n]))
}