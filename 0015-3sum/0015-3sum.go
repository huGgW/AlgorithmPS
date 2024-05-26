import (
	"slices"
)

func threeSum(nums []int) [][]int {
	slices.Sort(nums)
	answer := [][]int{}

	for i := 0; i < len(nums)-2; {
		fst := nums[i]
		b, e := i+1, len(nums)-1
		for b < e {
            scd, thd := nums[b], nums[e]
			sum := fst + scd + thd
			if sum > 0 {
				e--
			} else if sum < 0 {
				b++
			} else {
				answer = append(answer, []int{fst, scd, thd})

				for ; b < e && nums[b] == scd; b++ {
				}

				for ; b < e && nums[e] == thd; e-- {
				}
			}
		}

		for ; i < len(nums)-2 && nums[i] == fst; i++ {
		}
	}

	return answer
}