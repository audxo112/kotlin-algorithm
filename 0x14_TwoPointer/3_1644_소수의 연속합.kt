package `kotlin-algorithm`.`0x14_TwoPointer`

fun main() {
    val n = readln().toInt()

    val primeArray = getPrimeArray(n)
    val sumArray = LongArray(primeArray.size + 1)
    var partialSum = 0L
    sumArray[0] = partialSum

    for (i in 0..primeArray.lastIndex) {
        partialSum += primeArray[i]
        sumArray[i + 1] = partialSum
    }


    var left = 0
    var right = 0
    var answer = 0

    while (left < sumArray.size && right < sumArray.size) {
        val diff = sumArray[right] - sumArray[left]
        if (diff < n) {
            right++
        } else if (diff > n) {
            left++
        } else {
            answer++
            left++
        }
    }
    println(answer)
}

private fun getPrimeArray(n: Int): IntArray {
    //에라토스테네스의 체
    val numArray = IntArray(n + 1) { idx ->
        idx
    }
    // 1은 소수가 아니다.
    numArray[1] = 0
    for (i in 2..n) {
        if (numArray[i] == 0) {
            continue
        }
        for (j in 2 * i..n step (i)) {
            if (j >= numArray.size) {
                break
            } else {
                numArray[j] = 0
            }
        }
    }

    return numArray.filter { it != 0 }.toIntArray()

}