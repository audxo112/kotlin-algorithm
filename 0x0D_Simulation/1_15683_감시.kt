package solve_15683

import java.io.StreamTokenizer
private data class CCTV(val x: Int, val y: Int, val num: Int)

private val dx = arrayOf(1, -1, 0, 0)
private val dy = arrayOf(0, 0, 1, -1)
private var answer = Int.MAX_VALUE
private lateinit var map: Array<IntArray>
private val cctvList = ArrayList<CCTV>()
private val cctvWatchList =
    arrayOf(
        emptyArray(),
        arrayOf(arrayOf(0), arrayOf(1), arrayOf(2), arrayOf(3)),
        arrayOf(arrayOf(0, 1), arrayOf(2, 3)),
        arrayOf(arrayOf(0, 2), arrayOf(0, 3), arrayOf(1, 2), arrayOf(1, 3)),
        arrayOf(arrayOf(0, 1, 2), arrayOf(0, 1, 3), arrayOf(0, 2, 3), arrayOf(1, 2, 3)),
        arrayOf(arrayOf(0, 1, 2, 3))
    )

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    map = Array(n) { y ->
        IntArray(m) { x ->
            val num = input()
            if (num != 0 && num != 6) {
                cctvList.add(CCTV(x, y, num))
            }
            num
        }
    }

    dfs(map, 0)
    println(answer)
}

private fun dfs(map: Array<IntArray>, depth: Int) {
    if (depth == cctvList.size) {
        var count = 0
        map.forEach { arr ->
            arr.forEach {
                if (it == 0) count++
            }
        }
        answer = minOf(answer, count)
        return
    }
    val cctv = cctvList[depth]
    for (watch in cctvWatchList[cctv.num]) {
        val copyMap = mapCopy(map)
        runCCTV(watch, cctv, copyMap)
        dfs(copyMap, depth + 1)
    }

}

private fun runCCTV(watch: Array<Int>, cctv: CCTV, map: Array<IntArray>) {
    for (w in watch) {
        var nx = cctv.x
        var ny = cctv.y
        while (true) {
            nx += dx[w]
            ny += dy[w]

            if (nx < 0 || nx > map[0].lastIndex) break
            if (ny < 0 || ny > map.lastIndex) break
            if (map[ny][nx] == 6) break

            if (map[ny][nx] == 0) {
                map[ny][nx] = -1
            }
        }
    }
}

private fun mapCopy(preMap: Array<IntArray>) = Array(preMap.size) { y ->
    IntArray(preMap[0].size) { x ->
        preMap[y][x]
    }
}

