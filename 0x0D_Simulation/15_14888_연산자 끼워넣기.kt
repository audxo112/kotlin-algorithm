package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.*

private var maxAnswer = -Int.MAX_VALUE
private var minAnswer = Int.MAX_VALUE

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()

    val arr = IntArray(n) {
        input()
    }
    val operatorList = IntArray(4) {
        input()
    }

    backTrack(arr[0], 1, operatorList, arr, n)

    println(maxAnswer)
    println(minAnswer)
}

fun backTrack(nowNum: Int, nowIdx: Int, operatorList: IntArray, numArr: IntArray, n: Int) {
    if (nowIdx == n) {
        minAnswer = minOf(nowNum, minAnswer)
        maxAnswer = maxOf(nowNum, maxAnswer)
    }
    if (operatorList[0] > 0) {
        val newOperatorList = operatorList.clone()
        newOperatorList[0] -= 1
        backTrack(nowNum + numArr[nowIdx], nowIdx + 1, newOperatorList, numArr, n)
    }
    if (operatorList[1] > 0) {
        val newOperatorList = operatorList.clone()
        newOperatorList[1] -= 1
        backTrack(nowNum - numArr[nowIdx], nowIdx + 1, newOperatorList, numArr, n)
    }
    if (operatorList[2] > 0) {
        val newOperatorList = operatorList.clone()
        newOperatorList[2] -= 1
        backTrack(nowNum * numArr[nowIdx], nowIdx + 1, newOperatorList, numArr, n)
    }
    if (operatorList[3] > 0) {
        val newOperatorList = operatorList.clone()
        newOperatorList[3] -= 1
        backTrack(nowNum / numArr[nowIdx], nowIdx + 1, newOperatorList, numArr, n)
    }
}