package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

type Set struct {
	container [20]bool
}

func newSet() *Set {
	s := Set{container: [20]bool{}}
	s.empty()
	return &s
}

func (s *Set) add(x int8) {
	s.container[x-1] = true
}

func (s *Set) remove(x int8) {
	s.container[x-1] = false
}

func (s *Set) check(x int8) bool {
	return s.container[x-1]
}

func (s *Set) toggle(x int8) {
	s.container[x-1] = !s.container[x-1]
}

func (s *Set) all() {
	for i := 0; i < 20; i++ {
		s.container[i] = true
	}
}

func (s *Set) empty() {
	for i := 0; i < 20; i++ {
		s.container[i] = false
	}
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	sPtr := newSet()

	input, _ := reader.ReadString('\n')
	m, _ := strconv.Atoi(strings.TrimSpace(input))

	for i := 0; i < m; i++ {
		input, _ = reader.ReadString('\n')
		splInput := strings.Split(input, " ")
		command := strings.TrimSpace(splInput[0])
		switch command {
		case "add":
			x, _ := strconv.Atoi(strings.TrimSpace(splInput[1]))
			sPtr.add(int8(x))
		case "remove":
			x, _ := strconv.Atoi(strings.TrimSpace(splInput[1]))
			sPtr.remove(int8(x))
		case "check":
			x, _ := strconv.Atoi(strings.TrimSpace(splInput[1]))
			isExist := sPtr.check(int8(x))
			if isExist {
				writer.WriteString("1\n")

			} else {
				writer.WriteString("0\n")
			}
		case "toggle":
			x, _ := strconv.Atoi(strings.TrimSpace(splInput[1]))
			sPtr.toggle(int8(x))
		case "all":
			sPtr.all()
		case "empty":
			sPtr.empty()
		}
	}

	writer.Flush()
}

