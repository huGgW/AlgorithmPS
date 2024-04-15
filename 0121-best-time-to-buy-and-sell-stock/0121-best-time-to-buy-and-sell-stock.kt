class Solution {
    tailrec fun maxProfit(
        prices: IntArray,
        idx: Int = 0,
        min: Int? = null,
        maxDiff: Int = 0,
    ): Int = when(idx) {
        in 0 until prices.size -> {
            val newMin = min?.let { kotlin.math.min(it, prices[idx])} ?: prices[idx]
            val newMaxDiff = kotlin.math.max(maxDiff, prices[idx] - newMin)
            maxProfit(
                prices,
                idx + 1,
                newMin,
                newMaxDiff,
            )
        }
        prices.size -> {
            maxDiff
        }
        else -> throw RuntimeException()
    }
}
