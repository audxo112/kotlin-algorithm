//https://www.acmicpc.net/problem/4179
package solution4179

private data class Point(val x:Int, val y:Int)

private val dirs = arrayOf(
    1 to 0, 0 to -1, -1 to 0, 0 to 1
)

private fun solution(r:Int, c:Int, graph:Array<CharArray>, person:ArrayDeque<Point>, fire:ArrayDeque<Point>) : String{
    val (sx, sy) = person.first()
    if(sx == 0 || sx == c - 1 || sy == 0 || sy == r - 1){
        return "1"
    }

    val visited = Array(r){ y ->
        BooleanArray(c){ x ->
            graph[y][x] == '#' || graph[y][x] == 'F'
        }
    }
    visited[sy][sx] = true

    var time = 1
    while(person.isNotEmpty()){
        time += 1
        repeat(fire.size){
            val (x, y) = fire.removeFirst()

            for ((dx, dy) in dirs){
                val nx = x + dx
                val ny = y + dy

                if(nx !in 0 until c || ny !in 0 until r || graph[ny][nx] != '.'){
                    continue
                }

                graph[ny][nx] = 'F'
                fire.add(Point(nx, ny))
            }
        }

        repeat(person.size){
            val (x, y) = person.removeFirst()

            for ((dx, dy) in dirs){
                val nx = x + dx
                val ny = y + dy

                if(nx !in 0 until c || ny !in 0 until r || visited[ny][nx] || graph[ny][nx] != '.'){
                    continue
                }

                if(nx == 0 || nx == c - 1 || ny == 0 || ny == r - 1){
                    return time.toString()
                }

                visited[ny][nx] = true
                person.add(Point(nx, ny))
            }
        }
    }

    return "IMPOSSIBLE"
}

private fun main() = with(System.`in`.bufferedReader()){
    val (r, c) = readLine().split(" ").map {
        it.toInt()
    }

    val person = ArrayDeque<Point>()
    val fire = ArrayDeque<Point>()

    val graph = Array(r){ y ->
        val line = readLine()
        CharArray(c){ x ->
            when(val char = line[x]){
                'F' -> {
                    fire.add(Point(x, y))
                    char
                }
                'J' -> {
                    person.add(Point(x, y))
                    '.'
                }
                else -> char
            }
        }
    }

    println(solution(r, c, graph, person, fire))
}

