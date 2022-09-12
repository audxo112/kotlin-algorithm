import java.util.*


val offset = arrayOf(
    arrayOf(1, 0),
    arrayOf(0, 1),
    arrayOf(-1, 0),
    arrayOf(0, -1)
)

fun main() = with(System.`in`.bufferedReader()) {

    val bw = System.out.bufferedWriter()
    val nums = readLine().split(" ").map { it.toInt() }

    val array = Array(nums[0]) { IntArray(nums[1]) }
    val visited = Array(nums[0]) { BooleanArray(nums[1]) }

    for (i in array.indices) {
        array[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val (count, size) = findNumberAndMaxSizeOfPainting(array, visited)

    bw.write("$count")
    bw.newLine()
    bw.write("$size")
    bw.flush()
    bw.close()

}

fun findNumberAndMaxSizeOfPainting(array: Array<IntArray>, visited: Array<BooleanArray>): Pair<Int, Int> {
    var count = 0
    var maxSize = 0
    for (i in array.indices) {
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

fun dfsStack(i: Int, j: Int, array: Array<IntArray>, visited: Array<BooleanArray>): Int {
    val st = Stack<Pair<Int, Int>>()

    st.push(i to j)

    var sum = 0
    while (st.isNotEmpty()) {
        val (x, y) = st.pop()

        if (visited[x][y]) {
            continue
        }
        sum++
        visited[x][y] = true

        for (off in offset) {
            val nx = x + off[0]
            val ny = y + off[1]
            if (nx in array.indices && ny in array[0].indices && array[nx][ny] == 1) {
                st.push(nx to ny)
            }
        }
    }
    return sum
}
