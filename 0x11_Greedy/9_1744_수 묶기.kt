package `kotlin-algorithm`.`0x11_Greedy`

fun main(){
    val n = readln().toInt()
    val sequence  = IntArray(n){
        readln().toInt()
    }
    val positive  = sequence.filter { it > 0 }.sortedDescending()
    val negative  = sequence.filter { it <= 0 }.sorted()

    var answer = 0
    answer += getBundleSum(positive)
    answer += getBundleSum(negative)

    println(answer)
}

private fun getBundleSum(sequence: List<Int>): Int{
    var result = 0
    var nowIdx = 0
    var pair: ArrayList<Int> = arrayListOf()
    while (nowIdx < sequence.size){
        pair.add(sequence[nowIdx])
        if(pair.last() == 1){
            result += pair.sum()
            pair = arrayListOf()
        }
        if(pair.size == 2){
            val pairMultiple = pair[0] * pair[1]
            result += pairMultiple
            pair = arrayListOf()
        }
        nowIdx += 1
    }
    if(pair.size != 0){
        result += pair.sum()
    }
    return result
}