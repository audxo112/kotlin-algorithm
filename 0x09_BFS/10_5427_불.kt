package solve_5427

import java.util.*

private val dx = arrayOf(1, -1, 0, 0)
private val dy = arrayOf(0, 0, 1, -1)
private lateinit var building: List<CharArray>
private lateinit var fireTime: List<IntArray>
private lateinit var personTime: List<IntArray>

private data class Node(val x: Int, val y: Int)

fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        val st = StringTokenizer(readLine(), " ")
        val w = st.nextToken().toInt()
        val h = st.nextToken().toInt()

        building = List(h) {
            readLine().toCharArray()
        }
        fireTime = List(h) {
            IntArray(w)
        }
        personTime = List(h) {
            IntArray(w)
        }


        val personQueue: Queue<Node> = LinkedList()
        val fireQueue: Queue<Node> = LinkedList()

        for (i in 0 until w) {
            for (j in 0 until h) {
                when (building[j][i]) {
                    '*' -> {
                        fireQueue.add(Node(i, j))
                        fireTime[j][i] = 1
                    }
                    '@' -> {
                        personQueue.add(Node(i, j))
                        personTime[j][i] = 1
                    }
                }
            }
        }

        fireBFS(fireQueue)
        val answer = personBFS(personQueue)

        if (answer == null) {
            println("IMPOSSIBLE")
        } else {
            println(answer)
        }
    }
}

private fun fireBFS(queue: Queue<Node>) {
    while (queue.isNotEmpty()) {
        val nowFire = queue.poll()
        for (i in dx.indices) {
            val nx = nowFire.x + dx[i]
            val ny = nowFire.y + dy[i]
            if (nx < 0 || nx > building[0].lastIndex) continue
            if (ny < 0 || ny > building.lastIndex) continue
            if (building[ny][nx] == '#' || fireTime[ny][nx] != 0) continue

            if (fireTime[ny][nx] < fireTime[nowFire.y][nowFire.x] + 1) {
                fireTime[ny][nx] = fireTime[nowFire.y][nowFire.x] + 1
                queue.add(Node(nx, ny))
            }
        }
    }
}

private fun personBFS(queue: Queue<Node>): Int? {

    while (queue.isNotEmpty()) {
        val nowPerson = queue.poll()

        if (nowPerson.x == 0 || nowPerson.y == 0 || nowPerson.x == building[0].lastIndex || nowPerson.y == building.lastIndex) {
            return personTime[nowPerson.y][nowPerson.x]
        }

        for (i in dx.indices) {
            val nx = nowPerson.x + dx[i]
            val ny = nowPerson.y + dy[i]
            if (nx < 0 || nx > building[0].lastIndex) continue
            if (ny < 0 || ny > building.lastIndex) continue
            if (fireTime[ny][nx] != 0 && personTime[nowPerson.y][nowPerson.x] + 1 >= fireTime[ny][nx]) continue
            if (building[ny][nx] == '#' || building[ny][nx] == '*' || personTime[ny][nx] != 0) continue

            if (personTime[ny][nx] < personTime[nowPerson.y][nowPerson.x] + 1) {
                personTime[ny][nx] = personTime[nowPerson.y][nowPerson.x] + 1
                queue.add(Node(nx, ny))
            }
        }
    }
    return null
}