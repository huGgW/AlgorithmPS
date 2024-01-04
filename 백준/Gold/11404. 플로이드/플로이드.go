package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

type BusLine struct {
	from int
	to   int
}

func main() {
	// Initialize

	reader := bufio.NewReader(os.Stdin)
	var line string

	line, _ = reader.ReadString('\n')
	n, _ := strconv.Atoi(strings.TrimSpace(line))

	line, _ = reader.ReadString('\n')
	m, _ := strconv.Atoi(strings.TrimSpace(line))

	busLines := make(map[BusLine]int, m)

	for i := 0; i < m; i++ {
		line, _ = reader.ReadString('\n')
		infoStrs := strings.Split(strings.TrimSpace(line), " ")
		from, _ := strconv.Atoi(infoStrs[0])
		to, _ := strconv.Atoi(infoStrs[1])
		cost, _ := strconv.Atoi(infoStrs[2])

		busLine := BusLine{from - 1, to - 1}

		if c, ok := busLines[busLine]; ok {
			if cost < c {
				busLines[busLine] = cost
			}
		} else {
			busLines[busLine] = cost
		}
	}

  // Floyd-Warshall
	dists := make([][]int, n)
	for i := 0; i < n; i++ {
		dists[i] = make([]int, n)
	}

  // connect direct
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if j == i {
				continue
			}

			if c, ok := busLines[BusLine{i, j}]; 
			ok && (dists[i][j] == 0 || dists[i][j] > c) {
			  dists[i][j] = c
			}
		}
  }

  // connect indirect
	for i := 0; i < n; i++ {
    for h := 0; h < n; h++ {
      if h == i || dists[h][i] == 0 {
        continue
      }
      for j := 0; j < n; j++ {
        if j == h || j == i || dists[i][j] == 0 {
          continue
        }

        if dists[h][j] == 0 || dists[h][j] > dists[h][i] + dists[i][j] {
          dists[h][j] = dists[h][i] + dists[i][j]
        }
      }
    }
	}

	// Print output
  writer := bufio.NewWriter(os.Stdout)
  for _, distRow := range dists {
    for l, dist := range distRow {
      writer.WriteString(strconv.Itoa(dist))
      if l < len(distRow) - 1 {
        writer.WriteString(" ")
      } else {
        writer.WriteString("\n")
      }
    }
  }
  writer.Flush()
}
