// https://www.acmicpc.net/problem/14889

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

private lateinit var s: Array<IntArray>
private lateinit var arr: BooleanArray
private var min = Int.MAX_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    s = Array(n) {
        val st = StringTokenizer(br.readLine())
        IntArray(n) {
            st.nextToken().toInt()
        }
    }
    arr = BooleanArray(n)

    dfs(0, 0)

    println(min)
}

private fun dfs(idx: Int, start: Int) {
    if (idx == arr.size / 2) {
        var team1 = 0
        var team2 = 0

        for (i in 0 until s.size - 1) {
            for (j in i + 1 until s.size) {
                if (arr[i] && arr[j]) {
                    team1 += s[i][j] + s[j][i]
                } else if (!arr[i] && !arr[j]) {
                    team2 += s[i][j] + s[j][i]
                }
            }
        }

        min = min.coerceAtMost(abs(team1 - team2))
        return
    }

    for (i in start until s.size) {
        arr[i] = true
        dfs(idx + 1, i + 1)
        arr[i] = false
    }
}
