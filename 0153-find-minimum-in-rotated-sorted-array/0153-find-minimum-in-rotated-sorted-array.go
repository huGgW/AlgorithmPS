func findMin(nums []int) int {
	if len(nums) == 1 {
		return nums[0]
	}

	if nums[0] < nums[len(nums)-1] {
		return nums[0]
	} else {
		return nums[findStartIdx(0, len(nums)-1, nums)]
	}
}

func findStartIdx(b, e int, nums []int) int {
	if b + 1 == e {
		return e
	}

	m := (b + e) / 2
	if nums[b] > nums[m] {
		return findStartIdx(b, m, nums)
	} else if nums[m] > nums[e] {
		return findStartIdx(m, e, nums)
	}

	panic(nil)
}
