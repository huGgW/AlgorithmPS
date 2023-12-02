package main

import (
  "fmt"
  "bufio"
  "os"
  "strconv"
  "strings"
)

var cnts map[int]int = map[int]int{
  1: 1,
  2: 2,
  3: 4,
}

func main() {
  reader := bufio.NewReader(os.Stdin)
  writer := bufio.NewWriter(os.Stdout)

  str, _ := reader.ReadString('\n')
  t, _ := strconv.Atoi(strings.TrimSpace(str))

  for i := 0; i < t; i++ {
    str, _ = reader.ReadString('\n')
    x, _ := strconv.Atoi(strings.TrimSpace(str))
    cnt := getCnt(x)
    writer.WriteString(fmt.Sprintf("%d\n", cnt))
  }

  writer.Flush()
}

func getCnt(x int) int {
  cnt, exist := cnts[x]

  if !exist {
    cnt = newCnt(x)
    cnts[x] = cnt
  }

  return cnt
}

func newCnt(x int) int {
  numbers := [3]int{x, 0, 0}
  history := map[[3]int]int{
    numbers: 1,
  }
  queue := [][3]int {numbers}
  cnts := 1

  for len(queue) > 0 {
    currNums := queue[0]
    queue = queue[1:]

    for _, nextNums := range getNextNums(currNums) {
      if _, exists := history[nextNums]; exists {
        continue
      }

      cnt := cntCase(nextNums);
      history[nextNums] = cnt
      cnts += cnt
      queue = append(queue, nextNums)
    }
  }

  return cnts
}

func getNextNums (currNums [3]int) [][3]int {
  nextNums := [][3]int{}
  if currNums[0] >= 2 {
    nextNums = append(nextNums, [3]int{currNums[0]-2, currNums[1]+1, currNums[2]})
  }
  if currNums[0] >= 3 {
    nextNums = append(nextNums, [3]int{currNums[0]-3, currNums[1], currNums[2]+1})
  }

  return nextNums
}

func cntCase (nums [3]int) int {
  return (factorial(nums[0] + nums[1] + nums[2]) / 
    (factorial(nums[0]) * factorial(nums[1]) * factorial(nums[2])))
}

func factorial (n int) int {
  if n == 0 {
    return 1
  } else {
    return n * factorial(n-1)
  }
}
