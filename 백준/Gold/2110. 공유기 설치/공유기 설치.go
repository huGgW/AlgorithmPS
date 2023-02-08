package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
)

func main() {
	// Read N and C
	var N, C int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &N, &C)

	// Make house coords
	var houses []int64
	for i := 0; i < N; i++ {
		var x int64
		fmt.Fscanln(reader, &x)
		houses = append(houses, x)
	}

	// Sort house coords
	sort.Slice(houses, func(i int, j int) bool {
		return houses[i] < houses[j]
	})

	// If router number is only 2, early return
	if C == 2 {
		fmt.Println(int(houses[N-1] - houses[0]))
        return
	}

	// Get maximum closest distance of routers
	maximumClosestDistance := getMaximumClosestDistance(houses, C)

	wr := bufio.NewWriter(os.Stdout)
	fmt.Fprintln(wr, maximumClosestDistance)
	wr.Flush()
}

func getMaximumClosestDistance(houses []int64, C int) int64 {
	// Get minimum & maximum distance possible
	totalDist := houses[len(houses)-1] - houses[0]
	maxDist := (totalDist / int64(C-1)) + 1
	minDist := int64(1)
	virtualC := float64(C) - 0.5

	for maxDist >= minDist {
		hoppingDistance := (maxDist + minDist) / 2

		cnt := countRouters(
			hoppingDistance, houses, C,
		)

		if float64(cnt) < virtualC {
			maxDist = hoppingDistance - 1
		} else if float64(cnt) > virtualC {
			minDist = hoppingDistance + 1
		} else {
			return hoppingDistance
		}
	}

	return maxDist
}

func countRouters(
	hoppingDistance int64, houses []int64, C int,
) int {
	cnt := 1
	currentIdx := 0

	for cnt <= C {
		searchCoord := houses[currentIdx] + hoppingDistance
		idx := binarySearch(currentIdx+1, len(houses)-1, searchCoord, houses)
		if idx >= len(houses) {
			break
		}
		currentIdx = idx
		cnt++
	}

	return cnt
}

func binarySearch(b, e int, x int64, slice []int64) int {
	if b > e {
		return b
	}

	m := (b + e) / 2
	mid := slice[m]

	if mid == x {
		return m
	} else if x < mid {
		return binarySearch(b, m-1, x, slice)
	} else {
		return binarySearch(m+1, e, x, slice)
	}
}
