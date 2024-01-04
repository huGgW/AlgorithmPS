import sys
from typing import Generator, List, Optional

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

def idxGen(N, M, prev: List[int] = []):
    s = len(prev) + 1
    for i in range(N):
        if i in prev:
            continue

        prev.append(i)

        if s == M:
            yield prev
        else:
            yield from idxGen(N, M, prev)

        prev.pop(-1)

def numsStrFromIdxs(idxs: List[int], nums: List[int]) -> str:
    s = str(nums[idxs[0]])

    for i in range(1, len(idxs)):
        s += f" {nums[idxs[i]]}"

    return s


if __name__ == "__main__":
    N, M = map(int, input().split(" "))
    nums = list(map(int, input().split(" ")))

    nums.sort()

    for idxs in idxGen(N, M):
        output(numsStrFromIdxs(idxs, nums) + "\n")

    flush()
