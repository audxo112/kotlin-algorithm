package `kotlin-algorithm`.`0x0C_BackTracking`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var res: CharArray
private lateinit var arr: CharArray
private val sb = StringBuilder()
private val vowelSet = setOf('a', 'e', 'i', 'o', 'u')

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val l = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    res = CharArray(l)

    st = StringTokenizer(br.readLine())
    arr = CharArray(c) { st.nextToken()[0] }
    arr.sort()

    dfs(0, 0, 0)

    println(sb.toString())
}

private fun dfs(idx: Int, start: Int, vowelCount: Int) {
    if (idx == res.size) {
        if (vowelCount < 1 || res.size - vowelCount < 2) return

        res.forEach {
            sb.append(it)
        }
        sb.append('\n')
        return
    }

    for (i in start until arr.size) {
        res[idx] = arr[i]

        if (vowelSet.contains(arr[i])) {
            dfs(idx + 1, i + 1, vowelCount + 1)
        } else {
            dfs(idx + 1, i + 1, vowelCount)
        }
    }
}

private fun isAppropriate(): Boolean {
    var vowelCount = 0
    var consonantCount = 0
    res.forEach {
        when(it) {
            'a', 'e', 'i', 'o', 'u' -> vowelCount++
            else -> consonantCount++
        }
    }

    return vowelCount >= 1 && consonantCount >= 2
}