// https://www.acmicpc.net/problem/14503
package solution14503

import java.io.StreamTokenizer

private const val NORTH = 0
private const val EAST = 1
private const val SOUTH = 2
private const val WEST = 3

private class Robot(var r: Int, var c: Int, var d: Int){

    val fr
        get() = when(d){
            NORTH -> r - 1
            SOUTH -> r + 1
            else -> r
        }
    val br
        get() = when(d){
            NORTH -> r + 1
            SOUTH -> r - 1
            else -> r
        }

    val fc
        get() = when(d){
            EAST -> c + 1
            WEST -> c - 1
            else -> c
        }

    val bc
        get() = when(d){
            EAST -> c - 1
            WEST -> c + 1
            else -> c
        }

    fun rotate(){
        d = when(d){
            NORTH -> WEST
            EAST -> NORTH
            WEST -> SOUTH
            else -> EAST
        }
    }
}

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val robot = Robot(input(), input(), input())

    val map = Array(n) {
        IntArray(m) {
            input()
        }
    }

    // 청소 여부를 저장
    val visited = Array(n){
        BooleanArray(m)
    }

    var count = 0
    while(true){
        if(!visited[robot.r][robot.c]){
            visited[robot.r][robot.c] = true
            count += 1
        }

        var findDust = false
        for(i in 0 until 4){
            // 현재 방향을 확인하는게 아니 반시계 방향부터 확인하기 때문에 rotate 부터
            robot.rotate()
            val fr = robot.fr
            val fc = robot.fc
            if(map[fr][fc] == 1 || visited[fr][fc]){
                continue
            }
            findDust = true
            robot.r = fr
            robot.c = fc
            break
        }

        if(!findDust){
            // 청소할 곳을 찾지 못하면 후진을 한다
            val br = robot.br
            val bc = robot.bc
            // 후진 방향이 벽이면 종료
            if(map[br][bc] == 1){
                break
            }
            robot.r = br
            robot.c = bc
        }
    }

    println(count)
}