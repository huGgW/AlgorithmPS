class Solution {
    fun rob(nums: IntArray): Int {
        val memo = IntArray(nums.size) { 0 }
        for (i in 0 until nums.size) {
            memo[i] = when (i) {
                0 -> nums[0]
                1 -> maxOf(nums[0], nums[1])
                else -> maxOf(memo[i-1], memo[i-2] + nums[i])
            }
        }

        return memo[memo.size - 1]
    }
}
