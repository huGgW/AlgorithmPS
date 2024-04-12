import java.util.*

class Solution {
    class DoubleQueue {
        val minQueue = PriorityQueue<Int> { a, b -> a - b }
        val maxQueue = PriorityQueue<Int> { a, b -> b - a }
        var minExcludeCnt = 0
        var maxExcludeCnt = 0

        fun insert(v: Int) {
            minQueue.offer(v)
            maxQueue.offer(v)
        }

        fun deleteMin() {
            if (isEmpty()) {
                return
            }

            minQueue.poll()
            maxExcludeCnt += 1
            if (maxExcludeCnt == maxQueue.size) {
                reEmpty()
            }
        }

        fun deleteMax() {
            if (isEmpty()) {
                return
            }

            maxQueue.poll()
            minExcludeCnt += 1
            if (minExcludeCnt == minQueue.size) {
                reEmpty()
            }
        }

        fun returnMinMax(): IntArray
            = if (isEmpty()) intArrayOf(0, 0) else intArrayOf(maxQueue.peek(), minQueue.peek())

        private fun isEmpty()
            = minQueue.isEmpty() && maxQueue.isEmpty()

        private fun reEmpty() {
            minQueue.clear()
            maxQueue.clear()
            minExcludeCnt = 0
            maxExcludeCnt = 0
        }
    }

    fun solution(operations: Array<String>): IntArray {
        val dq = DoubleQueue()
        for (oper in operations) {
            decodeAndExecute(oper, dq)
        }

        return dq.returnMinMax()
    }
    
    fun decodeAndExecute(operation: String, dq: DoubleQueue) {
        val (oper, value) = operation.split(" ")
        when (oper) {
            "I" -> dq.insert(value.toInt())
            "D" -> when (value) {
                "1" -> dq.deleteMax()
                "-1" -> dq.deleteMin()
            }
        }
    }
}