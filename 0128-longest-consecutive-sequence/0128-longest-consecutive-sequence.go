type Empty struct{}
type Set map[int]Empty

func longestConsecutive(nums []int) int {
    consecs := Set{}
    for _, n := range nums {
        consecs[n] = Empty{}
    }

    maxConsecs := 0
    for _, n := range nums {
        if _, ok := consecs[n]; !ok {
            continue
        }

        tmpConsecs := 1
        delete(consecs, n)

        dec := n-1
        for {
            if _, ok := consecs[dec]; !ok {
                break
            }

            tmpConsecs++
            delete(consecs, dec)
            dec--
        }

        inc := n+1
        for {
            if _, ok := consecs[inc]; !ok {
                break
            }

            tmpConsecs++
            delete(consecs, inc)
            inc++
        }

        if tmpConsecs > maxConsecs {
            maxConsecs = tmpConsecs
        }
    }

    return maxConsecs
}