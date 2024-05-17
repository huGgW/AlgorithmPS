class Solution {
    fun singleNumber(nums: IntArray): Int {
        val set = mutableSetOf<Int>()
        nums.forEach {
            if (set.contains(it)) {
                set.remove(it)
            } else {
                set.add(it)
            }
        }
        return set.first()
    }
}
