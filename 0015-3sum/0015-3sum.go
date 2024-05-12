import (
	"slices"
)

type Triple struct {
    First int
    Second int
    Third int
}
func (t Triple) ToSlice() []int {
    return []int{t.First, t.Second, t.Third}
}

func threeSum(nums []int) [][]int {
    slices.Sort(nums)

    zeroSet := map[Triple]struct{}{}

    for i := 0; i < len(nums)-2; i++ {
        b, e := i+1, len(nums)-1
        for b < e {
            sum := nums[i] + nums[b] + nums[e]
            if sum > 0 {
                e--
            } else if sum < 0 {
                b++
            } else {
                zeroSet[Triple{nums[i], nums[b], nums[e]}] = struct{}{}
                b++
            }
        }
    }


    answer := [][]int{}
    for trp, _ := range zeroSet {
        answer = append(answer, trp.ToSlice())
    }

    return answer
}
