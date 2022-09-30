import kotlin.math.abs

private lateinit var queen: IntArray
private var count = 0

fun main(){
    val n = readln().toInt()

    queen = IntArray(n)

    dfs(n,0)
    println(count)
}

private fun dfs(n: Int, y: Int){
    if (n == y){
        count++
        return
    }
    for (pos in 0 until n){
        if(checkPos(y,pos)){
            queen[y] = pos
            dfs(n,y+1)
        }
    }
}

private fun checkPos(row: Int, pos: Int): Boolean{
    for (i in 0 until row){
        if(queen[i] == pos || row - i == abs(queen[i] - pos))
            return false
    }
    return true
}