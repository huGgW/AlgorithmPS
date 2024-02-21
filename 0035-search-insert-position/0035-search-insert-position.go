func searchInsert(nums []int, target int) int {
    return searchInsertRecursive(nums, target, 0, len(nums)-1)
}

func searchInsertRecursive(nums []int, target, begin, end int) int {
    if begin > end {
        return begin
    }

    med := (begin + end) / 2

    if nums[med] < target {
        return searchInsertRecursive(nums, target, med+1, end)
    } else if target < nums[med] {
        return searchInsertRecursive(nums, target, begin, med-1)
    } else {
        return med
    }
}