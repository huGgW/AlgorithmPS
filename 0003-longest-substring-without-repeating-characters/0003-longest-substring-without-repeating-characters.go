func lengthOfLongestSubstring(s string) int {
	if len(s) == 0 {
		return 0
	}

	rs := []rune(s)
	letters := map[rune]struct{}{ rs[0]: {} }
	maxLen := 1
	b, e := 0, 0

	for e < len(rs)-1 {
		e++
		r := rs[e]

		for _, found := letters[r]; found && b < e; _, found = letters[r] {
			rr := rs[b]
			delete(letters, rr)
			b++
		}

		letters[r] = struct{}{}

		if ln := len(letters); maxLen < ln {
			maxLen = ln
		}
	}

	return maxLen
}
