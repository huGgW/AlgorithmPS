class Solution {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val ans = mutableListOf<IntArray>()
        var inserted = false

        var i = 0
        while (i < intervals.size) {
            val isNewInterval = !inserted && newInterval[0] < intervals[i][0]
            val nextInterval = if (isNewInterval) { newInterval } else { intervals[i] }

            if (ans.isEmpty() || ans.last()[1] < nextInterval[0] ) {
                ans.add(nextInterval)
            } else {
                ans.last()[1] = maxOf(ans.last()[1], nextInterval[1])
            }

            if (isNewInterval) {
                inserted = true
            } else {
                i++
            }
        }

        if (!inserted) {
            if (ans.isEmpty() || ans.last()[1] < newInterval[0] ) {
                ans.add(newInterval)
            } else {
                ans.last()[1] = maxOf(ans.last()[1], newInterval[1])
            }
        }

        return ans.toTypedArray()
    }
}
