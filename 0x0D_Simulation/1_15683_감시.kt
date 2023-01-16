// https://www.acmicpc.net/problem/15683
package solution15683

import java.io.StreamTokenizer

class Camera(val type:Int, val x: Int, val y: Int)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    // 감시할 방향을 정의
    val L = 0
    val T = 1
    val R = 2
    val B = 3

    val dx = intArrayOf(-1, 0, 1, 0)
    val dy = intArrayOf(0, -1, 0, 1)

    // 각 카메라마다 감시할 수 있는 방향의 배열을 생성
    val cameraDirs = arrayOf(
        emptyArray(),
        arrayOf(intArrayOf(L), intArrayOf(T), intArrayOf(R), intArrayOf(B)),
        arrayOf(intArrayOf(L, R), intArrayOf(T, B)),
        arrayOf(intArrayOf(L, T), intArrayOf(T, R), intArrayOf(R, B), intArrayOf(B, L)),
        arrayOf(intArrayOf(L, T, R), intArrayOf(T, R, B), intArrayOf(R, B, L), intArrayOf(B, L, T)),
        arrayOf(intArrayOf(L, T, R, B))
    )

    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    val visited = Array(n) { IntArray(m) }

    // 특정 위치에서 방향 리스트에 있는 값을 1씩 더해서 감시로 표시
    // 이번 감시로 0이된 영역의 개수를 반환
    fun Array<IntArray>.install(x: Int, y: Int, dirs: IntArray): Int {
        var count = 0
        for (i in dirs) {
            var nx = x
            var ny = y
            while(true) {
                nx += dx[i]
                ny += dy[i]

                if(nx !in 0 until m || ny !in 0 until n || this[ny][nx] == -1){
                    break
                }
                if (this[ny][nx] == 0) {
                    count += 1
                }
                this[ny][nx] += 1
            }
        }
        return count
    }

    // 특정 위치에서 방향 리스트에 있는 값을 1씩 빼기 감시영역 제외
    fun Array<IntArray>.uninstall(x: Int, y: Int, dirs: IntArray) {
        for (i in dirs) {
            var nx = x
            var ny = y
            while(true){
                nx += dx[i]
                ny += dy[i]
                if(nx !in 0 until m || ny !in 0 until n || this[ny][nx] == -1){
                    break
                }
                this[ny][nx] -= 1
            }
        }
    }

    var zero = 0
    val cameras = ArrayList<Camera>()
    // 5번 카메라는 미리 감시표시를 하기위해 따로 저장
    // 벽을 확인 하지 않고 그리면 벽을 뚫고 감시 하기 때문에
    // 모든 지형지물을 파악하고 나중에 설치
    val camera5s = ArrayList<Camera>()

    for (y in 0 until n) {
        for (x in 0 until m) {
            val value = input()
            // 카메라 들의 위치는 이미 감시되므로 + 1
            // 벽은 감시할 수 없게 -1 로 표시
            // zero 의 개수를 따로 파악
            when (value) {
                5 -> {
                    camera5s.add(Camera(value, x, y))
                    visited[y][x] += 1
                }
                6 -> visited[y][x] = -1
                0 -> zero += 1
                else -> {
                    cameras.add(Camera(value, x, y))
                    visited[y][x] += 1
                }
            }
        }
    }

    // 5번 카메라를 전부 설치한다
    for(camera in camera5s){
        zero -= visited.install(camera.x, camera.y, cameraDirs[5][0])
    }

    // 백트래킹을 이용하여 모든 경우의 수를 파악
    fun ArrayList<Camera>.backTracking(n: Int, minZero: Int) {
        if(this.size == n){
            // 끝까지 도달 했을 경우에 최소값을 구해야 비교수가 줄어듬
            zero = minZero.coerceAtMost(zero)
            return
        }

        val camera = this[n]
        val dirs = cameraDirs[camera.type]

        for(i in dirs.indices){
            val newZero = visited.install(camera.x, camera.y, dirs[i])

            backTracking(n + 1, minZero - newZero)
            visited.uninstall(camera.x, camera.y, dirs[i])
        }
    }

    cameras.backTracking(0, zero)
    println(zero)
}