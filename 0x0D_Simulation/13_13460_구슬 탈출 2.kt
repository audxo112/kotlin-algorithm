// https://www.acmicpc.net/problem/13460
package solution13460

import java.util.StringTokenizer
import java.util.Queue
import java.util.LinkedList

private class Node(val rx: Int, val ry: Int, val bx: Int, val by: Int, val result: Int) {
    val key = (rx shl 12) + (ry shl 8) + (bx shl 4) + by
}

private fun main() = with(System.`in`.bufferedReader()) {
    val tokenizer = StringTokenizer(readLine())
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    val map = Array(n) { y ->
        readLine().toCharArray()
    }

    // Node 를 새로 만들어준다
    var redX = 0
    var redY = 0
    var blueX = 0
    var blueY = 0
    for (y in 0 until n) {
        for (x in 0 until m) {
            when (map[y][x]) {
                'R' -> {
                    redX = x
                    redY = y
                    map[y][x] = '.'
                }

                'B' -> {
                    blueX = x
                    blueY = y
                    map[y][x] = '.'
                }
            }
        }
    }

    // 가로로 움직이는 경우
    fun moveHorizontal(x1: Int, y1: Int, x2: Int, y2: Int, dir: Int, rbOrder: Boolean): Node {
        var result = 0
        var nx1 = x1
        var nx2 = x2

        while (map[y1][nx1 + dir] == '.') {
            nx1 += dir
        }
        if (map[y1][nx1 + dir] == 'O') {
            nx1 += dir
            result += if (rbOrder) 1 else 2
        }
        // 지금 들어간 구슬이 blue 면 종료
        if (result == 2) {
            return Node(0, 0, 0, 0, 2)
        }

        // y 값이 같으면 부딛히는 상황이 나올 수 있음
        if (y1 == y2) {
            while (nx2 + dir != nx1 && map[y2][nx2 + dir] == '.') {
                nx2 += dir
            }
        } else {
            while (map[y2][nx2 + dir] == '.') {
                nx2 += dir
            }
        }

        if (map[y2][nx2 + dir] == 'O') {
            nx2 += dir
            result += if (rbOrder) 2 else 1
        }
        // rb 순서에 따라 node 를 만들어줌
        return if (rbOrder) {
            Node(nx1, y1, nx2, y2, result)
        } else {
            Node(nx2, y2, nx1, y1, result)
        }
    }

    // 세로로 움직이는 경우
    fun moveVertical(x1: Int, y1: Int, x2: Int, y2: Int, dir: Int, rbOrder: Boolean): Node {
        var result = 0
        var ny1 = y1
        var ny2 = y2

        while (map[ny1 + dir][x1] == '.') {
            ny1 += dir
        }
        if (map[ny1 + dir][x1] == 'O') {
            ny1 += dir
            result += if (rbOrder) 1 else 2
        }
        if (result == 2) {
            return Node(0, 0, 0, 0, 2)
        }

        if (x1 == x2) {
            while (ny2 + dir != ny1 && map[ny2 + dir][x2] == '.') {
                ny2 += dir
            }
        } else {
            while (map[ny2 + dir][x2] == '.') {
                ny2 += dir
            }
        }

        if (map[ny2 + dir][x2] == 'O') {
            ny2 += dir
            result += if (rbOrder) 2 else 1
        }

        return if (rbOrder) {
            Node(x1, ny1, x2, ny2, result)
        } else {
            Node(x2, ny2, x1, ny1, result)
        }
    }

    val node = Node(redX, redY, blueX, blueY, 0)
    val queue: Queue<Node> = LinkedList()
    queue.add(node)

    // bit 연산을 이용해서 key 를 만들고 방문여부를 확인할때 사용
    // xxxxyyyyXXXXYYYY
    val visited = HashSet<Int>()
    visited.add(node.key)
    var count = 0

    // node 를 이용하여 완료여부 판단
    fun complete(node: Node): Boolean {
        if (node.result == 1) {
            return true
        } else if (node.result == 0) {
            val key = node.key
            if (!visited.contains(key)) {
                visited.add(key)
                queue.add(node)
            }
        }
        return false
    }

    fun bfs(): Int {
        while (queue.isNotEmpty()) {
            count += 1
            if (count > 10) {
                return -1
            }

            // queue 개수 만큼 1회 진행
            // left, right, top, bottom 순으로 기울였을 경우 각각 구한다
            for (i in 0 until queue.size) {
                val cur = queue.poll()

                val lNext = if (cur.rx < cur.bx) {
                    moveHorizontal(cur.rx, cur.ry, cur.bx, cur.by, -1, true)
                } else {
                    moveHorizontal(cur.bx, cur.by, cur.rx, cur.ry, -1, false)
                }

                if (complete(lNext)) {
                    return count
                }

                val rNext = if (cur.rx > cur.bx) {
                    moveHorizontal(cur.rx, cur.ry, cur.bx, cur.by, 1, true)
                } else {
                    moveHorizontal(cur.bx, cur.by, cur.rx, cur.ry, 1, false)
                }

                if (complete(rNext)) {
                    return count
                }

                val tNext = if (cur.ry < cur.by) {
                    moveVertical(cur.rx, cur.ry, cur.bx, cur.by, -1, true)
                } else {
                    moveVertical(cur.bx, cur.by, cur.rx, cur.ry, -1, false)
                }

                if (complete(tNext)) {
                    return count
                }

                val bNext = if (cur.ry > cur.by) {
                    moveVertical(cur.rx, cur.ry, cur.bx, cur.by, 1, true)
                } else {
                    moveVertical(cur.bx, cur.by, cur.rx, cur.ry, 1, false)
                }

                if (complete(bNext)) {
                    return count
                }
            }
        }
        return -1
    }

    println(bfs())
}