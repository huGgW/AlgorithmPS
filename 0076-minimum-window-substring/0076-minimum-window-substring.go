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

    beg, end := 0, -1

    subBeg, subEnd := -1, -1
    for end < len(s)-1 {
        isIncluded := false

        // Growing Phase
        for end < len(s)-1 && !isIncluded {
            end++

            r := rune(s[end])
            if c, found := tPattern[r]; found {
                tPattern[r] = c - 1
            }

            isIncluded = allIncluded(tPattern)
        }

        // Shrink Phase && record
        for isIncluded && beg <= end {
            if subBeg == -1 || subEnd - subBeg > end - beg {
                subBeg, subEnd = beg, end
            }

            r := rune(s[beg])
            if c, found := tPattern[r]; found {
                tPattern[r] = c + 1
            }

            beg++
            isIncluded = allIncluded(tPattern)
        }
    }

    if subBeg == -1 {
        return ""
    } else {
        return s[subBeg:subEnd+1]
    }
}

func allIncluded(pat map[rune]int) bool {
    for _, c := range pat {
        if c > 0 {
            return false
        }
    }
    return true
}