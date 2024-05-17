class Solution {
    fun singleNumber(nums: IntArray): Int
        = nums.fold(0) { l, r -> l xor r }
}
