package solve_12100

import java.io.StreamTokenizer

private var answer = Int.MIN_VALUE
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()

    val board = Array(n) {
        IntArray(n) {
            input()
        }
    }


    val a1 = leftShift(board)
    val a2 = upShift(a1)
    val a3 = leftShift(a2)
    val a4 = downShift(a3)
    val a5 = leftShift(a4)

    backTracking(1, board)
    println(answer)

}

private fun backTracking(depth: Int, board: Array<IntArray>) {
    if (depth == 5) {
        board.forEach { arr ->
            arr.forEach {
                answer = maxOf(it, answer)
            }
        }
        return
    }

    backTracking(depth + 1, leftShift(board))
    backTracking(depth + 1, rightShift(board))
    backTracking(depth + 1, upShift(board))
    backTracking(depth + 1, downShift(board))
}

private fun leftShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = mapCopy(board)

    for (i in newBoard.indices) {
        var nowIndex = 0
        for(j in 1 .. newBoard.lastIndex) {
            if(newBoard[i][j] != 0) {
                when(newBoard[i][nowIndex]) {
                    0 -> {
                        newBoard[i][nowIndex] = newBoard[i][j]
                        newBoard[i][j] = 0
                    }
                    newBoard[i][j] -> {
                        newBoard[i][nowIndex] *=2
                        nowIndex++
                        newBoard[i][j] = 0
                    }
                    else -> {
                        if(nowIndex +1 != j) {
                            newBoard[i][nowIndex +1] = newBoard[i][j]
                            newBoard[i][j] = 0
                        }
                        nowIndex++
                    }
                }
            }
        }
    }
    return newBoard
}

private fun rightShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = mapCopy(board)

    for (i in newBoard.indices) {
        var nowIndex = newBoard.lastIndex
        for(j in newBoard.lastIndex - 1 downTo 0) {
            if(newBoard[i][j] != 0) {
                when(newBoard[i][nowIndex]) {
                    0 ->{
                        newBoard[i][nowIndex] = newBoard[i][j]
                        newBoard[i][j] = 0
                    }
                    newBoard[i][j] -> {
                        newBoard[i][nowIndex] *=2
                        nowIndex--
                        newBoard[i][j] = 0
                    }
                    else -> {
                        if(nowIndex -1 > 0) {
                            newBoard[i][nowIndex -1] = newBoard[i][j]
                            newBoard[i][j] = 0
                        }
                        nowIndex--
                    }
                }
            }
        }
    }
    return newBoard
}

private fun upShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = mapCopy(board)

    for (j in newBoard.indices) {
        var nowIndex = 0
        for(i in 1 .. newBoard.lastIndex) {
            if(newBoard[i][j] != 0) {
                when(newBoard[nowIndex][j]) {
                    0 -> {
                        newBoard[nowIndex][j] = newBoard[i][j]
                        newBoard[i][j] = 0
                    }
                    newBoard[i][j] -> {
                        newBoard[nowIndex][j] *=2
                        nowIndex++
                        newBoard[i][j] = 0
                    }
                    else -> {
                        if(nowIndex +1 < i) {
                            newBoard[nowIndex + 1][j] = newBoard[i][j]
                            newBoard[i][j] = 0
                        }
                        nowIndex++
                    }
                }
            }
        }
    }
    return newBoard
}

private fun downShift(board: Array<IntArray>): Array<IntArray> {
    val newBoard = mapCopy(board)

    for (j in newBoard.indices) {
        var nowIndex = newBoard.lastIndex
        for(i in newBoard.lastIndex -1 downTo 1) {
            if(newBoard[i][j] != 0) {
                when(newBoard[nowIndex][j]) {
                    0 -> {
                        newBoard[nowIndex][j] = newBoard[i][j]
                        newBoard[i][j] = 0
                    }
                    newBoard[i][j] -> {
                        newBoard[nowIndex][j] *=2
                        nowIndex--
                        newBoard[i][j] = 0
                    }
                    else -> {
                        if(nowIndex -1 > 0) {
                            newBoard[nowIndex-1][j] = newBoard[i][j]
                            newBoard[i][j] = 0
                        }
                        nowIndex--
                    }
                }
            }
        }
    }
    return newBoard
}

private fun mapCopy(preMap: Array<IntArray>) = Array(preMap.size) { y ->
    IntArray(preMap[0].size) { x ->
        preMap[y][x]
    }
}