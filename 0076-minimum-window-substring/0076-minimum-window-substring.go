func minWindow(s string, t string) string {
    // Analyze the t
    tPattern := map[rune]int {}

    for _, r := range t {
        if c, found := tPattern[r]; found {
            tPattern[r] = c + 1
        } else {
            tPattern[r] = 1
        }
    }
    leftCnt := len(tPattern)

    beg, end := 0, -1

    subBeg, subEnd := -1, -1
    for end < len(s)-1 {
        // Growing Phase
        for end < len(s)-1 && leftCnt > 0 {
            end++

            r := rune(s[end])
            updateState(r, true, tPattern, &leftCnt)
        }

        // Shrink Phase && record
        for leftCnt == 0 && beg <= end {
            if subBeg == -1 || subEnd - subBeg > end - beg {
                subBeg, subEnd = beg, end
            }

            r := rune(s[beg])
            updateState(r, false, tPattern, &leftCnt)

            beg++
        }
    }

    if subBeg == -1 {
        return ""
    } else {
        return s[subBeg:subEnd+1]
    }
}

func updateState(r rune, isAdd bool, pat map[rune]int, cnt *int) {
    if c, found := pat[r]; found {
        if isAdd {
            if c == 1 {
                *cnt--
            }
            pat[r] = c - 1
        } else {
            if c == 0 {
                *cnt++
            }
            pat[r] = c + 1
        }
    }
}