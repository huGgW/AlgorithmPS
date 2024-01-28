package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

type Pos struct {
	i, j int
}

func (p Pos) neighbors() []Pos {
	return []Pos{
		{p.i + 1, p.j},
		{p.i, p.j + 1},
		{p.i - 1, p.j},
		{p.i, p.j - 1},
	}
}

type Step struct {
	Pos
	cnt   int
	broke bool
}

type Queue []Step

func (q *Queue) isEmpty() bool {
	return len(*q) == 0
}
func (q *Queue) enqueue(s Step) {
	*q = append(*q, s)
}
func (q *Queue) dequeue() (Step, bool) {
	if q.isEmpty() {
		return Step{}, false
	}

	s := (*q)[0]
	*q = (*q)[1:]
	return s, true
}

func printMaze(ll [][]int) {
	for _, l := range ll {
		fmt.Println(l)
	}
	fmt.Println()
}

func main() {
	reader := bufio.NewReader(os.Stdin)

	// Read N, M
	line, _ := reader.ReadString('\n')
	lineSplit := strings.Split(line[:len(line)-1], " ")
	N, _ := strconv.Atoi(lineSplit[0])
	M, _ := strconv.Atoi(lineSplit[1])

	// Read maze
	maze := make([][]int, N)
	for i := 0; i < N; i++ {
		maze[i] = make([]int, M)
		line, _ := reader.ReadString('\n')
		for j := 0; j < M; j++ {
			x := 0
			if line[j] == '1' {
				x = 1
			}
			maze[i][j] = x
		}
	}

	// Find path with BFS
	// record dist in maze (negative distance for walls)
	queue := Queue{{Pos{0, 0}, 1, false}}
	notBrokeHistory := map[Pos]int{{0, 0}: 1}
	brokeHistory := map[Pos]int{}

	for !queue.isEmpty() {
		s, _ := queue.dequeue()

		if s.Pos.i == N-1 && s.Pos.j == M-1 {
			continue
		}

		for _, np := range s.neighbors() {
			// boundary check
			if !((0 <= np.i && np.i < len(maze)) &&
				(0 <= np.j && np.j < len(maze[0]))) {
				continue
			}

			// if wall
			if maze[np.i][np.j] == 1 {
				if beforeCnt, found := brokeHistory[np]; !s.broke &&
					isWallWorthBreak(np.i, np.j, maze) &&
					(!found || beforeCnt > s.cnt+1) {
					brokeHistory[np] = s.cnt + 1
					queue.enqueue(Step{Pos{np.i, np.j}, s.cnt + 1, true})
				}
			} else { // if not wall
				var targetHistory map[Pos]int
				if s.broke {
					targetHistory = brokeHistory
				} else {
					targetHistory = notBrokeHistory
				}

				if beforeCnt, found := targetHistory[np]; !found || beforeCnt > s.cnt+1 {
					targetHistory[np] = s.cnt + 1
					queue.enqueue(Step{Pos{np.i, np.j}, s.cnt + 1, s.broke})
				}
			}
		}
	}

	brokeResult, brokeFound := brokeHistory[Pos{N - 1, M - 1}]
	notBrokeResult, notBrokeFound := notBrokeHistory[Pos{N - 1, M - 1}]
	if !brokeFound && !notBrokeFound {
		fmt.Println(-1)
	} else if brokeFound {
		fmt.Println(brokeResult)
	} else if notBrokeFound {
		fmt.Println(notBrokeResult)
	} else {
		fmt.Println(int(math.Min(float64(brokeResult), float64(notBrokeResult))))
	}
}

func isWallWorthBreak(i, j int, maze [][]int) bool {
	return (j > 0 && maze[i][j-1] == 0) || // left
		(j < len(maze[i])-1 && maze[i][j+1] == 0) || // right
		(i > 0 && maze[i-1][j] == 0) || // up
		(i < len(maze)-1 && maze[i+1][j] == 0) // down
}
