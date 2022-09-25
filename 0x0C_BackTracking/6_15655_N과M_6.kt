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

    for (i in k until n) {
        if (answer.contains(arr[i]).not()){
            answer.add(arr[i])
            backtrack(i + 1, n, m)
            answer.removeLast()
        }
    }
}