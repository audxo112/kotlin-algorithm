package `kotlin-algorithm`.`0x11_Greedy`

import java.util.*

fun main() {
    var st = StringTokenizer(readln())
    val n = st.nextToken().toInt()

    val flowers = Array(n) {
        st = StringTokenizer(readln())
        Flower(
            st.nextToken().toInt() * 100 + st.nextToken().toInt(),
            st.nextToken().toInt() * 100 + st.nextToken().toInt()
        )
    }
    flowers.sortWith(compareBy({ it.start }, { it.end }))

    var nowFlower = Flower(0, 0)
    var now = 0

    for (i in flowers.indices) {
        if (flowers[i].start > 301) break
        if (flowers[i].end > nowFlower.end) {
            nowFlower = flowers[i]
            now = i
        }
    }

    var ans = 0
    var start = 301
    var end = 0
    //1201 이전 날짜 까지만 확인
    while (start < 1201) {
        var flag = false
        for (i in now until n) {
            //이전 꽃이 지기 이전에 다음 꽃이 피어 있어야 한다.
            if (flowers[i].start > start) break
            if (end < flowers[i].end) {
                end = flowers[i].end
                now = i + 1
                flag = true
            }
        }
        if (flag) {
            start = end
            ans++
        } else
            break
    }
    if (end < 1201)
        println(0)
    else
        println(ans)
}

private data class Flower(
    val start: Int,
    val end: Int,
)