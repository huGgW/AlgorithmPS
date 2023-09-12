import (
    "sort"
)

type void struct{}

func solution(n int, lost []int, reserve []int) int {
    // Sort lost and reserve
    sort.Sort(sort.IntSlice(lost))
    sort.Sort(sort.IntSlice(reserve))

    lostSet := make(map[int]void)
    reserveSet := make(map[int]void)

    for _, v := range lost {
        lostSet[v] = void{}
    }
    for _, v := range reserve {
        reserveSet[v] = void{}
    }

    // Remove duplicates
    for k, _ := range lostSet {
        if _, ok := reserveSet[k]; ok {
            delete(lostSet, k)
            delete(reserveSet, k)
        }
    }


    maximumFillLost := maximumFillLost(lostSet, reserveSet)
    return n - len(lostSet) + maximumFillLost
}

func maximumFillLost(lostSet map[int]void, reserveSet map[int]void) int {
    if len(lostSet) == 0 {
        return 0
    }

    lostSetClone := clone(lostSet)

    key := 0
    for k, _ := range lostSetClone {
        key = k
        delete(lostSetClone, k)
        break
    }

    smallReserveSet := clone(reserveSet)
    bigReserveSet := clone(reserveSet)

    small := 0;
    if _, ok := smallReserveSet[key-1]; ok {
        delete(smallReserveSet, key-1)
        small += 1
    }
    small += maximumFillLost(lostSetClone, smallReserveSet)

    big := 0;
    if _, ok := bigReserveSet[key+1]; ok {
        delete(bigReserveSet, key+1)
        big += 1
    }
    big += maximumFillLost(lostSetClone, bigReserveSet)

    if small > big {
        return small
    } else {
        return big
    }
}

func clone(m map[int]void) map[int]void {
    cloneMap := make(map[int]void)
    for k, v := range m {
        cloneMap[k] = v
    }
    return cloneMap
}

