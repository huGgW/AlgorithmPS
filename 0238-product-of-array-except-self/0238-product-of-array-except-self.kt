class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        var zeroCnt = 0
        var mult = 1
        nums.forEach {
            when (it) {
                0 -> zeroCnt++
                else -> mult *= it
            }
        }

        return nums.map {
            when (zeroCnt) {
                0 -> mult / it
                1 -> when (it) {
                    0 -> mult
                    else -> 0
                }
                else -> 0
            }
        }.toIntArray()
    }
}