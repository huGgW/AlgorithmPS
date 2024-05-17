func rotate(nums []int, k int) {
	marks := make([]bool, len(nums))
    k = k % len(nums)
	for i := 0; i < len(nums) && !marks[i]; i++ {
		bef := nums[i]
		for j := i + k; !marks[j]; j = (j + k) % len(nums) {
			tmp := bef
			bef = nums[j]
			nums[j] = tmp
			marks[j] = true
		}
	}
}
