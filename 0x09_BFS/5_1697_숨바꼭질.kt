import java.util.*

private val dx = mutableListOf(-1,1)

fun main(){
    val (n,k) = readln().split(" ").map{it.toInt()}

    val graph = IntArray(100001) {0}
    val check = BooleanArray(100001){false}

    graph[k] = -2

    if (n == k){
        println(0)
    } else{
        println(bfs(n,graph, check))
    }
}

private fun bfs(n: Int,graph:IntArray ,check:BooleanArray):Int{
    val q = LinkedList<Int>()
    var nx : Int
    q.offer(n)

    while (q.isNotEmpty()){
        val x = q.poll()

        for (i in 0..2){
            nx = if (i == 2){
                x * 2
            } else{
                x + dx[i]
            }

            if (nx > 10000 || nx < 0 || check[nx]){
                continue
            }
            else if(graph[nx] != -2){
                graph[nx] = graph[x] +1
                q.offer(nx)
                check[nx] = true
            }
            else return graph[x] +1
        }
    }
    return 0
}