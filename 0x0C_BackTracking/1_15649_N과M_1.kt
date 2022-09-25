
private lateinit var arr : IntArray
private lateinit var visited : BooleanArray

fun main(){
    val (n,m) = readln().split(" ").map { it.toInt() }
    arr = IntArray(m)
    visited = BooleanArray(n)
    backtrack(0,n,m)
}

private fun backtrack(k:Int, n:Int, m:Int){
    if (k==m){
        arr.forEach { print("$it ") }
        println()
        return
    }

    for (i in 1..n){
        if(!visited[i - 1]){
            arr[k] = i
            visited[i - 1] = true
            backtrack(k + 1,n,m)
            visited[i - 1] = false
        }
    }
}