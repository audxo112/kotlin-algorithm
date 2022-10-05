package `kotlin-algorithm`.`0x10_DP`

import kotlin.math.min

fun main(){
    val n = readln().toInt()

    val arr = IntArray(n+1)

    for(i in 2..n){
        arr[i] = arr[i-1] + 1

        if(i % 2 == 0){
            arr[i] = min(arr[i], arr[i / 2] + 1)
        }
        if(i % 3 == 0){
            arr[i] = min(arr[i],arr[i/3] + 1)
        }
    }

    print(arr[n])

}