private lateinit var arr: List<Int>
private lateinit var answer: ArrayList<Int>
private val sb =  StringBuilder()

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    arr = readln().split(" ").map { it.toInt() }.sorted()
    answer = ArrayList()

    backtrack(0,n,m)
    println(sb.toString())
}

private fun backtrack(k: Int, n:Int, m: Int) {
    if (answer.size == m) {
        answer.forEach { sb.append("$it ") }
        sb.append("\n")
        return
    }

    for (i in 0 until n) {
            answer.add(arr[i])
            backtrack(i, n, m)
            answer.removeLast()
    }
}