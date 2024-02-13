from typing import *
import sys
sys.setrecursionlimit(10**6)

class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        # init class variable
        self.n = n
        self.bitmasks = [Bitmask(self.n) for _ in range(self.n)]
        self.queenColsOfRow: List[int] = []
        self.result: List[List[str]] = []

        self.traverseRow(0)
        return self.result

    def traverseRow(self, i: int):
        if i == self.n:
            self.addResult()
            return

        for j in range(self.n):
            if not self.allNotContains(i, j):
                continue

            for mi, mj in self.yieldQueenAttackable(i, j):
                self.bitmasks[i].add(mi, mj)
            self.queenColsOfRow.append(j)
            self.traverseRow(i+1)
            self.queenColsOfRow.pop(-1)
            for mi, mj in self.yieldQueenAttackable(i, j):
                self.bitmasks[i].discard(mi, mj)

    def allNotContains(self, i: int, j: int) -> bool:
        for bm in self.bitmasks:
            if bm.contains(i, j):
                return False

        return True

    def yieldQueenAttackable(self, i: int, j: int) -> Generator[Tuple[int, int], None, None]:
        for mi in range(self.n):
            yield((mi, j))

        for mj in range(self.n):
            yield((i, mj))

        mi, mj = i, j
        while True:
            mi += 1; mj += 1
            if mi == self.n or mj == self.n:
                break
            yield((mi, mj))

        mi, mj = i, j
        while True:
            mi -= 1; mj -= 1
            if mi == -1 or mj == -1:
                break
            yield((mi, mj))

        mi, mj = i, j
        while True:
            mi += 1; mj -= 1
            if mi == self.n or mj == -1:
                break
            yield((mi, mj))

        mi, mj = i, j
        while True:
            mi -= 1; mj += 1
            if mi == -1 or mj == self.n:
                break
            yield((mi, mj))



    def addResult(self):
        res = [['.' for _ in range(self.n)] for _ in range(self.n)]
        for i, j in enumerate(self.queenColsOfRow):
            res[i][j] = 'Q'

        self.result.append(list(map(
            lambda ls: ''.join(ls),
            res
        )))

        

class Bitmask:
    def __init__(self, n: int):
        self.n = n
        self.mask = 0
    
    def _2dTo1d(self, i: int, j: int) -> int:
        return self.n * i + j

    def add(self, i: int, j: int):
        self.mask = self.mask | 1 << (self._2dTo1d(i, j))

    def discard(self, i: int, j: int):
        self.mask = self.mask & ~(1 << (self._2dTo1d(i, j)))

    def contains(self, i: int, j: int) -> bool:
        return bool(self.mask & 1 << (self._2dTo1d(i, j)))

