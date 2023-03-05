// https://www.acmicpc.net/problem/14888

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

private lateinit var nums: IntArray
private lateinit var ops: IntArray
private lateinit var arr: IntArray
private var max = Int.MIN_VALUE
private var min = Int.MAX_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())
    nums = IntArray(n) {
        st.nextToken().toInt()
    }
    st = StringTokenizer(br.readLine())
    ops = IntArray(4) {
        st.nextToken().toInt()
    }
    arr = IntArray(n - 1)

    dfs(0)

    println(max)
    println(min)
}

private fun dfs(idx: Int) {
    if (idx == arr.size) {
        val numList = LinkedList<Int>()
        nums.forEach {
            numList.add(it)
        }
        for (i in arr.indices) {
            val first = numList.poll()
            val second = numList.poll()
            when (arr[i]) {
                0 -> { // 덧셈
                    numList.addFirst(first + second)
                }

                1 -> { // 뺄셈
                    numList.addFirst(first - second)
                }

                2 -> { // 곱셈
                    numList.addFirst(first * second)
                }

                3 -> { // 나눗셈
                    numList.addFirst(
                        if (second < 0) {
                            first / -second
                        } else {
                            first / second
                        }
                    )
                }
            }
        }

        if (numList[0] < min) {
            min = numList[0]
        }
        if (numList[0] > max) {
            max = numList[0]
        }
        return
    }

    for (i in ops.indices) {
        if (ops[i] > 0) {
            ops[i]--
            arr[idx] = i
            dfs(idx + 1)
            ops[i]++
        }
    }
}