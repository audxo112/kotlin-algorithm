// https://www.acmicpc.net/problem/14891
package solution14891__code2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private const val CR = 1
private const val RCR = -1

// 배열의 값을 이동하지 않고 순환큐처럼 index를 이동하는 방식으로 구현
private class Gear(
    val info: String,
    private var leftIndex: Int = 6,
    private var rightIndex: Int = 2
) {
    val left
        get() = info[leftIndex]

    val right
        get() = info[rightIndex]

    // 한번밖에 쓰지 않으니 rotate 에서 매번 구하는게 더 비효율적
    val top
        get() = info[(leftIndex + 2) % info.length]

    fun rotate(dir: Int) {
        if (dir == CR) {
            leftIndex = (leftIndex + info.length - 1) % info.length
            rightIndex = (rightIndex + info.length - 1) % info.length
        } else if (dir == RCR) {
            leftIndex = (leftIndex + 1) % info.length
            rightIndex = (rightIndex + 1) % info.length
        }
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val gears = Array(4) {
        Gear(br.readLine())
    }

    // start, end 를 만들어서 역류하기 않게
    fun rotate(index: Int, dir: Int, start: Int, end: Int) {
        if (index > start) {
            // 객체를 이용 하기 때문에 좌, 우 비교가 간편
            if (gears[index - 1].right != gears[index].left) {
                rotate(index - 1, -dir, start, index - 1)
            }
        }
        if (index < end) {
            if (gears[index + 1].left != gears[index].right) {
                rotate(index + 1, -dir, index + 1, end)
            }
        }
        // 각각 기어의 회전은 기어에게 맡김
        gears[index].rotate(dir)
    }

    repeat(br.readLine().toInt()) {
        val tokenizer = StringTokenizer(br.readLine(), " ")
        val num = tokenizer.nextToken().toInt()
        val dir = tokenizer.nextToken().toInt()
        rotate(num - 1, dir, 0, 3)
    }

    var total = 0
    for (i in 0 until 4) {
        if (gears[i].top == '1') {
            total += 1 shl i
        }
    }
    println(total)
}