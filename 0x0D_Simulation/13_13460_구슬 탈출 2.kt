//@file:Suppress("IMPLICIT_CAST_TO_ANY")
//
//package solve_13460
//
//import java.util.*
//
//private data class Node(var x: Int, var y: Int)
//private class MapInfo(var red: Node, var blue: Node, val goal: Node, val map: Array<CharArray>)
//
//private lateinit var map: Array<CharArray>
//
//private val dx = arrayOf(-1, 1, 0, 0)
//private val dy = arrayOf(0, 0, -1, 1)
//
//private const val LEFT = 0
//private const val RIGHT = 1
//private const val UP = 2
//private const val DOWN = 3
//
//fun main() = with(System.`in`.bufferedReader()) {
//    val st = StringTokenizer(readLine())
//    val n = st.nextToken().toInt()
//    val m = st.nextToken().toInt()
//
//    map = Array(n) {
//        CharArray(m)
//    }
//
//    var red = Node(1, 1)
//    var blue = Node(1, 1)
//    var goal = Node(1, 1)
//
//    for (i in 0 until n) {
//        val input = readLine()
//        for (j in 0 until m) {
//            map[i][j] = input[j]
//            when (input[j]) {
//                'R' -> red = Node(j, i)
//                'B' -> blue = Node(j, i)
//                'O' -> goal = Node(j, i)
//            }
//        }
//    }
//}
//
//private fun move(marble: Node, goal: Node, dir: Int, newMap: Array<CharArray>): Node {
//    var nx = marble.x
//    var ny = marble.y
//
//    val nowColor = newMap[ny][nx]
//
//    do {
//        if (nx == goal.x && ny == goal.y) {
//            return Node(nx, ny)
//        }
//        nx += dx[dir]
//        ny += dy[dir]
//    } while (nx in 0 until newMap[0].size && ny in newMap.indices && newMap[ny][nx] == '.' || newMap[ny][nx] == 'O')
//
//    nx -= dx[dir]
//    ny -= dy[dir]
//    newMap[ny][nx] = nowColor
//    return Node(nx, ny)
//}
//
//private fun tiltMap(mapInfo: MapInfo, dir: Int): Int {
//    val sequence = getSequence(mapInfo.red, mapInfo.blue, dir)
//    val newMap = mapInfo.map.copy()
//    for (marble in sequence) {
//        move(marble, mapInfo.goal, dir, newMap)
//    }
//
//}
//
//private fun getSequence(red: Node, blue: Node, dir: Int): ArrayList<Node> {
//    val sequence =
//        when (dir) {
//            LEFT -> {
//                if (red.x < blue.x) {
//                    arrayListOf(red, blue)
//                } else {
//                    arrayListOf(blue, red)
//                }
//            }
//            RIGHT -> {
//                if (red.x < blue.x) {
//                    arrayListOf(blue, red)
//                } else {
//                    arrayListOf(red, blue)
//                }
//            }
//            UP -> {
//                if (red.y < blue.y) {
//                    arrayListOf(red, blue)
//                } else {
//                    arrayListOf(blue, red)
//                }
//            }
//            else -> {
//                if (red.y < blue.y) {
//                    arrayListOf(blue, red)
//                } else {
//                    arrayListOf(red, blue)
//                }
//            }
//        }
//    return sequence
//}
//
//
//private fun Array<CharArray>.copy() =
//    Array(this.size) {
//        this[it].copyOf()
//    }