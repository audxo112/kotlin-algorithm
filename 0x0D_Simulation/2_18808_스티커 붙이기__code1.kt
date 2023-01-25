// https://www.acmicpc.net/problem/18808
package solution18808__code1

import java.io.StreamTokenizer

class Point(val x: Int, val y: Int)

class Sticker(
    val arr: Array<IntArray>,
    val points: ArrayList<Point>,
    val w: Int = arr[0].size,
    val h: Int = arr.size,
) {
    // x, y 만 바꾸면 대각선으로 대칭이 되게 된다
    // y를 뒤집어서 회전으로 변경
    // x를 뒤집으면 반대로 회전
    fun rotate(): Sticker {
        val points = ArrayList<Point>(points.size)
        val item = Array(w) { y ->
            IntArray(h) { x ->
                arr[h - x - 1][y].also {
                    if (it == 1) {
                        points.add(Point(x, y))
                    }
                }
            }
        }
        return Sticker(
            item, points
        )
    }
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val M = input()
    val K = input()

    val map = Array(N) { IntArray(M) }

    fun Array<IntArray>.attach(sticker: Sticker, sx: Int, sy: Int): Boolean {
        // point 를 이용해서 추가할 수 있는지 판다
        if (sticker.points.any { p ->
                this[sy + p.y][sx + p.x] == 1
            }) {
            return false
        }

        // 추가할 수 있으면 추가
        for (p in sticker.points) {
            this[sy + p.y][sx + p.x] = 1
        }
        return true
    }

    // 스티커 크기를 고려해서 순회
    fun Array<IntArray>.install(sticker: Sticker): Boolean{
        for(y in 0 .. N - sticker.h) for(x in 0 .. M - sticker.w){
            if(attach(sticker, x, y)){
                return true
            }
        }
        return false
    }

    val stickers = Array(K) {
        val r = input()
        val c = input()
        // 처음에는 점의 개수를 모르니 r * c 개 만큼 공간을 미리 잡아준다
        val points = ArrayList<Point>(r * c)
        val item = Array(r) { y ->
            IntArray(c) { x ->
                input().also {
                    if (it == 1) {
                        // 모든 Point를 미리 저장
                        points.add(Point(x, y))
                    }
                }
            }
        }
        Sticker(item, points)
    }

    var count = 0
    for(list in stickers){
        var sticker = list
        for(i in 0 until 4){
            if(map.install(sticker)){
                count += sticker.points.size
                break
            }
            sticker = sticker.rotate()
        }
    }
    println(count)
}