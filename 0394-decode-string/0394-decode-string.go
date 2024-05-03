import (
	"unicode"
	"strconv"
	"slices"
)

func decodeString(s string) string {
	resultRunes, _ := decodeRuneRecursive([]rune(s), 0)
	return string(resultRunes)
}

func decodeRuneRecursive(runes []rune, cursor int) ([]rune, int) {
	if len(runes) == cursor {
		return []rune{}, cursor
	}

	if unicode.IsDigit(runes[cursor]) {
		begCursor := cursor
		for ; begCursor < len(runes); begCursor++ {
			if runes[begCursor] == '[' {
				break
			}
		}
		iterNum, _ := strconv.Atoi(string(runes[cursor:begCursor]))

		iterRunes, afterCursor := decodeRuneRecursive(runes, begCursor+1)
		result := []rune{}
		for i := 0; i < iterNum; i++ {
			result = append(result, slices.Clone(iterRunes)...)
		}
		after, allHandledCursor := decodeRuneRecursive(runes, afterCursor)
		return append(result, slices.Clone(after)...), allHandledCursor
	} else {
		cutIdx := cursor
		for ; cutIdx < len(runes); cutIdx++ {
			if unicode.IsDigit(runes[cutIdx]) || runes[cutIdx] == ']' {
				break
			}
		}

		if cutIdx == len(runes) {
			return slices.Clone(runes[cursor:cutIdx]), cutIdx
		} else if runes[cutIdx] == ']' { 
			return slices.Clone(runes[cursor:cutIdx]), cutIdx + 1
		} else {
			after, afterCursor := decodeRuneRecursive(runes, cutIdx)
			return append(slices.Clone(runes[cursor:cutIdx]), slices.Clone(after)...), afterCursor
		}
	}
}
