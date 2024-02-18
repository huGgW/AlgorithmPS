package main

import (
	"bufio"
	"os"
	"strconv"
)

const (
	NONE = iota
	UP
	LEFT
	CROSS
)

var PATH = map[int]struct{ di, dj int }{
	UP:    {0, -1},
	LEFT:  {-1, 0},
	CROSS: {-1, -1},
}

func reverse(i, j int) (int, int) {
	return -i, -j
}

func main() {
	sc := bufio.NewScanner(os.Stdin)
	bufW := bufio.NewWriter(os.Stdout)

	// s1, s2
	sc.Scan()
	s1 := sc.Text()
	sc.Scan()
	s2 := sc.Text()

	// paths && length
	paths := make([][]int, len(s1)+1)
	for i := range paths {
		paths[i] = make([]int, len(s2)+1)
	}
	lengths := make([][]int, len(s1)+1)
	for i := range lengths {
		lengths[i] = make([]int, len(s2)+1)
	}

	// traverse
	for i := 1; i <= len(s1); i++ {
		si := i - 1
		for j := 1; j <= len(s2); j++ {
			sj := j - 1
			upLength := lengths[i+PATH[UP].di][j+PATH[UP].dj]
			leftLength := lengths[i+PATH[LEFT].di][j+PATH[LEFT].dj]
			var crossLength int
			if s1[si] == s2[sj] {
				crossLength = lengths[i+PATH[CROSS].di][j+PATH[CROSS].dj] + 1
			} else {
				crossLength = -1
			}

			if crossLength >= upLength && crossLength >= leftLength {
				paths[i][j] = CROSS
				lengths[i][j] = crossLength
			} else if upLength >= leftLength {
				paths[i][j] = UP
				lengths[i][j] = upLength
			} else {
				paths[i][j] = LEFT
				lengths[i][j] = leftLength
			}
		}
	}

	// print LCS
	bufW.WriteString(strconv.Itoa(lengths[len(s1)][len(s2)]))
	bufW.WriteRune('\n')

	if lengths[len(s1)][len(s2)] == 0 {
	    bufW.Flush()
	    return
    }

	// traverse LCS
    lcs := []rune{}
    for i, j := len(s1), len(s2); i > 0 && j > 0; {
        si, sj := i-1, j-1
        path := paths[i][j]

        switch path {
        case UP: {
            i += PATH[UP].di
            j += PATH[UP].dj
        }
        case LEFT: {
            i += PATH[LEFT].di
            j += PATH[LEFT].dj
        }
        case CROSS: {
            if s1[si] != s2[sj] {
                panic(nil)
            }

            lcs = append([]rune{rune(s1[si])}, lcs...)
            i += PATH[CROSS].di
            j += PATH[CROSS].dj
        }
        default: {
            panic(nil)
        }
        }
    }

    bufW.WriteString(string(lcs))
    bufW.WriteRune('\n')
    bufW.Flush()
}
