from typing import *
import sys
sys.setrecursionlimit(10**6)

class Solution:
    def partition(self, s: str) -> List[List[str]]:
        self.chars = list(s)
        self.memo: List[List[Optional[bool]]] = \
            [[None for _ in range(len(self.chars))] for _ in range(len(self.chars))]
        self.partitions: List[List[Tuple[int, int]]] = []

        # Analyze all substring which is palindrome
        for l in range(1, len(self.chars) + 1):
            for i in range(len(self.chars) - l + 1):
                j = i + l - 1
                self.fillIsPalin(i, j)

        # partition
        self.partitionRecursive(0, [])

        return list(map(
            lambda tuples: list(map(
                lambda t: ''.join(self.chars[t[0]:t[1]+1]),
                tuples
            )),
            self.partitions
        ))

    def fillIsPalin(self, b: int, e: int):
        if self.memo[b][e] is not None:
            return

        bb, ee = b, e
        result = True
        while bb < ee:
            if self.memo[bb][ee] is not None:
                result = self.memo[bb][ee]
                break

            if self.chars[bb] != self.chars[ee]:
                result = False
                break

            bb += 1; ee -= 1

        self.memo[b][e] = result

    def partitionRecursive(self, b: int, tmp: List[Tuple[int, int]]):
        if b == len(self.chars):
            self.partitions.append(tmp.copy())
            return

        for e in range(b, len(self.chars)):
            if not self.memo[b][e]:
                continue

            tmp.append((b, e))
            self.partitionRecursive(e+1, tmp)
            tmp.pop(-1)
