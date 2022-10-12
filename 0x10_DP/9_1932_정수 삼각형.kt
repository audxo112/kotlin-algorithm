package `kotlin-algorithm`.`0x10_DP`

import java.util.StringTokenizer

fun main(){
    var st = StringTokenizer(readln())

    val n = st.nextToken().toInt()

    val arr = Array(n){index ->
        IntArray(index + 1)
    }
    val score = Array(n){index ->
        IntArray(index + 1)
    }

    for(i in 0 until n){
        st = StringTokenizer(readln())
        for (j in 0 .. i){
            arr[i][j] = st.nextToken().toInt()
        }

        if(i == 0){
            score[0][0] = arr[0][0]
        } else{
            for(j in 0 .. i){
                when (j) {
                    0 -> {
                        score[i][j] = score[i-1][j] + arr[i][j]
                    }
                    i -> {
                        score[i][j] = score[i-1][j-1] + arr[i][j]
                    }
                    else -> {
                        score[i][j] = maxOf(score[i-1][j],score[i-1][j-1])  + arr[i][j]
                    }
                }
            }
        }
    }
    println(score[n-1].maxOrNull())
}