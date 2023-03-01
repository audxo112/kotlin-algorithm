// https://www.acmicpc.net/problem/11559
package solution11559__code1

import java.io.BufferedReader
import java.io.InputStreamReader

private class Node(val x: Int, val y: Int)

private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val H = 12
    val W = 6

    val map = Array(H) {
        br.readLine().toCharArray()
    }
    // 배열을 매번 생성이 아닌 combo를 이용하여 방문여부
    val visited = Array(H) { IntArray(W) }

    var combo = 0

    // 마크하는 공간을 미리 할당
    val markList = Array(H * W) { Node(0, 0) }

    fun mark(x: Int, y: Int): Boolean {
        visited[y][x] = combo
        val shape = map[y][x]

        var index = 0
        markList[index] = Node(x, y)
        var count = 1

        // 같은 모양이 존재하는 곳을 markList 에 추가
        while (index < count) {
            val cur = markList[index]
            for (i in 0 until 4) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]

                if (nx !in 0 until W || ny !in 0 until H || visited[ny][nx] == combo || map[ny][nx] != shape) {
                    continue
                }

                visited[ny][nx] = combo
                markList[count++] = Node(nx, ny)
            }
            index += 1
        }

        // markList 에 4개 이상 체크된 경우 전부 제거
        if (count >= 4) {
            for (i in 0 until count) {
                val node = markList[i]
                map[node.y][node.x] = '.'
            }
            return true
        }

        return false
    }

    // 중간에 존재하는 점을 제거해준다
    fun sweep() {
        for (x in 0 until W) {
            var i = H - 1
            var j: Int
            do {
                while (i >= 0 && map[i][x] != '.') {
                    i -= 1
                }
                j = i - 1
                while (j >= 0 && map[j][x] == '.') {
                    j -= 1
                }
                if (j >= 0) {
                    map[i][x] = map[j][x]
                    map[j][x] = '.'
                    i -= 1
                }
            } while (j >= 0)
        }
    }

    var play = true
    while (play) {
        play = false
        combo += 1
        var y = H - 1
        while (y >= 0) {
            for (x in 0 until W) {
                // 알파벳인 경우 확인
                if (map[y][x] != '.' && visited[y][x] < combo && mark(x, y)) {
                    play = true
                }
            }
            y -= 1
        }
        if (play) {
            sweep()
        }
    }

    println(combo - 1)
}