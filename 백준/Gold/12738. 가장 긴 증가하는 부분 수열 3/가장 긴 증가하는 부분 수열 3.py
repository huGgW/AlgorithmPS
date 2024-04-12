from typing import *
from bisect import bisect_left
import sys
sys.setrecursionlimit(10**6)

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

def LIS(nums: List[int]) -> int:
    # inplace
    end = -1
    for x in nums:
        if end == -1 or nums[end] < x:
            end += 1
            nums[end] = x
            continue

        bisectIdx = bisect_left(nums, x, hi=end)
        nums[bisectIdx] = x

    return end+1

if __name__ == "__main__":
    n = int(input())
    nums = [int(s) for s in input().split()]
    lis = LIS(nums)
    output(f"{lis}\n")
    flush()
