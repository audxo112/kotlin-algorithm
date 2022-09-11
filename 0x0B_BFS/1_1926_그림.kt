import java.util.*

fun main() = with(System.`in`.bufferedReader()) {

    val bw = System.out.bufferedWriter()
    val nums = readLine().split(" ").map { it.toInt() }

    val array = mutableListOf<List<Int>>()
    val visited = mutableListOf<MutableList<Boolean>>()

    for (i in 0 until nums[0]) {
        array.add(readLine().split(" ").map { it.toInt() })
    }

    for (i in 0 until nums[0]) {
        visited.add(mutableListOf())
        for (j in 0 until nums[1]) {
            visited[i].add(false)
        }
    }
    val (count, size) = findNumberAndMaxSizeOfPainting(array, visited)

    bw.write("$count")
    bw.newLine()
    bw.write("$size")
    bw.flush()
    bw.close()

}

fun findNumberAndMaxSizeOfPainting(array: MutableList<List<Int>>, visited: MutableList<MutableList<Boolean>>): Pair<Int, Int> {
    var count = 0
    var maxSize = 0
    for (i in 0 until array.size) {
        for (j in 0 until array[i].size) {
            if (array[i][j] == 1 && !visited[i][j]) {
                val size = dfsStack(i, j, array, visited)
                if (size > maxSize) {
                    maxSize = size
                }
                count++
            }
        }
    }
    return Pair(count, maxSize)
}

val offset = arrayOf(
    arrayOf(1, 0),
    arrayOf(0, 1),
    arrayOf(-1, 0),
    arrayOf(0, -1)
)
fun dfsRecur(i: Int, j: Int, array: MutableList<List<Int>>, visited: MutableList<MutableList<Boolean>>) {
    if (visited[i][j]) {
        return
    }

    visited[i][j] = true

    for (off in offset) {
        val newI = i + off[0]
        val newJ = j + off[1]
        if (isPossibleIndex(newI, newJ, array) && array[newI][newJ] == 1) {
            dfsRecur(newI, newJ, array, visited)
        }
    }
}

fun dfsStack(i: Int, j: Int, array: MutableList<List<Int>>, visited: MutableList<MutableList<Boolean>>): Int {
    val st = Stack<Pair<Int, Int>>()

    st.push(Pair(i, j))

    var sum = 0
    while (st.isNotEmpty()) {
        val (x, y) = st.pop()

        if (visited[x][y]) {
            continue
        }
        sum++
        visited[x][y] = true

        for (off in offset) {
            val newX = x + off[0]
            val newY = y + off[1]
            if (isPossibleIndex(newX, newY, array) && array[newX][newY] == 1) {
                st.push(Pair(newX, newY))
            }
        }
    }
    return sum
}

fun isPossibleIndex(i: Int, j: Int, array: MutableList<List<Int>>): Boolean {

    if (i < 0 || i >= array.size) {
        return false
    }
    if (j < 0 || j >= array[i].size) {
        return false
    }

    return true
}