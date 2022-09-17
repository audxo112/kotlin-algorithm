
import java.util.*

private val dx = listOf(0,0,1,-1)
private val dy = listOf(1,-1,0,0)


fun main(){
    val (n,m) = readln().split(" ").map { it.toInt() }

    val graph = Array(n) {Array(m) {"#"} }
    for (i in 0..n){
        val line = readln().chunked(1)
        line.forEachIndexed{index,it ->
            graph[i][index] = it
        }
    }
}

private fun bfsj(x:Int,y:Int,n:Int,m:Int,graph:Array<Array<String>>){

    val j = LinkedList<Pair<Int,Int>>()
    j.offer(x to y)

    while (j.isNotEmpty()){
        val (x,y) = j.poll()

        for (i in 0 until 4){
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx in 0 until n && ny in 0 until m){
                if (graph[nx][ny] == "."){
                    j.add(nx to ny)
                }
            }
        }
    }
}

private fun bfsf(x:Int,y:Int,n:Int,m:Int,graph:Array<Array<String>>){

    val f = LinkedList<Pair<Int,Int>>()
    f.offer(x to y)

    while (f.isNotEmpty()){
        val (x,y) = f.poll()

        for (i in 0 until 4){
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx in 0 until n && ny in 0 until m){
                if (graph[nx][ny] == "."){
                    f.add(nx to ny)
                }
            }
        }
    }
}