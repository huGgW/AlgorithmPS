class Solution {
    fun rob(nums: IntArray): Int =
        when (nums.size) {
            1 -> nums[0]
            2 -> maxOf(nums[0], nums[1])
            else -> {
                var twoBefore = nums[0]
                var oneBefore = maxOf(nums[1], nums[0])
                for (i in 2 until nums.size) {
                    val next = maxOf(oneBefore, twoBefore + nums[i])
                    twoBefore = oneBefore
                    oneBefore = next
                }
                oneBefore
            }
        }
}
