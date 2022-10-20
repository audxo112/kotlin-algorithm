import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()

    val meetings = Array(n) {
        val st = StringTokenizer(readln())
        Meeting(st.nextToken().toInt(), st.nextToken().toInt())
    }

    meetings.sortWith(compareBy({ it.end },{it.start}))

    var answer = 0
    var nowMeetings = Meeting(0,0)

    for (m in meetings) {
        if (m.start >= nowMeetings.end) {
            nowMeetings = m
            answer += 1
        }
    }
    println(answer)
}

data class Meeting(
    var start: Int,
    var end: Int
)