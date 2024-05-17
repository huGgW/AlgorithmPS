import java.util.PriorityQueue
import kotlin.math.*

class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val pq = PriorityQueue<IntArray> { 
            a, b -> b[1] - a[1]
        }
        intervals.sortBy { it[0] }

        intervals.forEach {
            if (pq.isEmpty()) {
                pq.offer(it)
            } else {
                val (_, topE) = pq.peek()
                val (b, e) = it
                if (topE >= b) {
                    pq.peek()[1] = max(e, topE)
                } else {
                    pq.offer(it)
                }
            }
        }

        return pq.toArray(arrayOf())
    }
}
