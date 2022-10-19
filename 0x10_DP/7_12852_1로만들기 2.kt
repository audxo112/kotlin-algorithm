package `kotlin-algorithm`.`0x10_DP`

import java.util.StringTokenizer

fun main(){
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()

    val arr = Array(n+1){
        Pair(0,0)
    }

    for(i in 2..n){
        arr[i] = Pair(arr[i-1].first + 1,i-1)

        if(i % 2 == 0){
            arr[i] = min(arr[i], Pair(arr[i/2].first + 1,i/2))
        }
        if(i % 3 == 0){
            arr[i] = min(arr[i], Pair(arr[i/3].first + 1,i/3))
        }
    }
    println(arr[n].first)
    println(getSequence(n,arr))
}

private fun min(a: Pair<Int,Int>, b: Pair<Int,Int>): Pair<Int,Int>{
    return if(a.first <= b.first){
        a
    } else{
        b
    }
}

private fun getSequence(n: Int, arr: Array<Pair<Int,Int>>): StringBuilder{
    val sb = StringBuilder()
    var now = n
    while(now != 1){
        sb.append("$now ")
        now = arr[now].second
    }
    sb.append("1")
    return sb
}
