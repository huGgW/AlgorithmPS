package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

type Graph map[int]*map[int]int

func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)

	// read testcase number
	str, _ := reader.ReadString('\n')
	tcNum, _ := strconv.Atoi(str[:len(str)-1])
	results := make([]bool, tcNum)

	for tc := 0; tc < tcNum; tc++ {
		// read vertNum, loadNum, wormNum
		vertNum, loadNum, wormNum := readThreeInt(reader)

		// init graph
		graph := make(Graph, vertNum)
		for v := 1; v <= vertNum; v++ {
			graph[v] = &(map[int]int{v: 0})
		}

		// init load
		for l := 0; l < loadNum; l++ {
			s, e, t := readThreeInt(reader)
			updateToMinEdge(&graph, s, e, t)
			updateToMinEdge(&graph, e, s, t)
		}
		// init wormhole
		for w := 0; w < wormNum; w++ {
			s, e, t := readThreeInt(reader)
			updateToMinEdge(&graph, s, e, -t)
		}

		start := 1
		result := false
		for start <= vertNum {
			negExists, dists := negativeCycleExists(graph, start, vertNum)

			if negExists {
				result = negExists
				break
			}

			// check again from inf dist, for disjoint set case
			for start = start + 1; start <= vertNum && dists[start] != nil; start++ {
			}
		}
		results[tc] = result
	}

	for _, result := range results {
		if result {
			writer.WriteString("YES\n")
		} else {
			writer.WriteString("NO\n")
		}
	}
	writer.Flush()
}

func negativeCycleExists(graph Graph, start, vertNum int) (bool, map[int]*int) {
	// init dist
	dists := make(map[int]*int, vertNum) // dist from start
	for v := 1; v <= vertNum; v++ {
		if v == start {
			dists[v] = new(int)
			*dists[v] = 0
		} else {
			dists[v] = nil
		}
	}

	// bellman ford algorithm
	for i := 0; i < vertNum-1; i++ {
		p := start
		for {
			if dists[p] != nil {
				for q, edgeDist := range *(graph[p]) {
					if dists[q] == nil || *(dists[q]) > *(dists[p])+edgeDist {
						dists[q] = new(int)
						*(dists[q]) = *(dists[p]) + edgeDist
					}
				}
			}
			p = (p % vertNum) + 1
			if p == start {
				break
			}
		}
	}

	// check negative cycle
	p := start
	for {
		if dists[p] != nil {
			for q, edgeDist := range *(graph[p]) {
				if dists[q] == nil || *(dists[q]) > *(dists[p])+edgeDist {
					return true, dists
				}
			}
		}
		p = (p % vertNum) + 1
		if p == start {
			break
		}
	}
	return false, dists
}

func readThreeInt(reader *bufio.Reader) (int, int, int) {
	str, _ := reader.ReadString('\n')
	strList := strings.Split(str[:len(str)-1], " ")
	nums := [3]int{}
	for i := 0; i < 3; i++ {
		nums[i], _ = strconv.Atoi(strList[i])
	}

	return nums[0], nums[1], nums[2]
}

func updateToMinEdge(graphPtr *Graph, from, to, dist int) {
	origin, found := (*(*graphPtr)[from])[to]
	if (found && origin > dist) || !found {
		(*(*graphPtr)[from])[to] = dist
	}
}
