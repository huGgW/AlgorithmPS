class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length;) {
            var n = nums[i];
            if (
                1 <= n && n <= nums.length 
                && n != i + 1
                && nums[n-1] != nums[i]
            ) {
                var tmp = nums[n - 1];
                nums[n - 1] = n;
                nums[i] = tmp;
            } else {
                i++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }
}
