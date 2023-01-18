// https://www.acmicpc.net/problem/18808
package solution18808__code2

import java.io.StreamTokenizer

// 모든 회전에 대해서 point를 생성하면 메모리 지역성으로 인해 시간이 지연되는것으로 판단

class Sticker(
    val arr: Array<IntArray>,
    val count: Int,
    val w: Int = arr[0].size,
    val h: Int = arr.size,
) {
    fun rotate(): Sticker {
        val item = Array(w) { y ->
            IntArray(h) { x ->
                arr[h - x - 1][y]
            }
        }
        return Sticker(
            item, count
        )
    }

    fun check(sticker: Sticker): Boolean {
        if (w != sticker.w || h != sticker.h) {
            return false
        }
        for (y in arr.indices) {
            for (x in arr[y].indices) {
                if (arr[y][x] != sticker.arr[y][x]) {
                    return false
                }
            }
        }
        return true
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

    fun Array<IntArray>.available(sticker: Sticker, sx: Int, sy: Int): Boolean {
        for (y in sy until sy + sticker.h) for (x in sx until sx + sticker.w) {
            if (sticker.arr[y - sy][x - sx] == 1 && this[y][x] == 1) {
                return false
            }
        }
        return true
    }

    fun Array<IntArray>.attach(sticker: Sticker, sx: Int, sy: Int) {
        for (y in sy until sy + sticker.h) for (x in sx until sx + sticker.w) {
            if (sticker.arr[y - sy][x - sx] == 1) {
                this[y][x] = 1
            }
        }
    }

    fun Array<IntArray>.install(sticker: Sticker): Boolean {
        for (y in 0..N - sticker.h) for (x in 0..M - sticker.w) {
            if (available(sticker, x, y)) {
                attach(sticker, x, y)
                return true
            }
        }
        return false
    }

    val stickers = Array(K) {
        val r = input()
        val c = input()
        var count = 0
        val item = Array(r) { y ->
            IntArray(c) { x ->
                input().also {
                    if (it == 1) {
                        count += 1
                    }
                }
            }
        }
        Sticker(item, count)
    }

    var count = 0
    for (initSticker in stickers) {
        if (map.install(initSticker)) {
            count += initSticker.count
            continue
        }
        val list = ArrayList<Sticker>(4)
        list.add(initSticker)
        for (i in 0 until 3) {
            val sticker = list[i].rotate()
            if (list.any { sticker.check(it) }) {
                break
            }
            if (map.install(sticker)) {
                count += initSticker.count
                break
            }
            list.add(sticker)
        }
    }
    println(count)
}