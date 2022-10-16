package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tc = br.readLine().toInt()
    val input = IntArray(tc) { br.readLine().toInt() }
    val dp = Array(input.maxOrNull()!! + 1) { Node(0,0) }
    dp[0].zero = 1
    dp[0].one = 0
    if (dp.size > 1) {
        dp[1].zero = 0
        dp[1].one = 1
    }

    for (i in 2 until dp.size) {
        dp[i].apply {
            zero = dp[i - 1].zero + dp[i - 2].zero
            one = dp[i - 1].one + dp[i - 2].one
        }
    }

    val sb = StringBuilder()
    repeat(tc) {
        val idx = input[it]
        sb.appendLine(dp[idx].toString())
    }
    print(sb)
}

data class Node(var zero: Int, var one: Int) {
    override fun toString(): String {
        return "$zero $one"
    }
}