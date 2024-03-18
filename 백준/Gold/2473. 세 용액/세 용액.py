import math
import bisect

from typing import *
import sys
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

def main(vals: List[int], N: int) -> Tuple[int, int, int]:
    vals.sort()

    absSum = math.inf
    state: Tuple[int, int, int] = (-1, -1, -1)

    for b in range(0, N-2):
        for e in range(N-1, b+1, -1):
            needs = -(vals[e] + vals[b])
            rightBisectIdx = bisect.bisect_right(
                vals,
                needs,
                b+1,
                e-1,
            )

            smallSum = vals[rightBisectIdx-1] - needs \
                if rightBisectIdx-1 > b else math.inf
            bigSum = vals[rightBisectIdx] - needs \
                if rightBisectIdx < e else math.inf
            optTmpSum = smallSum if abs(smallSum) < abs(bigSum) else bigSum
            optTmpIdx = rightBisectIdx-1 if abs(smallSum) < abs(bigSum) else rightBisectIdx

            if abs(optTmpSum) < absSum:
                absSum = abs(optTmpSum)
                state = (vals[b], vals[optTmpIdx], vals[e])
                if optTmpSum == 0:
                    return state

    return state


if __name__ == "__main__":
    N = int(input())
    vals = [int(x) for x in input().split()]

    a, b, c = main(vals, N)
    output(f"{a} {b} {c}")
    flush()
