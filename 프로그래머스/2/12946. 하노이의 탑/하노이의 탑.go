func solution(n int) [][]int {
	return simulateHanoi(1, 3, 2, n)
}

func simulateHanoi (from, to, middle, biggest int) [][]int {
	if biggest == 1 {
		return [][]int {{from, to}}
	}

	return append(
		simulateHanoi(from, middle, to, biggest-1),
		append(
			[][]int{{from, to}},
			simulateHanoi(middle, to, from, biggest-1)...,
		)...,
	)
}
