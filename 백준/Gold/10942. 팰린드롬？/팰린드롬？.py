from typing import *
import sys
sys.setrecursionlimit(10**5)
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

N = int(input())
nums = list(map(int, input().split()))

M = int(input())

histIn: Dict[int, Tuple[int, int]] = {}
histNegOut: Dict[int, Tuple[int, int]] = {}

for _ in range(M):
    s, e = map(int, input().split())
    s -= 1; e -= 1

    inRangeOpt = histIn.get(s+e)
    negOutRangeOpt = histNegOut.get(s+e)

    allSame = True
    f, t = s, e
    while f <= t:
        if f == t:
            break

        if inRangeOpt is not None and inRangeOpt[0] <= f and t <= inRangeOpt[1]:
            break

        if negOutRangeOpt is not None and f <= negOutRangeOpt[0] and negOutRangeOpt[1] <= t:
            allSame = False
            break

        if nums[f] != nums[t]:
            allSame = False
            break

        f += 1; t -= 1

    if allSame:
        if inRangeOpt is None or not (inRangeOpt[0] <= s and e <= inRangeOpt[1]):
            histIn[s + e] = (s, e)
        output(f"1\n")
    else:
        if negOutRangeOpt is None or not (f <= negOutRangeOpt[0] and negOutRangeOpt[1] <= t):
            histNegOut[f + t] = (f, t)
        output(f"0\n")

flush()