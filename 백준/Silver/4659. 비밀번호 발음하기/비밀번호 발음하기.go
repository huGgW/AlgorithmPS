package main

import (
	"bufio"
	"fmt"
	"os"
)

var vowels map[rune]struct{} = map[rune]struct{}{
	'a': struct{}{},
	'e': struct{}{},
	'i': struct{}{},
	'o': struct{}{},
	'u': struct{}{},
}

func main() {
	sc := bufio.NewScanner(os.Stdin)
	wr := bufio.NewWriter(os.Stdout)
	defer wr.Flush()

	for {
		sc.Scan()
		txt := sc.Text()

		if txt == "end" {
			break
		}

        isAcceptable := check(txt)
        if isAcceptable {
            wr.WriteString(fmt.Sprintf("<%s> is acceptable.\n", txt))
        } else {
            wr.WriteString(fmt.Sprintf("<%s> is not acceptable.\n", txt))
        }
	}
}

func check(s string) bool {
	lastRune := 'd'
	lastIsVowel := false
	continuousCnt := 0
	continuousVowelCnt := 0
	containsOneVowel := false

	for i, r := range s {
		runeIsVowel := isVowel(r)
        if runeIsVowel {
            containsOneVowel = true
        }

		if i == 0 {
			lastRune = r
			lastIsVowel = runeIsVowel
			continuousVowelCnt = 1
			continuousCnt = 1
		} else {
			// continous vowel or not vowel
			if lastIsVowel == runeIsVowel {
				if continuousVowelCnt >= 2 {
					return false
				}

                continuousVowelCnt++
			} else {
				continuousVowelCnt = 1
			}

			// same rune
			if lastRune == r {
				if !(r == 'e' || r == 'o') {
					return false
				}

                continuousCnt++
			} else {
				continuousCnt = 1
			}

            lastIsVowel = runeIsVowel
            lastRune = r
		}
	}

    return containsOneVowel
}

func isVowel(r rune) bool {
	_, contains := vowels[r]
	return contains
}