package `kotlin-algorithm`.`0x11_Greedy`

import java.util.StringTokenizer

fun main(){
    val n = readln().toInt()
    val st = StringTokenizer(readln())
//    val dp = IntArray(n+1)
//
//    while (st.hasMoreTokens()){
//        val child = st.nextToken().toInt()
//        dp[child] = dp[child-1] + 1
//
//    }
//    println(n - dp.maxOrNull()!!)

    val children = IntArray(n+1)
    val position = IntArray(n+1)

    for (i in 1 .. n){
        val num = st.nextToken().toInt()
        children[i] = num
        position[num] = i
    }
    var maxLen = 1

    for (i in 1 until n) {
        var len = 1
        var nowChild = children[i]
        var nowChildPosition = position[nowChild]
        if(nowChild == n){
            continue
        }
        var nextChildPosition = position[nowChild + 1]

        while (nowChildPosition < nextChildPosition){
            nowChild = children[nextChildPosition]
            nowChildPosition = position[nowChild]
            nextChildPosition = position[nowChild + 1]
            len+=1
        }
        if(maxLen < len){
            maxLen = len
        }
    }
    println(n-maxLen)
}
