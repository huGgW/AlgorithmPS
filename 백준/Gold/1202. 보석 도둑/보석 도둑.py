from typing import *
from dataclasses import dataclass, field
import heapq
import bisect
import sys

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush
sys.setrecursionlimit(10**6)


@dataclass(order=True)
class Diamond:
    sort_index: int = field(init=False)
    m: int
    v: int

    # for heap sorting
    def __post_init__(self):
        self.sort_index = -self.v


def main(diamonds: List[Diamond], bags: List[int]) -> int:
    # sort diamonds and bags
    diamonds.sort(key=lambda d: d.m)
    bags.sort()
    diamondHeap: List[Diamond] = []

    totalVal = 0
    diamondPtr = 0
    for bag in bags:
        while diamondPtr < len(diamonds) and diamonds[diamondPtr].m <= bag:
            heapq.heappush(diamondHeap, diamonds[diamondPtr])
            diamondPtr += 1

        if diamondHeap:
            largestDiamond = heapq.heappop(diamondHeap)
            totalVal += largestDiamond.v

    return totalVal


if __name__ == "__main__":
    N, K = map(int, input().split())
    diamonds = [
        Diamond(*(map(int, input().split()))) \
        for _ in range(N)
    ]
    bags = [int(input()) for _ in range(K)]

    result = main(diamonds, bags)
    print(result)

