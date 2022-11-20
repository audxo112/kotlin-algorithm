package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val nA = st.nextToken().toInt()
    val nB = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val arrA = IntArray(nA) {
        st.nextToken().toInt()
    }

    st = StringTokenizer(br.readLine())
    val arrB = IntArray(nB) {
        st.nextToken().toInt()
    }

    arrB.sort()

    val res = mutableListOf<Int>()
    arrA.forEach {
        if (arrB.binarySearch(it) < 0) {
            res.add(it)
        }
    }

    res.sort() // PriorityQueue 보다 빨랐다.

    val sb = StringBuilder()
    sb.appendLine(res.size)
    res.forEach {
        sb.append("$it ")
    }

    println(sb.toString())
}

private fun solution1() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val nA = st.nextToken().toInt()
    val nB = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val arrA = IntArray(nA) {
        st.nextToken().toInt()
    }

    val set = mutableSetOf<Int>()
    st = StringTokenizer(br.readLine())
    repeat(nB) {
        set.add(st.nextToken().toInt())
    }

    val que = PriorityQueue<Int>()
    arrA.forEach {
        if (set.contains(it).not()) {
            que.offer(it)
        }
    }

    val sb = StringBuilder()
    sb.appendLine(que.size)
    while (que.isNotEmpty()) {
        sb.append("${que.poll()} ")
    }

    println(sb.toString())
}