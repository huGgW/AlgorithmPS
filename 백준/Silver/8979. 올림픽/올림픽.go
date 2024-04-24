package main

import (
	"bufio"
	"container/heap"
	"os"
	"strconv"
	"strings"
)

type Medals struct {
	Number int
	Gold   int
	Silver int
	Bronze int
}

func NewMedals(s string) Medals {
	ls := strings.Split(s, " ")
	number, _ := strconv.Atoi(ls[0])
	gold, _ := strconv.Atoi(ls[1])
	silver, _ := strconv.Atoi(ls[2])
	bronze, _ := strconv.Atoi(ls[3])
	return Medals{number, gold, silver, bronze}
}
func CmpMedal(m, om Medals) int {
	gcmp := m.Gold - om.Gold
	if gcmp != 0 {
		return gcmp
	}

	scmp := m.Silver - om.Silver
	if scmp != 0 {
		return scmp
	}

	bcmp := m.Bronze - om.Bronze
	return bcmp
}

type MedalHeap []Medals

func (mh MedalHeap) Len() int {
	return len(mh)
}

// Large medal first
func (mh MedalHeap) Less(i, j int) bool {
	return (-1 * CmpMedal(mh[i], mh[j])) < 0
}
func (mh *MedalHeap) Swap(i, j int) {
	(*mh)[i], (*mh)[j] = (*mh)[j], (*mh)[i]
}
func (mh *MedalHeap) Push(x any) {
	*mh = append(*mh, x.(Medals))
}
func (mh *MedalHeap) Pop() any {
	elem := (*mh)[len(*mh)-1]
	*mh = (*mh)[:len(*mh)-1]
	return elem
}

func main() {
	sc := bufio.NewScanner(os.Stdin)
	wr := bufio.NewWriter(os.Stdout)
	defer wr.Flush()

	sc.Scan()
	ls := strings.Split(sc.Text(), " ")
	N, _ := strconv.Atoi(ls[0])
	K, _ := strconv.Atoi(ls[1])

	var medalHeap *MedalHeap = &MedalHeap{}
	for i := 0; i < N; i++ {
		sc.Scan()
		medal := NewMedals(sc.Text())
		medalHeap.Push(medal)
	}
	heap.Init(medalHeap)

	var before *Medals = nil
	rate := 0
	for medalHeap.Len() > 0 {
		md := heap.Pop(medalHeap).(Medals)

		if !(before != nil && CmpMedal(*before, md) == 0) {
			rate++
		}

		if md.Number == K {
			break
		}

		before = &md
	}

	wr.WriteString(strconv.Itoa(rate))
}
