func findDuplicate(nums []int) int {
	for i := 0; i < len(nums); {
		n := nums[i]
		p := n - 1

		if i == p {
			i++
			continue
		}

		if n == nums[p] {
			return n
		}

		nums[i], nums[p] = nums[p], nums[i]
	}

	panic(nil)
}
