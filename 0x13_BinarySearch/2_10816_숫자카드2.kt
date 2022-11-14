package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())
    val cards = IntArray(n) {
        st.nextToken().toInt()
    }

    cards.sort()

    val m = br.readLine().toInt()
    val sb = StringBuilder()
    st = StringTokenizer(br.readLine())

    repeat(m) {
        val num = st.nextToken().toInt()
        val res = foundUpperBound(cards, num) - foundLowerBound(cards, num)
        sb.append("$res ")
    }

    println(sb.toString())
}

private fun solution1(cards: IntArray, num: Int): Int {
    var start = 0
    var end = cards.lastIndex
    var mid = 0
    var count = 0

    while (start <= end) {
        mid = (start + end) / 2

        when {
            num == cards[mid] -> {
                count++

                var idx = mid + 1
                while (idx < cards.size && num == cards[idx]) {
                    idx++
                    count++
                }

                idx = mid - 1
                while (idx >= 0 && num == cards[idx]) {
                    idx--
                    count++
                }
                break
            }

            num < cards[mid] -> {
                end = mid - 1
            }

            else -> {
                start = mid + 1
            }
        }
    }

    return count
}

private fun foundLowerBound(cards: IntArray, num: Int): Int {
    var start = 0
    var end = cards.lastIndex
    var mid = 0

    while (start <= end) {
        mid = (start + end) / 2
        if (num <= cards[mid]) {
            end = mid - 1
        } else {
            start = mid + 1
        }
    }

    return start
}

private fun foundUpperBound(cards: IntArray, num: Int): Int {
    var start = 0
    var end = cards.lastIndex
    var mid = 0

    while (start <= end) {
        mid = (start + end) / 2
        if (num < cards[mid]) {
            end = mid - 1
        } else {
            start = mid + 1
        }
    }

    return start
}