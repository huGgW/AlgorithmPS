class Solution {
    tailrec fun rob(
        nums: IntArray, curr: Int = 0, twoBefore: Int = 0, oneBefore: Int = 0
    ): Int = when (curr) {
        in 0 until nums.size ->
            rob(nums, curr + 1, oneBefore, maxOf(twoBefore + nums[curr], oneBefore))

        else -> oneBefore
    }
}
