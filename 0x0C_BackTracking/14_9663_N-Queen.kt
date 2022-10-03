import java.util.*
import kotlin.math.abs

//https://chanhuiseok.github.io/posts/baek-1/


private var result = 0
private val board = IntArray(15)
fun main() = with(System.`in`.bufferedReader()) {

    val n = readLine().toInt()

    nqueen(0, n)

    println(result)
}

private fun nqueen(depth: Int, n: Int) {

    if (depth == n) {
        result++
        return
    }

    for (i in 0 until n) {
        board[depth] = i
        if (isValid(depth)) {
            nqueen(depth + 1, n)
        }
    }
}

private fun isValid(i: Int): Boolean {
    for (k in 0 until i) {
        if (board[i] == board[k]) {
            return false
        }
        if (i - k == abs(board[i] - board[k])) {
            return false
        }
    }
    return true
}