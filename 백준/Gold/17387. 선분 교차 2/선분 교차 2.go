package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type Point struct {
	x, y int64
}

// key: <x, -y>
func (p Point) isSmallerThan(op Point) bool {
	return p.x < op.x || p.x == op.x && p.y > op.y
}

type Vector struct {
	dx, dy int64
}

type Line struct {
	ps, pb Point
}

func newLine(x1, y1, x2, y2 int64) Line {
	p1 := Point{x1, y1}
	p2 := Point{x2, y2}
	if p1.isSmallerThan(p2) {
		return Line{p1, p2}
	} else {
		return Line{p2, p1}
	}
}

func (l Line) gradient() Vector {
	return Vector{l.pb.x - l.ps.x, l.pb.y - l.ps.y}
}

func main() {
	var line string
	reader := bufio.NewReader(os.Stdin)

	line, _ = reader.ReadString('\n')
	lineSplit := strings.Split(line[:len(line)-1], " ")
	x11, _ := strconv.ParseInt(lineSplit[0], 10, 64)
	y11, _ := strconv.ParseInt(lineSplit[1], 10, 64)
	x21, _ := strconv.ParseInt(lineSplit[2], 10, 64)
	y21, _ := strconv.ParseInt(lineSplit[3], 10, 64)

	line, _ = reader.ReadString('\n')
	lineSplit = strings.Split(line[:len(line)-1], " ")
	x12, _ := strconv.ParseInt(lineSplit[0], 10, 64)
	y12, _ := strconv.ParseInt(lineSplit[1], 10, 64)
	x22, _ := strconv.ParseInt(lineSplit[2], 10, 64)
	y22, _ := strconv.ParseInt(lineSplit[3], 10, 64)

	l1 := newLine(x11, y11, x21, y21)
	l2 := newLine(x12, y12, x22, y22)

	var intersects bool
	intersects, isSameLine := analyzeLine(l1, l2)

	if isSameLine {
		intersects = sameLineIntersects(l1, l2)
	}

	if intersects {
		fmt.Println(1)
	} else {
		fmt.Println(0)
	}
}

func calcCCW(v1, v2 Vector) int64 {
	return v1.dx*v2.dy - v2.dx*v1.dy
}

func analyzeLine(l1, l2 Line) (bool, bool) {
	points := []Point{l1.ps, l2.ps, l1.pb, l2.pb}

	ccwWasPlus := false
	allCCWis0 := true
	for i := 0; i < 4; i++ {
		vf := Vector{points[(i+1)%4].x - points[i].x, points[(i+1)%4].y - points[i].y}
		vt := Vector{points[(i+2)%4].x - points[(i+1)%4].x, points[(i+2)%4].y - points[(i+1)%4].y}

		ccw := calcCCW(vf, vt)

		if ccw < 0 {
			if allCCWis0 || !ccwWasPlus {
				allCCWis0 = false
				ccwWasPlus = false
			} else {
				return false, false
			}
		} else if ccw > 0 {
			if allCCWis0 || ccwWasPlus {
				allCCWis0 = false
				ccwWasPlus = true
			} else {
				return false, false
			}
		}
	}

	if allCCWis0 {
		return false, true
	} else {
		return true, false
	}
}

func sameLineIntersects(l1, l2 Line) bool {
	if l1.gradient().dx == 0 { // vertical line
		var smally1, bigy1, smally2, bigy2 int64
		if l1.ps.y < l1.pb.y {
			smally1, bigy1 = l1.ps.y, l1.pb.y
		} else {
			smally1, bigy1 = l1.pb.y, l1.ps.y
		}
		if l2.ps.y < l2.pb.y {
			smally2, bigy2 = l2.ps.y, l2.pb.y
		} else {
			smally2, bigy2 = l2.pb.y, l2.ps.y
		}

		return !(bigy1 < smally2 || bigy2 < smally1)
	}

	// not vertical
	return !(l1.pb.x < l2.ps.x || l2.pb.x < l1.ps.x)
}
