// https://www.acmicpc.net/problem/14499
package solution14499

import java.io.StreamTokenizer

private val E = 1
private val W = 2
private val N = 3
private val S = 4

// 0, 0 은 tmp 값을 저장
// 2, 2 은 top 위치에 있는 값을 저장

private class Dice {
    private val pattern = Array(3) { IntArray(3) }

    val top
        get() = pattern[2][2]

    fun eastRoll() {
        pattern[0][0] = pattern[1][0]
        pattern[1][0] = pattern[1][1]
        pattern[1][1] = pattern[1][2]
        pattern[1][2] = pattern[2][2]
        pattern[2][2] = pattern[0][0]
    }

    fun westRoll() {
        pattern[0][0] = pattern[1][2]
        pattern[1][2] = pattern[1][1]
        pattern[1][1] = pattern[1][0]
        pattern[1][0] = pattern[2][2]
        pattern[2][2] = pattern[0][0]
    }

    fun northRoll() {
        pattern[0][0] = pattern[2][1]
        pattern[2][1] = pattern[1][1]
        pattern[1][1] = pattern[0][1]
        pattern[0][1] = pattern[2][2]
        pattern[2][2] = pattern[0][0]
    }

    fun southRoll() {
        pattern[0][0] = pattern[0][1]
        pattern[0][1] = pattern[1][1]
        pattern[1][1] = pattern[2][1]
        pattern[2][1] = pattern[2][2]
        pattern[2][2] = pattern[0][0]
    }

    fun play(value: Int): Int {
        if (value == 0) {
            return pattern[1][1]
        }
        pattern[1][1] = value
        return 0
    }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    // x, y 위치 잘보기
    var x = input()
    var y = input()
    val k = input()

    val dice = Dice()
    val map = Array(n){ IntArray(m){ input() } }

    val dirs = Array(k) { input() }

    val sb = StringBuilder()

    fun play(){
        map[x][y] = dice.play(map[x][y])
        sb.appendLine(dice.top)
    }

    for(dir in dirs){
        when{
            dir == E && y + 1 < m -> {
                y += 1
                dice.eastRoll()
                play()
            }
            dir == W && y - 1 >= 0 -> {
                y -= 1
                dice.westRoll()
                play()
            }
            dir == N && x - 1 >= 0 -> {
                x -= 1
                dice.northRoll()
                play()
            }
            dir == S && x + 1 < n -> {
                x += 1
                dice.southRoll()
                play()
            }
        }
    }

    println(sb.toString())
}
