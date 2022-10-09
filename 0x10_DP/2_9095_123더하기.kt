package `kotlin-algorithm`.`0x10_DP`

fun main(){
    val t = readln().toInt()

    for(i in 1..t){
        val n = readln().toInt()
        val arr = IntArray(12)
        arr[1] = 1
        arr[2] = 2
        arr[3] = 4

        for(j in 4..n){
                arr[j] = arr[j-1] + arr[j-2] + arr[j-3]
        }
        println(arr[n])
    }
}