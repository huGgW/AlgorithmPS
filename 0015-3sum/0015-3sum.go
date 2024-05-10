import (
	"fmt"
	"slices"
)

type Pair struct {
    First int
    Second int
}

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

    for b := 0; b <= len(nums)-3; b++ {
        for e := b+1;  e <= len(nums)-2; e++ {
            needVal := -1 * (nums[b] + nums[e])
            idx, found := slices.BinarySearch(nums[e+1:], needVal)
            idx += e+1

            if found {
                trp := Triple{nums[b], nums[e], nums[idx]}
                if _, found := zeroSet[trp]; !found {
                    zeroSet[trp] = struct{}{}
                }
            }
        }
    }

    answer := [][]int{}
    for trp, _ := range zeroSet {
        answer = append(answer, trp.ToSlice())
    }

    return answer
}