private lateinit var arr: List<Int>
private lateinit var answer: IntArray
private lateinit var visited: BooleanArray
private val sb =  StringBuilder()

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    arr = readln().split(" ").map { it.toInt() }.sorted()
    answer = IntArray(m)
    visited = BooleanArray(n)

    backtrack(0,n)
    println(sb.toString())
}

private fun backtrack(k: Int, n:Int) {
    if (k == answer.size) {
        answer.forEach { sb.append("$it ") }
        sb.append("\n")
        return
    }

    for (i in 0 until n) {
        if(!visited[i]){
            visited[i] = true
            answer[k] = arr[i]
            backtrack(k+1,n)
            visited[i] = false
        }
    }
}