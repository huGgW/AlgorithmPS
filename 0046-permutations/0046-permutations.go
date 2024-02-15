import (
    "slices"
)

type Set int
func getEmptySet() Set { return 0}
func (s *Set) mask(x int) {
    *s = (*s) | (1 << x)
}
func (s *Set) unmask(x int) {
    *s = (*s) & (^(1 << x))
}
func (s Set) contains(x int) bool {
    return s & (1 << x) > 0
}

func permute(nums []int) [][]int {
    tmp := []int{}
    set := getEmptySet()
    result := [][]int{}

    addCases(nums, &set, &tmp, &result)
    return result
}

func addCases(nums []int, set *Set, tmp *[]int, result *[][]int) {
    isLast := true

    for i := 0; i < len(nums); i++ {
        if set.contains(i) {
            continue
        }

        isLast = false
        *tmp = append(*tmp, nums[i])
        set.mask(i)
        addCases(nums, set, tmp, result)
        set.unmask(i)
        *tmp = (*tmp)[:len(*tmp)-1]
    }

    if isLast {
        *result = append(*result, slices.Clone(*tmp))
    }
}