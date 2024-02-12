import (
    "strings"
)

func letterCombinations(digits string) []string {
    var alphabets = map[rune][]rune {
        '2': []rune{'a', 'b', 'c'},
        '3': []rune{'d', 'e', 'f'},
        '4': []rune{'g', 'h', 'i'},
        '5': []rune{'j', 'k', 'l'},
        '6': []rune{'m', 'n', 'o'},
        '7': []rune{'p', 'q', 'r', 's'},
        '8': []rune{'t', 'u', 'v'},
        '9': []rune{'w', 'x', 'y', 'z'},
    }

    if len(digits) == 0 {
        return []string{}
    }

    cnts := make([]int, len(digits))
    totalCnt := 1
    for i := 0; i < len(digits); i++ {
        cnt := len(alphabets[rune(digits[i])])
        cnts[i] = cnt
        totalCnt *= cnt
    }

    resultBuilder := make([]strings.Builder, totalCnt)
    for i, _ := range resultBuilder {
        resultBuilder[i] = strings.Builder{}
    }

    totalIter, cnt, sameIter := 1, 1, totalCnt
    for i, r := range digits {
        totalIter *= cnt
        cnt = cnts[i]
        sameIter /= cnt

        for t := 0; t < totalIter; t++ {
            for c := 0; c < cnt; c++ {
                for s := 0; s < sameIter; s++ {
                    resultBuilder[t*(cnt*sameIter) + c*(sameIter) + s].
                        WriteRune(alphabets[rune(r)][c])
                }
            }
        }
    }

    result := make([]string, 0, len(resultBuilder))
    for _, sb := range resultBuilder {
        result = append(result, sb.String())
    }

    return result
}
