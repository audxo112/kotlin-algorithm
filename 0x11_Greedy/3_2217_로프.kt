import java.util.StringTokenizer


fun main(){
    val k = readln().toInt()
    val lopes = Array(k){
        val st = StringTokenizer(readln())
        st.nextToken().toInt()
    }
    lopes.sort()

    val answerArr = IntArray(k)

    for(i in 0 until k){
        answerArr[i] = lopes[i] * (k-i)
    }

    println(answerArr.maxOrNull())
}