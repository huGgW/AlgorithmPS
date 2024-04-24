package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	scanner.Scan()
	p, _ := strconv.Atoi(
		scanner.Text(),
	)

	for i := 0; i < p; i++ {
		scanner.Scan()
		ls := strings.Split(
			scanner.Text(),
			" ",
		)

		studs := []int{}
		tc := 0
		moveCnt := 0
		for i, s := range ls {
			num, _ := strconv.Atoi(s)

			if i == 0 {
				tc = num
			} else {
				idx := bisectRight(studs, num, 0, len(studs)-1)
				if idx == len(studs) {
					studs = append(studs, num)
				} else {
					moveCnt += len(studs) - idx
					studs = insert(studs, num, idx)
				}
			}
		}

		writer.WriteString(
			fmt.Sprintf("%d %d\n", tc, moveCnt),
		)
	}
}

func bisectRight(ls []int, target, lo, hi int) int {
	if lo > hi {
		return lo
	}

	mid := (lo + hi) / 2

	if target < ls[mid] {
		return bisectRight(ls, target, lo, mid-1)
	} else {
		return bisectRight(ls, target, mid+1, hi)
	}
}

func insert(ls []int, x, idx int) []int {
	ls = append(ls[:idx+1], ls[idx:]...)
	ls[idx] = x
	return ls
}
