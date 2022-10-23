// https://www.acmicpc.net/problem/1003
package solution1003


private fun main() = with(System.`in`.bufferedReader()){
    val t = readLine().toInt()
    val sb = StringBuilder()
    val zeroDp = IntArray(41)
    zeroDp[0] = 1

    val oneDp = IntArray(41)
    oneDp[1] = 1

    var cN = 1
    repeat(t){
        val N = readLine().toInt()
        if(N > cN){
            for(n in cN + 1 .. N){
                zeroDp[n] = zeroDp[n - 1] + zeroDp[n - 2]
                oneDp[n] = oneDp[n - 1] + oneDp[n - 2]
            }
            cN = N
        }

        sb.appendLine("${zeroDp[N]} ${oneDp[N]}")
    }
    println(sb)
}
