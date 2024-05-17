func sortColors(nums []int) {
	zeroBoundary, twoBoundary := -1, len(nums)

	i := 0
	for zeroBoundary < i && i < twoBoundary {
		n := nums[i]
		switch n {
		case 0:
			zeroBoundary++
			nums[i], nums[zeroBoundary] = nums[zeroBoundary], nums[i]
			if i <= zeroBoundary {
				i = zeroBoundary + 1
			}

		case 2:
			twoBoundary--
			nums[i], nums[twoBoundary] = nums[twoBoundary], nums[i]
		default:
			i++
		}
	}
}
