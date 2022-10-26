//https://www.acmicpc.net/problem/1744
package solution1744

private fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val minus = IntArray(1001)
    val plus = IntArray(1001)

    repeat(N){
        val value = readLine().toInt()
        if(value > 0){
            plus[value] += 1
        }
        else{
            minus[value * -1] += 1
        }
    }

    var sum = 0
    var mi = 1000
    var tmp:Int? = null

    while(mi >= 0){
        if(minus[mi] > 0){
            if(tmp != null){
                sum += tmp * mi
                tmp = null
                minus[mi] -= 1
            }
            else {
                if(minus[mi] > 1){
                    sum += mi * mi
                    minus[mi] -= 2
                }
                else{
                    tmp = mi
                    minus[mi] -= 1
                }
            }
        }
        else{
            mi -= 1
        }
    }
    if(tmp != null){
        sum -= tmp
        tmp = null
    }

    var pi = 1000
    while(pi > 1){
        if(plus[pi] > 0){
            if(tmp != null){
                sum += tmp * pi
                tmp = null
                plus[pi] -= 1
            }
            else{
                if(plus[pi] > 1){
                    sum += pi * pi
                    plus[pi] -= 2
                }
                else{
                    tmp = pi
                    plus[pi] -= 1
                }
            }
        }
        else{
            pi -= 1
        }
    }
    if(tmp != null){
        sum += tmp
    }
    sum += plus[1]
    println(sum)
}