private lateinit var arr: IntArray
private val sb =  StringBuilder()

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    arr = IntArray(m)

    backtrack(n, 0,0)
    println(sb.toString())
}

private fun backtrack(n: Int, k: Int,l: Int) {
    if (k == arr.size) {
        arr.forEach { sb.append("$it ") }
        sb.append("\n")
        return
    }

    for (i in l until n) {
        arr[k] = i + 1
        backtrack(n, k + 1,i)
    }
}