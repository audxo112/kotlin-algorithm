import java.util.*

fun main() = with(System.`in`.bufferedReader()) {

    val bw = System.out.bufferedWriter()
    val nums = readLine().split(" ").map { it.toInt() }

    val array = mutableListOf<String>()
    val visited = mutableListOf<MutableList<Boolean>>()
    val checked = mutableListOf<MutableList<Boolean>>()
    val depth = mutableListOf<MutableList<Int>>()

    for (i in 0 until nums[0]) {
        array.add(readLine())
    }

    for (i in 0 until nums[0]) {
        visited.add(mutableListOf())
        checked.add(mutableListOf())
        depth.add(mutableListOf())
        for (j in 0 until nums[1]) {
            visited[i].add(false)
            checked[i].add(false)
            depth[i].add(1)
        }
    }
    bfs(0, 0, array, visited, depth, checked)

    bw.write("${depth[nums[0] - 1][nums[1] - 1]}")
    bw.flush()
    bw.close()

}

fun bfs(
    i: Int,
    j: Int,
    array: MutableList<String>,
    visited: MutableList<MutableList<Boolean>>,
    depth: MutableList<MutableList<Int>>,
    checked: MutableList<MutableList<Boolean>>
) {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(Pair(i, j))

    while (queue.isNotEmpty()) {
        val (x, y) = queue.remove()

        if (visited[x][y]) {
            continue
        }
        visited[x][y] = true

        for (off in offset) {
            val newX = x + off[0]
            val newY = y + off[1]
            if (isPossibleIndex(newX, newY, array) && array[newX][newY] == '1' && !checked[newX][newY]) {
                queue.add(Pair(newX, newY))
                checked[newX][newY] = true
                depth[newX][newY] = depth[x][y] + 1
            }
        }
    }

}

fun isPossibleIndex(i: Int, j: Int, array: MutableList<String>): Boolean {
    if (i < 0 || i >= array.size) {
        return false
    }
    if (j < 0 || j >= array[i].length) {
        return false
    }

    return true
}
