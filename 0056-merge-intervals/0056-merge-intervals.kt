class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val ans = mutableListOf<IntArray>()
        intervals.sortWith(compareBy({it[0]}, {it[1]}))

        intervals.forEach {
            val (_, topE) = ans.lastOrNull() ?: let { _ ->
                ans.add(it)
                return@forEach
            }
            if (topE >= it[0]) {
                ans.last()[1] = max(topE, it[1])
            } else {
                ans.add(it)
            }
        }

        return ans.toTypedArray()
    }
}
