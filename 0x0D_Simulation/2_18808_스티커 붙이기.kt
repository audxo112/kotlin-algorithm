package solve_18808

import java.io.StreamTokenizer

private lateinit var notebookBoard: Array<BooleanArray>
private var answer = 0

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input() //노트북 세로
    val m = input() //노트북 가로
    val k = input() // 스티커 개수

    notebookBoard = Array(n) {
        BooleanArray(m)
    }

    val stickerArray = Array(k) {
        val r = input()
        val c = input()
        Array(r) {
            IntArray(c) {
                input()
            }
        }
    }

    for (sticker in stickerArray) {
        var nowSticker = sticker
        for (rotateCnt in 0 until 4) {
            if (moveSticker(nowSticker)) {
                break
            }
            nowSticker = rotateSticker(nowSticker)
        }
    }

    println(answer)
}

private fun moveSticker(sticker: Array<IntArray>): Boolean {
    for (i in 0 until notebookBoard.size - sticker.size + 1) {
        for (j in 0 until notebookBoard[0].size - sticker[0].size + 1) {
            if (checkBoard(sticker, j, i)) {
                return true
            }
        }
    }
    return false
}

private fun checkBoard(sticker: Array<IntArray>, x: Int, y: Int): Boolean {
    if (sticker.size > notebookBoard.size || sticker[0].size > notebookBoard[0].size) return false
    for (k in sticker.indices) {
        for (l in sticker[0].indices) {
            if (sticker[k][l] == 1 && notebookBoard[y + k][x + l]) return false
        }
    }

    for (k in sticker[0].indices) {
        for (l in sticker.indices) {
            if (sticker[l][k] == 1) {
                if (x + k > notebookBoard[0].lastIndex || y + l > notebookBoard.lastIndex) continue
                notebookBoard[y + l][x + k] = true
                answer += 1
            }
        }
    }
    return true
}

private fun rotateSticker(sticker: Array<IntArray>): Array<IntArray> {
    val newSticker = Array(sticker[0].size) {
        IntArray(sticker.size)
    }

    for (i in sticker[0].indices) {
        for (j in 0 until sticker.size / 2) {
            val temp = sticker[j][i]
            sticker[j][i] = sticker[sticker.lastIndex - j][i]
            sticker[sticker.lastIndex - j][i] = temp
        }
    }

    for (i in 0 until sticker[0].size) {
        for (j in sticker.indices) {
            newSticker[i][j] = sticker[j][i]
        }
    }

    return newSticker
}