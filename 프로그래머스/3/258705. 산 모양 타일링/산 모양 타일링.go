func solution(n int, tops []int) int {
	hist := make([]int, n)

	// First
	hist[0] = 3 + tops[0]

	// Second
	if n >= 2 {
		hist[1] = hist[0] * (3 + tops[1]) - 1
	}

	// Larger
	for x := 3; x <= n; x++ {
		hist[x-1] = ((hist[x-2] * (3 + tops[x-1])) - hist[x-3] + 10007) % 10007
	}

	return hist[n-1]
}
