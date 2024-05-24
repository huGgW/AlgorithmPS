class Solution {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val (begin, end) = let {
            var b: Int? = null
            var e: Int? = null
            intervals.forEachIndexed { i, interval ->
                if (!(interval[1] < newInterval[0] || newInterval[1] < interval[0])) {
                    b ?: let { b = i }
                    e = i
                }
            }
            b to e
        }

        return if (begin == null && end == null) {
            intervals.toMutableList().run {
                val idx = -1 * binarySearch(newInterval, { a, b -> a[0] - b[0] }) - 1
                add(idx, newInterval)
                toTypedArray()
            }
        } else if (begin != null && end != null) {
            val ans = mutableListOf<IntArray>()
            val merged = mutableListOf<Int>()

            intervals.forEachIndexed { i, interval ->
                if (i == begin) {
                    merged.add(minOf(interval[0], newInterval[0]))
                }

                if (i == end) {
                    merged.add(maxOf(interval[1], newInterval[1]))
                    ans.add(merged.toIntArray())
                }

                if (i !in begin..end) {
                    ans.add(interval)
                }
            }

            ans.toTypedArray()
        } else {
            throw Exception()
        }
    }
}
