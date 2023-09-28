type void struct{}

func solution(n int, computers [][]int) int {
    cnt := 0

    toVisit := make(map[int]void)
    for i := 0; i < n; i++ {
        toVisit[i] = void{}
    }

    for i := 0; i < n; i++ {
        if _, ok := toVisit[i]; !ok {
            continue
        }

        cnt++

        next := []int{i}
        for len(next) != 0 {
            newNext := []int{}
            for _, x := range next {
                if _, ok := toVisit[x]; !ok {
                    continue
                }

                delete(toVisit, x)
                for n, v := range computers[x] {
                    if _, ok := toVisit[n]; ok && v == 1 {
                        newNext = append(newNext, n)
                    }
                }
            }

            next = newNext
        }
    }

    return cnt
}
