from bisect import bisect_left, bisect_right
import sys

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush


def subSumCnt(ls: list[int], N: int, S: int) -> int:
    frontSums = getSums(ls[:N//2])
    backSums = getSums(ls[N//2:])

    # find only on front
    frLIdx = bisect_left(frontSums, S)
    frRIdx = bisect_right(frontSums, S)
    fCnt = frRIdx - frLIdx

    # find only on back
    baLIdx = bisect_left(backSums, S)
    baRIdx = bisect_right(backSums, S)
    bCnt = baRIdx - baLIdx

    # find front and back
    fbCnt = 0
    for fs in frontSums:
        needSum = S - fs
        lidx = bisect_left(backSums, needSum)
        ridx = bisect_right(backSums, needSum)

        fbCnt += ridx - lidx

    return fCnt + bCnt + fbCnt


def getSums(ls: list[int]) -> list[int]:
    sums = []

    for n in ls:
        for i in range(len(sums)):
            sums.append(sums[i] + n)
        sums.append(n)

    sums.sort()
    return sums


if __name__ == "__main__":
    N, S = map(int, input().split())
    ls = [int(s) for s in input().split()]

    cnt = subSumCnt(ls, N, S)
    output(f"{cnt}\n")
    flush()
