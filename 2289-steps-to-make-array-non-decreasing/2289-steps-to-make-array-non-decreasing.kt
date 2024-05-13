class Solution {
    fun totalSteps(nums: IntArray): Int {
        val memo = IntArray(nums.size + 1)
        val stack = ArrayDeque<Int>()

        (nums.size - 1 downTo 0).forEach { i ->
            while (stack.isNotEmpty() && nums[stack.last()] < nums[i]) {
                val histIdx = stack.removeLast()
                memo[i] = max(memo[i] + 1, memo[histIdx])
            }

            stack.addLast(i)
        }

        return memo.max()
    }
}