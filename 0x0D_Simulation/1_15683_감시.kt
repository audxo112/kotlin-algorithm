// https://www.acmicpc.net/problem/15683

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private val dx = arrayOf(-1, 1, 0, 0)
private val dy = arrayOf(0, 0, -1, 1)
// cctv 별 감시 방향 종류
private val watchList =
    arrayOf(
        arrayOf(),
        arrayOf(arrayOf(0), arrayOf(1), arrayOf(2), arrayOf(3)), // 1번 cctv
        arrayOf(arrayOf(0, 1), arrayOf(2, 3)),  // 2번 cctv
        arrayOf(arrayOf(0, 2), arrayOf(0, 3), arrayOf(1, 2), arrayOf(1, 3)), // 3번 cctv
        arrayOf(arrayOf(0, 1, 2), arrayOf(0, 1, 3), arrayOf(0, 2, 3), arrayOf(1, 2, 3)), // 4번 cctv
        arrayOf(arrayOf(0, 1, 2, 3)) // 5번 cctv
    )
private lateinit var cctvList: MutableList<CCTV>
private var min = Int.MAX_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val arr = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(m) {
            st.nextToken().toInt()
        }
    }

    cctvList = mutableListOf()

    for (i in 0 until n) {
        for (j in 0 until m) {
            if (arr[i][j] in 1..5) {
                cctvList.add(CCTV(i, j, arr[i][j]))
            }
        }
    }

    dfs(arr, 0)

    println(min)
}

private fun dfs(arr: Array<IntArray>, dept: Int) {
    if (dept == cctvList.size) {
        var count = 0
        for (i in arr.indices) {
            for (j in arr[0].indices) {
                if (arr[i][j] == 0) {
                    count++
                }
            }
        }

        if (count < min) {
            min = count
        }
        return
    }

    val (x, y, type) = cctvList[dept]
    // cctv 종류에 따른 감시방향들
    for (watch in watchList[type]) {
        val copied = deepCopy(arr)
        watchArr(watch, x, y, copied)
        // 다음 cctv
        dfs(copied, dept + 1)
    }
}

private fun deepCopy(arr: Array<IntArray>): Array<IntArray> {
    return Array(arr.size) {
        arr[it].clone()
    }
}

private fun watchArr(watch: Array<Int>, x: Int, y: Int, arr: Array<IntArray>) {
    // 한 방향씩 탐색
    for (i in watch) {
        // 벽에 막힐 때까지 탐색
        var xx = x
        var yy = y
        while (true) {
            xx += dx[i]
            yy += dy[i]

            if (xx !in arr.indices || yy !in arr[0].indices || arr[xx][yy] == 6) break

            if (arr[xx][yy] == 0) {
                arr[xx][yy] = -1 // # 표시
            }
        }
    }
}

data class CCTV(val x: Int, val y: Int, val type: Int)