import (
    "math"
    "sort"
)

func findBalancePeople(n int, times []int) []int {
	var sumOfInv float64 = 0
	for _, t := range times {
		sumOfInv += 1.0 / float64(t)
	}

	balance := make([]int, len(times))
	for i, t := range times {
		balance[i] = int(math.Floor(((1.0 / float64(t)) / sumOfInv) * float64(n)))
	}

	return balance
}

func findInsertIdx(arr []int, b int, e int, x int, calc func(int) int64) int {
	if b > e {
		return b
	}

	m := (b + e) / 2
	calcM := calc(arr[m])
	calcX := calc(x)

	if calcM == calcX {
		return m
	} else if calcX < calcM {
		return findInsertIdx(arr, b, m-1, x, calc)
	} else {
		return findInsertIdx(arr, m+1, e, x, calc)
	}
}

func solution(n int, times []int) int64 {
	// find balance, calculate remaining
	balance := findBalancePeople(n, times)
	for _, b := range balance {
		n -= b
	}

	nextEndTime := make([]int64, len(times))
	idxs := make([]int, len(times))
	for i, t := range times {
		nextEndTime[i] = int64(t) * int64(balance[i])
		idxs[i] = i
	}

	// sort idxs
	sort.Slice(idxs, func(i, j int) bool {
		return nextEndTime[idxs[i]] + int64(times[idxs[i]]) < nextEndTime[idxs[j]] + int64(times[idxs[j]])
	})

	for ; n > 0; n-- {
		nextEndTime[idxs[0]] += int64(times[idxs[0]])
		x := idxs[0]
		insertIdx := findInsertIdx(idxs, 1, len(idxs)-1, x, func (i int) int64 {
			return nextEndTime[i] + int64(times[i])
		})
		copy(idxs[0:insertIdx-1], idxs[1:insertIdx])
		idxs[insertIdx-1] = x
	}

	// Find latest ending
	maxEndTime := int64(0)
	for _, n := range nextEndTime {
		if n > maxEndTime {
			maxEndTime = n
		}
	}

	return maxEndTime
}
