import sys
from typing import Generator, List, Optional

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

def idxGen(N, M, hist: List[bool], prev: List[int] = []):
    s = len(prev) + 1
    for i in range(N):
        if hist[i]:
            continue

        prev.append(i)
        hist[i] = True

        if s == M:
            yield prev
        else:
            yield from idxGen(N, M, hist, prev)

        prev.pop(-1)
        hist[i] = False

def numsStrFromIdxs(idxs: List[int], nums: List[int]) -> str:
    s = str(nums[idxs[0]])

    for i in range(1, len(idxs)):
        s += f" {nums[idxs[i]]}"

    return s


if __name__ == "__main__":
    N, M = map(int, input().split(" "))
    nums = list(map(int, input().split(" ")))

    nums.sort()

    for idxs in idxGen(N, M, [False for _ in range(N)]):
        output(numsStrFromIdxs(idxs, nums) + "\n")

    flush()
