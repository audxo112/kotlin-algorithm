import java.util.StringTokenizer

private var answer = ArrayList<Int>()

fun main(){

    var st : StringTokenizer

    while(true){
        st = StringTokenizer(readln(), " ")
        val k = st.nextToken().toInt()
        if (k == 0) break
        val nums = IntArray(k){
            st.nextToken().toInt()
        }
        dfs(6,0,nums)
        println()
    }
}


private fun dfs(k: Int, start: Int, nums: IntArray){
    if(answer.size == k){
        answer.forEach {
            print("$it ")
        }
        println()
    }

    for(i in start until nums.size){
        answer.add(nums[i])
        dfs(k,i+1,nums)
        answer.removeLast()
    }

}