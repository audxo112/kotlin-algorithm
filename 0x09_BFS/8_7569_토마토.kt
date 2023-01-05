//https://www.acmicpc.net/problem/7569
package solution7569

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.Queue

class Point(val x: Int, val y: Int, val z: Int)

val dx = intArrayOf(1, 0, -1, 0, 0, 0)
val dy = intArrayOf(0, -1, 0, 1, 0, 0)
val dz = intArrayOf(0, 0, 0, 0, 1, -1)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val m = input()
    val n = input()
    val h = input()
    var zero = 0
    val queue: Queue<Point> = LinkedList()

    // x, y, z 에 대해서 3차원 배열 생성
    val tomato = Array(h) {z ->
        Array(n) {y ->
            IntArray(m) {x ->
                input().also {
                    // 미리 0, 1에 대해서 처리를 해준다
                    if (it == 0) {
                        zero += 1
                    } else if(it == 1){
                        queue.add(Point(x, y, z))
                    }
                }
            }
        }
    }

    // zero 가 없다면 끝
    if(zero == 0){
        println(0)
        return@run
    }

    var date = -1
    while(queue.isNotEmpty()){
        date += 1
        repeat(queue.size){
            val cur = queue.poll()
            for(i in 0 until 6){
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                val nz = cur.z + dz[i]

                if(nx !in 0 until m || ny !in 0 until n || nz !in 0 until h || tomato[nz][ny][nx] != 0){
                    continue
                }

                zero -= 1
                tomato[nz][ny][nx] = 1
                queue.add(Point(nx, ny, nz))
            }
        }
    }

    println(if(zero == 0) date else -1)
}