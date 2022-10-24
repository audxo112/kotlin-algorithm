package `kotlin-algorithm`.`0x11_Greedy`

fun main(){
    val input = readln()

    val minusParse = input.split("-")
    val plusArray = IntArray(minusParse.size)

    for (i in minusParse.indices){
        plusArray[i] = minusParse[i].split("+").sumOf { it.toInt() }
    }

    var answer = 0
    for(i in plusArray.indices){
        if (i == 0){
            answer += plusArray[i]
        } else{
            answer -= plusArray[i]
        }
    }
    println(answer)
}