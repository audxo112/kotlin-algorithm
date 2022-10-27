package `kotlin-algorithm`.`0x11_Greedy`

fun main() {
    val n = readln().toInt()
    val levelScore = IntArray(n) {
        readln().toInt()
    }

    var answer = 0

    for(i in levelScore.lastIndex - 1 downTo 0){
        if(levelScore[i] >= levelScore[i+1]){
            answer += levelScore[i] - levelScore[i+1] + 1
            levelScore[i] = levelScore[i+1] -1
        }
    }
    println(answer)
}