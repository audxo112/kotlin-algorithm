// https://www.acmicpc.net/problem/14503

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = arrayOf(-1, 0, 1, 0) // 북 동 남 서
private val dy = arrayOf(0, 1, 0, -1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    var x = st.nextToken().toInt()
    var y = st.nextToken().toInt()
    var dir = st.nextToken().toInt()
    var count = 1
    val arr = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(m) {
            st.nextToken().toInt()
        }
    }

    arr[x][y] = -1

    while (true) {
        var tmp = dir
        for(i in 0 until 4) {
            tmp += 3 // 바로 반시계 회전
            val xx = x + dx[tmp % 4]
            val yy = y + dy[tmp % 4]

            if (xx in arr.indices && yy in arr[0].indices && arr[xx][yy] == 0) {
                x = xx
                y = yy
                dir = tmp
                arr[xx][yy] = -1 // 청소
                count++
                break
            }

            // 주변에 청소할 곳 없음
            if (i == 3) {
                dir += 2 // 후진
                val bx = x + dx[dir % 4]
                val by = y + dy[dir % 4]
                if (arr[bx][by] == 1) {
                    println(count)
                    return
                }
                dir -= 2 // 방향 원래대로
                x = bx
                y = by
            }
        }
    }
}