// https://www.acmicpc.net/problem/15686
package solution15686__code1

import java.io.StreamTokenizer
import kotlin.math.abs

private class Node(val x: Int, val y: Int)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val M = input()

    // 각각 나올 수 있는 최대 갯수로 용량을 초기화
    val house = ArrayList<Node>(2 * N)
    val chicken = ArrayList<Node>(13)

    for (y in 0 until N) for (x in 0 until N) {
        when (input()) {
            1 -> house.add(Node(x, y))
            2 -> chicken.add(Node(x, y))
        }
    }

    val H = house.size
    val C = chicken.size
    // 미리 거리를 계산한다
    val dist = Array(C) { c ->
        IntArray(H) { h ->
            abs(chicken[c].x - house[h].x) + abs(chicken[c].y - house[h].y)
        }
    }

    var minDist = Int.MAX_VALUE

    fun dfs(c: Int, m: Int, curDist: IntArray) {
        if (m == M) {
            minDist = curDist.sum().coerceAtMost(minDist)
            return
        }
        // c == C 가 같거나 전부 선택해도 M 개가 되지 않는 경우 종료
        else if (c == C || C - c < M - m) {
            return
        }

        // 지금까지의 최소 거리를 미리 계산
        val newDist = IntArray(H) {
            dist[c][it].coerceAtMost(curDist[it])
        }

        dfs(c + 1, m + 1, newDist)
        dfs(c + 1, m, curDist.clone())
    }

    dfs(0, 0, IntArray(H) { Int.MAX_VALUE })
    println(minDist)
}