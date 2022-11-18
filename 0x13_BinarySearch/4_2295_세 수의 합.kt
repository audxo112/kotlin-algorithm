package `kotlin-algorithm`.`0x13_BinarySearch`

fun main() {
    val n = readln().toInt()
    val arr = IntArray(n)
    for (i in 0 until n) {
        arr[i] = readln().toInt()
    }
    arr.sortDescending()
    val twoSumSet = mutableSetOf<Int>()
    for(x in arr){
        for(y in arr){
            twoSumSet.add(x + y)
        }
    }

    val twoSumSortedList = twoSumSet.toList().sorted()
    for(k in arr){
        for (z in arr) {
            val idx = twoSumSortedList.binarySearch(k-z)
            if(idx >= 0){
                println(z + twoSumSortedList[idx])
                return
            }
        }
    }
}
