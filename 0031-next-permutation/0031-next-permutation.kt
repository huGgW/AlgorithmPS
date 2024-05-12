class Solution {
    fun nextPermutation(nums: IntArray): Unit {
        var decreaseBegin = nums.size - 1
        while (decreaseBegin > 0) {
            if (nums[decreaseBegin-1] >= nums[decreaseBegin]) {
                decreaseBegin--
            } else {
                break
            }
        }

        sortInplace(nums, decreaseBegin, nums.size - 1)
        if (decreaseBegin == 0) {
            return
        }

        val changeIdx = bisectRight(nums, nums[decreaseBegin-1], decreaseBegin, nums.size - 1)
        (nums[decreaseBegin-1] to nums[changeIdx]).let {
            nums[decreaseBegin-1] = it.second
            nums[changeIdx] = it.first
        }
    }

    fun sortInplace(arr: IntArray, lo: Int, hi: Int) {
        if (lo > hi) {
            return
        }

        val mid = (lo + hi) / 2
        (arr[mid] to arr[hi]).let {
            arr[hi] = it.second
            arr[mid] = it.first
        }

        var pivot = arr[hi]
        var i = lo-1
        for (j in lo..<hi) {
            if (pivot > arr[j]) {
                i++
                (arr[i] to arr[j]).let {
                    arr[i] = it.second
                    arr[j] = it.first
                }
            }
        }

        i++
        (arr[i] to arr[hi]).let {
            arr[i] = it.second
            arr[hi] = it.first
        }
        sortInplace(arr, lo, i-1)
        sortInplace(arr, i+1, hi)
    }

    fun bisectRight(arr: IntArray, x: Int, lo: Int, hi: Int): Int {
        if (lo > hi) {
            return lo
        }

        val mid = (lo + hi) / 2
        if (arr[mid] <= x) {
            return bisectRight(arr, x, mid+1, hi)
        } else /*(arr[mid] > x)*/ {
            return bisectRight(arr, x, lo, mid-1)
        }
    }
}
