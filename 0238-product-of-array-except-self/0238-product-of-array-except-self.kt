class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        val n = nums.size
        val backward = nums.clone()

        (1 until n).forEach {
            nums[it] *= nums[it-1]
        }

        (n-2 downTo 0).forEach {
            backward[it] *= backward[it+1]
        }

        backward[0] = backward[1]
        (1 .. n-2).forEach {
            backward[it] = backward[it+1] * nums[it-1]
        }
        backward[n-1] = nums[n-2]

        return backward
    }
}
