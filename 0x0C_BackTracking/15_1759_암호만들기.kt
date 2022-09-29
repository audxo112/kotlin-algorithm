import java.util.StringTokenizer

private var password = ""
private lateinit var wordList: List<String>

fun main(){
    var st = StringTokenizer(readln())
    val l = st.nextToken().toInt()
    val c = st.nextToken().toInt()

    st = StringTokenizer(readln())
    wordList = List(c){
        st.nextToken()
    }.sorted()

    dfs(l,0)

}

private fun dfs(l: Int, s: Int){
    if(password.length == l){
        if(checkPassword(password) in (1..password.length-2)){
            println(password)
        }
    }
    for (i in s until wordList.size){
        password += wordList[i]
        dfs(l,i+1,)
        password = password.substring(0,password.length - 1)
    }
}

private fun checkPassword(password: String): Int{
    val vowel = listOf('a','e','i','o','u')
    var cnt = 0
    vowel.forEach { v ->
        cnt += password.count {it == v}
    }
    return cnt
}