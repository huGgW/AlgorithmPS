import java.util.*

class Solution {
    fun minLengthAfterRemovals(nums: List<Int>): Int {
        val heap = PriorityQueue<Int> { a, b -> b - a }

        var before = -1
        var cnt = 0
        nums.forEach {
            when (before) {
                -1 -> {
                    cnt = 1
                    before = it
                }
                it -> {
                    cnt++
                }
                else -> {
                    heap.offer(cnt)
                    cnt = 1
                    before = it
                }
            }
        }
        heap.offer(cnt)

        while (heap.size >= 2) {
            val first = heap.poll() - 1
            val second = heap.poll() - 1

            if (first > 0) {
                heap.offer(first)
            }
            if (second > 0) {
                heap.offer(second)
            }
        }

        return if (heap.isEmpty()) {
            0
        } else {
            heap.peek()
        }
    }
}
