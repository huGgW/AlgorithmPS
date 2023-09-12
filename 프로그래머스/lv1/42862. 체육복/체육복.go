import (
    "sort"
)

type void struct{}

func solution(n int, lost []int, reserve []int) int {
  // Remove duplicates
    lostSet := make(map[int]void)
    reserveSet := make(map[int]void)

    for _, v := range lost {
        lostSet[v] = void{};
    }
    for _, v := range reserve {
        reserveSet[v] = void{};
    }

    for k, _ := range lostSet {
        if _, ok := reserveSet[k]; ok {
            delete(lostSet, k)
            delete(reserveSet, k)
        }
    }

    keys := make([]int, len(lostSet))
    for k, _ := range lostSet {
        keys = append(keys, k);
    }
    sort.Sort(sort.IntSlice(keys));

    for _, v := range keys {
        if _, ok := reserveSet[v - 1]; ok {
            delete(lostSet, v);
            delete(reserveSet, v-1);
        } else if _, ok := reserveSet[v + 1]; ok {
            delete(lostSet, v);
            delete(reserveSet, v+1);
        }
    }

    return n - len(lostSet);
}
