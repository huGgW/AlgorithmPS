func sortColors(nums []int) {
	zeros, ones, twos := 0, 0, 0
	for _, n := range nums {
		switch n {
		case 0: zeros++
		case 1: ones++
		case 2: twos++
		}
	}

	for i := range zeros {
		nums[i] = 0
	}
	for i := range ones {
		nums[zeros + i] = 1
	}
	for i := range twos {
		nums[zeros + ones + i] = 2
	}
}
