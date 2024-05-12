class Solution {
    fun maxArea(height: IntArray): Int {
        var beg = 0
        var end = height.size - 1
        var maxArea = 0

        while (end > beg) {
            val isBegSmall = height[beg] < height[end]
            val minHeight = if (isBegSmall) {
                height[beg]
            } else {
                height[end]
            }
            maxArea = max(maxArea, minHeight * (end - beg))

            if (isBegSmall) {
                beg++
            } else {
                end--
            }
        }

        return maxArea
    }
}
