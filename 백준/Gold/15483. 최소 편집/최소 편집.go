package main

import (
    "bufio"
    "os"
    "fmt"
)

func main() {
    scanner := bufio.NewScanner(os.Stdin)

    // Read words
    scanner.Scan()
    word1 := scanner.Text()
    scanner.Scan()
    word2 := scanner.Text()

    memo := make(map[[2]string]int)
    result := minEditDistance(word1, word2, memo)

    fmt.Println(result)
}

func minEditDistance(word1, word2 string, memo map[[2]string]int) int {
    key := [2]string{word1, word2}

    if dist, ok := memo[key]; ok {
        return dist
    }

    // base condition
    if word1 == word2 {
        memo[key] = 0
        return 0
    }

    if len(word1) == 0 || len(word2) == 0 {
        var dist int
        if len(word1) == 0 {
            dist = len(word2)
        } else {
            dist = len(word1)
        }

        memo[key] = dist
        return dist
    }

    if word1[0] == word2[0] {
        return minEditDistance(word1[1:], word2[1:], memo)
    }

    minDist := len(word1) + len(word2)

    // remove from word1
    removeDist := 1 + minEditDistance(word1[1:], word2, memo)
    if minDist > removeDist {
        minDist = removeDist
    }

    // add to word1 (== remove from word2)
    addDist := 1 + minEditDistance(word1, word2[1:], memo)
    if minDist > addDist {
        minDist = addDist
    }

    // replace word1
    replaceDist := 1 + minEditDistance(word1[1:], word2[1:], memo)
    if minDist > replaceDist {
        minDist = replaceDist
    }

    memo[key] = minDist
    return minDist
}

