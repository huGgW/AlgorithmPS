package main

import (
    "fmt"
)

func main() {
    var n int
    fmt.Scanf("%d", &n)

    grid := make([][]bool, n)
    for i := 0; i < n; i++ {
        grid[i] = make([]bool, n)

        var s string
        fmt.Scanln(&s)

        for j, c := range s {
            grid[i][j] = c == 'W'
        }
    }

    for i := 0; i < n; i++ {
        blacks := 0
        whites := 0
        cont := 0
        before := false

        // Columns
        for j := 0; j < n; j++ {
            // Count

            now := grid[i][j]
            if now {
                whites += 1
            } else {
                blacks += 1
            }

            if now == before {
                cont += 1
            } else {
                before = now
                cont = 1
            }

            // Evaluate
            if blacks > n/2 || whites > n/2 || cont >= 3 {
                goto WRONG
            }
        }


        blacks = 0
        whites = 0
        cont = 0
        before = false

        // Rows
        for j := 0; j < n; j++ {
            // Count

            now := grid[j][i]
            if now {
                whites += 1
            } else {
                blacks += 1
            }

            if now == before {
                cont += 1
            } else {
                before = now
                cont = 1
            }

            // Evaluate
            if blacks > n/2 || whites > n/2 || cont >= 3 {
                goto WRONG
            }
        }
    }

    // RIGHT
    fmt.Println(1)
    return

    WRONG:
    fmt.Println(0)
    return
}
