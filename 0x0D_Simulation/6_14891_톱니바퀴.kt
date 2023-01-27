// https://www.acmicpc.net/problem/14891
package solution14891

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val CR = 1
    val RCR = -1

    val count = 8
    val left = 6
    val right = 2

    val gears = arrayOf(
        br.readLine().toCharArray(),
        br.readLine().toCharArray(),
        br.readLine().toCharArray(),
        br.readLine().toCharArray()
    )

    // start, end 를 만들어서 역류하기 않게
    fun rotate(index: Int, dir: Int, start: Int, end: Int){
        if(index > start){
            if(gears[index - 1][right] != gears[index][left]){
                rotate(index - 1, -dir, start, index - 1)
            }
        }
        if(index < end){
            if(gears[index + 1][left] != gears[index][right]){
                rotate(index + 1, -dir, index + 1, end)
            }
        }

        // 시계 방향으로 회전 7번째 인덱스 부터 바꾸기 때문에 7번을 저장
        if(dir == CR){
            val tmp = gears[index][count - 1]
            for(i in count - 1 downTo 1){
                val next = (i + count - 1) % count
                gears[index][i] = gears[index][next]
            }
            gears[index][0] = tmp
        }
        // 반시계 방향으로 회전 0번째 인덱스 부터 바꾸기 때문에 0번을 저장
        else if(dir == RCR){
            val tmp = gears[index][0]
            for(i in 0 until count - 1){
                val next = (i + 1) % count
                gears[index][i] = gears[index][next]
            }
            gears[index][count - 1] = tmp
        }
    }

    repeat(br.readLine().toInt()){
        val tokenizer = StringTokenizer(br.readLine(), " ")
        val num = tokenizer.nextToken().toInt()
        val dir = tokenizer.nextToken().toInt()
        rotate(num - 1, dir, 0, 3)
    }

    // 그냥 bit 연산자로 계산해봄
    var total = 0
    for(i in 0 until 4){
        if(gears[i][0] == '1'){
            total += 1 shl i
        }
    }
    println(total)
}