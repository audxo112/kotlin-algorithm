package `kotlin-algorithm`.`0x10_DP`

import kotlin.math.max

fun main(){
    val n = readln().toInt()
    val stairs = IntArray(301)
    val score = IntArray(301)
    for (i in 1 .. n){
        stairs[i] = readln().toInt()
    }
    score[1] = stairs[1]
    score[2] = stairs[1] + stairs[2]

    for(i in 3 ..n){
        score[i] = max(score[i-2] + stairs[i],score[i-3] + stairs[i-1] + stairs[i] )
    }
    println(score[n])
}