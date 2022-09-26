//https://www.acmicpc.net/problem/4179
package solution4179


import java.util.StringTokenizer

private class Point(val x:Int, val y:Int)


private val dx = intArrayOf(0, 1, 0, -1)
private val dy = intArrayOf(1, 0, -1, 0)


private fun solution(r:Int, c:Int, graph:Array<CharArray>, person:ArrayDeque<Point>, fire:ArrayDeque<Point>) : String{
    val sPos = person.first()
    if(sPos.x == 0 || sPos.x == c - 1 || sPos.y == 0 || sPos.y == r - 1){
        return "1"
    }

    val visited = Array(r){ y ->
        BooleanArray(c){ x ->
            graph[y][x] == '#' || graph[y][x] == 'F'
        }
    }
    visited[sPos.y][sPos.x] = true

    var time = 1
    while(person.isNotEmpty()){
        time += 1
        repeat(fire.size){
            val fPos = fire.removeFirst()

            for (dir in 0 .. 3){
                val nx = fPos.x + dx[dir]
                val ny = fPos.y + dy[dir]

                if(nx < 0 || nx >= c || ny < 0 || ny >= r || graph[ny][nx] != '.'){
                    continue
                }

                graph[ny][nx] = 'F'
                fire.add(Point(nx, ny))
            }
        }

        repeat(person.size){
            val pPos = person.removeFirst()

            for (dir in 0 .. 3){
                val nx = pPos.x + dx[dir]
                val ny = pPos.y + dy[dir]

                if(nx < 0 || nx >= c || ny < 0 || ny >= r || visited[ny][nx] || graph[ny][nx] != '.'){
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
    val tokenizer = StringTokenizer(readLine(), " ")
    val r = tokenizer.nextToken().toInt()
    val c = tokenizer.nextToken().toInt()

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

