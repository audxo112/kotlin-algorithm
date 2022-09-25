
private lateinit var arr : IntArray
private var n = 0
private var m = 0

fun main(){
    val input = readln().split(" ").map{it.toInt()}
    n = input[0]
    m = input[1]


    arr = IntArray(m)

    dfs(0,1)
}

private fun dfs(x: Int, start: Int) {
    if (x == m) {
        print("${arr.joinToString(" ")}\n")
        return
    } else {
        for (i in start..n) {
            arr[x] = i
            dfs(x + 1, i + 1)
        }
    }
}