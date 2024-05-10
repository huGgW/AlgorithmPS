func moveZeroes(nums []int)  {
    lim := len(nums)
    i := 0

    for i < lim {
        if nums[i] == 0 {
            lim -= 1
            for j := i + 1; j <= lim; j++ {
                nums[j-1] = nums[j]
            }
            nums[lim] = 0
        } else {
            i++
        }
    }
}