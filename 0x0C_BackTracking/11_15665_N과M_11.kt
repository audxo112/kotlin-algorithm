private lateinit var arr: List<Int>
private lateinit var answer: ArrayList<Int>
private lateinit var visited: BooleanArray
private val sb =  StringBuilder()

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    arr = readln().split(" ").map { it.toInt() }.sorted()
    answer = ArrayList()
    visited = BooleanArray(n)

    backtrack(0, n,m)
    println(sb.toString())
}

private fun backtrack(k: Int, n:Int, m: Int) {
    if (answer.size == m) {
        answer.forEach { sb.append("$it ") }
        sb.append("\n")
        return
    }
    var lNum = 0
    for (i in 0 until n) {
        if (lNum != arr[i]){
            answer.add(arr[i])
            lNum = arr[i]
            backtrack(i+1,n,m)
            answer.removeLast()
        }
    }
}