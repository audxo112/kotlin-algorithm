private lateinit var nums : List<Int>
private var n = 0
private var s = 0
private var answer = 0

fun main(){
    val input = readln().split(" ").map{it.toInt()}
    n = input[0]
    s = input[1]

    nums = readln().split(" ").map{it.toInt()}

    dfs(0,0)
    if(s == 0){
        answer -= 1     //공집합
    }
    println(answer)
}

private fun dfs(x: Int, sum: Int) {
    if(x == n){
        if(sum == s){
            answer += 1
        }
        return
    }

    dfs(x+1, sum + nums[x])
    dfs(x+1,sum)
}