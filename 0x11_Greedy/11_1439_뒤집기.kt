package `kotlin-algorithm`.`0x11_Greedy`

fun main(){
    val s = readln()

    var cnt = 0
    for(i in 1 until s.length){
        if(s[i] != s[i-1]){
            cnt+=1
        }
    }
    if(cnt % 2 == 1){
        cnt += 1
    }
    println(cnt/2)
}