class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        return lookUp(coins, amount, mutableMapOf()) ?: -1
    }

    fun lookUp(coins: IntArray, amount: Int, history: MutableMap<Int, Int?>): Int? = if (amount in history) {
        history[amount]
    } else {
        when {
            amount < 0 -> null
            amount == 0 -> 0
            else -> {
                val minCnt = coins.map {
                    lookUp(coins, amount - it, history)
                }.filterNotNull()
                    .minOrNull()
                minCnt ?. let { it + 1 }
            }
        }.also {
            if (amount > 0) {
                history[amount] = it
            }
        }
    }
}
