package `kotlin-algorithm`.`0x10_DP`

fun main(){
    val n = readln().toInt()

    val dp = IntArray(n+1)

    if(n <= 3) {
        println(n)
        return
    }

    dp[1] = 1
    dp[2] = 2

    for(i in 3..n){
        dp[i] = (dp[i-2] + dp[i-1]) % 10007
    }

    println(dp[n])
}