package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

const (
	READY = iota
	VISITED
	LOOP
	ISOLATED
)

func noLoopCnt(nextStud []int, n int) int {
	states := make([]int, n+1)
	for i := 1; i <= n; i++ {
		states[i] = READY
	}

	for i := 1; i <= n; i++ {
		if states[i] != READY {
			continue
		}

		// mark visited
		j := i
		for states[j] == READY {
			states[j] = VISITED
			j = nextStud[j]
		}

		// mark loop
		k := j
		for states[k] == VISITED {
			states[k] = LOOP
			k = nextStud[k]
		}

		// mark isolated
		s := i
		for states[s] == VISITED {
			states[s] = ISOLATED
			s = nextStud[s]
		}
	}

	cnt := 0
	for _, state := range states {
		if state == ISOLATED {
			cnt++
		}
	}

	return cnt
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var tc int
	fmt.Fscanf(reader, "%d\n", &tc)

	for t := 0; t < tc; t++ {
		var n int
		fmt.Fscanf(reader, "%d\n", &n)

		line, _ := reader.ReadString('\n')
		lineSpl := strings.Split(
			strings.TrimSpace(line),
			" ",
		)

		nextStud := make([]int, n+1)
		for i := 1; i <= n; i++ {
			nextStud[i], _ = strconv.Atoi(lineSpl[i-1])
		}

		cnt := noLoopCnt(nextStud, n)
		writer.WriteString(fmt.Sprintf("%d\n", cnt))
	}
}


