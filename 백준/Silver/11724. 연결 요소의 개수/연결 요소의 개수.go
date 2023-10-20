package main

import (
	"bufio"
	"os"
	"fmt"
)

func main() {
	in := bufio.NewReader(os.Stdin)
	out := bufio.NewWriter(os.Stdout)

	// Read 1st line
	n, m := getTwoIntsFromStdin(in)

	// Init graph
	graph := make(map[int][]int)
	visited := make(map[int]bool)

	for i := 1; i <= m; i++ {
		x, y := getTwoIntsFromStdin(in)
		if nodes, ok := graph[x]; ok {
			graph[x] = append(nodes, y)
		} else {
			visited[x] = false
			graph[x] = []int{y}
		}

		if nodes, ok := graph[y]; ok {
			graph[y] = append(nodes, x)
		} else {
			visited[y] = false
			graph[y] = []int{x}
		}
	}

	cnt := 0

	// Traverse
	for i := 1; i <= n; i++ {
		if !visited[i] {
			traverseFromNode(i, graph, visited)
			cnt += 1
		}
	}

	out.WriteString(fmt.Sprintf("%d\n", cnt))
	out.Flush()
}

func traverseFromNode(node int, graph map[int][]int, visited map[int]bool) {
	next := graph[node]
	visited[node] = true

	for _, n := range next {
		if !visited[n] {
			traverseFromNode(n, graph, visited)
		}
	}
}

func getTwoIntsFromStdin(in *bufio.Reader) (int, int) {
	var n, m int

	s, _ := in.ReadString('\n')
	fmt.Sscanf(s, "%d %d", &n, &m)

	return n, m
}
