package solve_14889

import java.io.*
import kotlin.math.*

private lateinit var map: Array<IntArray>
private lateinit var visited: BooleanArray
private var answer = Int.MAX_VALUE

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()

    map = Array(n + 1) {
        IntArray(n + 1)
    }
    visited = BooleanArray(n+1)
    for(i in 1..n){
        for (j in 1..n){
            map[i][j] = input()
        }
    }
    getComb(1,0,n)
    println(answer)
}

fun getComb(idx: Int, depth: Int, n: Int){
    if(depth == n/2){
        getAnswer(n)
        return
    }
    for(i in idx .. n){
        if(visited[i].not()){
            visited[i] = true
            getComb(i+1,depth+1, n)
            visited[i] = false
        }
    }
}

fun getAnswer(n: Int) {
    var teamOneScore = 0
    var teamTwoScore = 0

    for(i in 1..n){
        for (j in i+1..n){
            if(visited[i] && visited[j]) {
                teamOneScore += map[i][j] + map[j][i]
            } else if(visited[i].not() && visited[j].not()){
                teamTwoScore += map[i][j] + map[j][i]
            }
        }
    }

    answer = minOf(answer, abs(teamOneScore-teamTwoScore))

}

