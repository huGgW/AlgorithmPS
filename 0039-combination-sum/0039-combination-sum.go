import (
	"slices"
)

func combinationSum(candidates []int, target int) [][]int {
	slices.Sort(candidates)

	result := [][]int{}
	tmp := []int{}
	idx := 0
	combinationStack(candidates, &target, idx, &tmp, &result)
	return result
}

func combinationStack(candidates []int, tPtr *int, idx int, tmp *[]int, result *[][]int) {
	if *tPtr < 0 {
		return
	}

	if *tPtr == 0 {
		*result = append(*result, slices.Clone(*tmp))
		return
	} 

	for i := idx; i < len(candidates); i++ {
		*tmp = append(*tmp, candidates[i])
		*tPtr -= candidates[i]

		combinationStack(candidates, tPtr, i, tmp, result)

		*tPtr += candidates[i]
		*tmp = (*tmp)[:len(*tmp)-1]
	}
}
