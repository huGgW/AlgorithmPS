package main

import "fmt"

func main() {
    zeros := []int{1, 0}
    ones := []int{0, 1}

    var t int
    fmt.Scanf("%d", &t)

    for i := 0; i < t; i++ {
        var x int
        fmt.Scanf("%d", &x)

        zeroCnt, oneCnt := getCnts(x, zeros, ones)
        fmt.Printf("%d %d\n", zeroCnt, oneCnt)
    }
}

func getCnts(x int, zeros, ones []int) (int, int) {
    n := len(zeros)

    if x >= n {
        for i := n; i <= x; i++ {
            zeros = append(zeros, zeros[i-1] + zeros[i-2])
            ones = append(ones, ones[i-1] + ones[i-2])
        }
    }

    return zeros[x], ones[x]
}
