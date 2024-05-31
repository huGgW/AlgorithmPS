data class State (
    val nextIndex: Int,
    val amount: Int,
)


class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        coins.sortDescending()
        return lookUp(coins, State(0, amount), mutableMapOf())
    }

    fun lookUp(coins: IntArray, state: State, history: MutableMap<State, Int>): Int = history[state] ?: let {
        if (state.nextIndex == coins.size - 1) {
            val cnt = if (state.amount % coins.last() == 0) {
                state.amount / coins.last()
            } else {
                -1
            }

            cnt.also {
                history[state] = it
            }

        } else {
            val coin = coins[state.nextIndex]
            var leftAmount = state.amount % coin
            var thisCnt = state.amount / coin
            var minTotalCnt: Int = -1

            while (thisCnt >= 0) {
                val cnt = lookUp(coins, State(state.nextIndex + 1, leftAmount), history)
                if (cnt != -1) {
                    if (minTotalCnt == -1 || minTotalCnt > cnt + thisCnt) {
                        minTotalCnt = cnt + thisCnt
                    }
                }
                thisCnt--
                leftAmount += coin
            }

            minTotalCnt.also {
                history[state] = it
            }
        }
    }
}
