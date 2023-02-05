// https://www.acmicpc.net/problem/15686
package solution15686__code2

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.Queue
import java.util.Stack
import kotlin.math.abs

private class Node(val x: Int, val y: Int)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val M = input()

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
    val dist = Array(C) { c ->
        IntArray(H) { h ->
            abs(chicken[c].x - house[h].x) + abs(chicken[c].y - house[h].y)
        }
    }

    var minDist = Int.MAX_VALUE
    // 기존에는 Stack 을 이용해서 push, pop 을 진행했으나
    // 주영님 코드를 참고하여 배열로 변경
    // 212ms -> 132ms
    val selected = IntArray(M)

    // 마지막에 계산을 진행
    fun calculateDist(): Int {
        var totalDist = 0
        for (h in house.indices) {
            var min = Int.MAX_VALUE
            for (s in selected) {
                min = dist[s][h].coerceAtMost(min)
            }
            totalDist += min
        }
        return totalDist
    }

    fun dfs(c: Int, m: Int) {
        if (m == M) {
            minDist = calculateDist().coerceAtMost(minDist)
            return
        } else if (c == C || C - c < M - m) {
            return
        }

        selected[m] = c
        dfs(c + 1, m + 1)
        dfs(c + 1, m)
    }

    dfs(0, 0)
    println(minDist)
}