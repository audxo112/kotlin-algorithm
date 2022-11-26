package `kotlin-algorithm`.`0x13_BinarySearch`

import java.util.*

fun main(){
    var st = StringTokenizer(readln())
    val nA = st.nextToken().toInt()
    val nB = st.nextToken().toInt()

    st = StringTokenizer(readln())
    val arrayA = IntArray(nA){
        st.nextToken().toInt()
    }

    st = StringTokenizer(readln())
    val arrayB = IntArray(nB){
        st.nextToken().toInt()
    }

    arrayA.sort()
    arrayB.sort()

    val sb = StringBuilder()
    var cnt = 0
    for(num in arrayA){
        if(arrayB.binarySearch(num) < 0){
            sb.append("$num ")
            cnt += 1
        }
    }
    println(cnt)
    if(cnt > 0) {
        println(sb)
    }

}