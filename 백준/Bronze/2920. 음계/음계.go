package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)


func main() {
	sc := bufio.NewScanner(os.Stdin)
	sc.Scan()
	strs := strings.Split(sc.Text(), " ")

	result := checkAscending(strs)
	fmt.Println(result)
}

func checkAscending(slice []string) string {
	isAscending := false
	for i, s := range slice {
		x, _ := strconv.Atoi(s)

		if i == 0 {
			if x == 1 {
				isAscending = true
			} else if x == 8 {
				isAscending = false
			} else {
				return "mixed"
			}
		} else {
			var n int
			if isAscending {
				n = i + 1
			} else {
				n = 8 - i
			}

			if n != x {
				return "mixed"
			}
		}
	}

	if isAscending {
		return "ascending"
	} else {
		return "descending"
	}
}
