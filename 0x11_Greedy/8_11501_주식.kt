package `kotlin-algorithm`.`0x11_Greedy`

import java.util.StringTokenizer

fun main(){
    val t = readln().toInt()
    for (i in 0 until t){
        val n = readln().toInt()
        val st = StringTokenizer(readln())
        val stockArray = LongArray(n){
            st.nextToken().toLong()
        }

        var nowMax: Long = 0
        var stockSum: Long = 0
        var count = 0
        var money: Long = 0
        for(stock in stockArray.reversed()){
            if(nowMax > stock){
                stockSum += stock
                count += 1
            } else {
                money += (nowMax * count) - stockSum
                count = 0
                stockSum = 0
                nowMax = stock
            }
        }
        money += (nowMax * count) - stockSum
        println(money)
    }
}