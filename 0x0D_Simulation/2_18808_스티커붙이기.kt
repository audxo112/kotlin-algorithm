// https://www.acmicpc.net/problem/18808

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var n: Int = 0
private var m: Int = 0
private lateinit var laptop: Array<IntArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    laptop = Array(n) {
        IntArray(m)
    }

    repeat(k) {
        st = StringTokenizer(br.readLine())
        val r = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        val sticker = Array(r) {
            st = StringTokenizer(br.readLine())
            IntArray(c) {
                st.nextToken().toInt()
            }
        }

        var tmp = sticker
        for (i in 0 until 4) {
            // 붙일 수 있는지 검사
            if (checkSticker(tmp)) {
                break
            }
            // 90도 회전, (마지막엔 회전할 필요 없음)
            if (i != 3) {
                tmp = rotate90(tmp)
            }
        }
    }

    var count = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (laptop[i][j] == 1) count++
        }
    }
    println(count)
}

private fun checkSticker(sticker: Array<IntArray>): Boolean {
    val r = sticker.size
    val c = sticker[0].size

    for (i in laptop.indices) {
        // 남은 길이가 스티커의 세로 보다 작음
        if (n - i < r) break

        for (j in laptop[0].indices) {
            // 남은 길이가 스티커의 가로 보다 작음
            if (m - j < c) break

            if (checkVisited(sticker, i, j)) {
                attachSticker(sticker, i, j)
                return true
            }
        }
    }

    return false
}

private fun attachSticker(sticker: Array<IntArray>, x: Int, y: Int) {
    for (i in sticker.indices) {
        for (j in sticker[0].indices) {
            if (laptop[x + i][y + j] == 0 && sticker[i][j] == 1) {
                laptop[x + i][y + j] = sticker[i][j]
            }
        }
    }
}

private fun checkVisited(sticker: Array<IntArray>, x: Int, y: Int): Boolean {
    for (i in sticker.indices) {
        for (j in sticker[0].indices) {
            // 노트북에 이미 붙인 스티커 영역과 겹치면
            if (laptop[x + i][y + j] == 1 && sticker[i][j] == 1) {
                return false
            }
        }
    }
    return true
}

private fun rotate90(arr: Array<IntArray>): Array<IntArray> {
    val n = arr[0].size
    val m = arr.size
    return Array(n) { i ->
        IntArray(m) { j ->
            arr[m - j - 1][i]
        }
    }
}