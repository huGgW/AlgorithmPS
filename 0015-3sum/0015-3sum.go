import (
	"slices"
)

func threeSum(nums []int) [][]int {
    slices.Sort(nums)

    zeroSet := map[[3]int]struct{}{}

    for i := 0; i < len(nums)-2; i++ {
        b, e := i+1, len(nums)-1
        for b < e {
            sum := nums[i] + nums[b] + nums[e]
            if sum > 0 {
                e--
            } else if sum < 0 {
                b++
            } else {
                zeroSet[[3]int{nums[i], nums[b], nums[e]}] = struct{}{}
                b++
                e--
            }
        }
    }


    answer := [][]int{}
    for arr, _ := range zeroSet {
        answer = append(answer, arr[:])
    }

    return answer
}