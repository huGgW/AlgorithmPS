from typing import *
import sys

sys.setrecursionlimit(10**6)

class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        rowLen, colLen = len(matrix), len(matrix[0])

        # find available rows
        rowSmallKey = lambda r: matrix[r][0]
        rowBigKey = lambda r: matrix[r][-1]

        rowFromIdx = self.bisectLeftWithKey(target, 0, rowLen - 1, rowBigKey)
        rowToIdx = self.bisectRightWithKey(target, 0, rowLen - 1, rowSmallKey) - 1

        # from available rows, find target
        for row in matrix[max(rowFromIdx, 0) : min(rowToIdx + 1, rowLen)]:
            colKey = lambda c: row[c]
            idxOpt = self.binarySearchWithKey(target, 0, colLen - 1, colKey)
            if idxOpt is not None:
                return True
        return False

    def binarySearchWithKey(
        self, target: int, lo: int, hi: int, key: Callable[[int], int]
    ) -> Optional[int]:
        if lo > hi:
            return None

        mid = int((lo + hi) // 2)
        x = key(mid)

        if target < x:
            return self.binarySearchWithKey(target, lo, mid - 1, key)
        elif x < target:
            return self.binarySearchWithKey(target, mid + 1, hi, key)
        else:
            return mid

    def bisectLeftWithKey(
        self, target: int, lo: int, hi: int, key: Callable[[int], int]
    ) -> int:
        if lo > hi:
            return lo

        mid = int((lo + hi) // 2)
        x = key(mid)

        if target <= x:
            return self.bisectLeftWithKey(target, lo, mid - 1, key)
        elif x < target:
            return self.bisectLeftWithKey(target, mid + 1, hi, key)

    def bisectRightWithKey(
        self, target: int, lo: int, hi: int, key: Callable[[int], int]
    ) -> int:
        if lo > hi:
            return lo

        mid = int((lo + hi) // 2)
        x = key(mid)

        if target < x:
            return self.bisectRightWithKey(target, lo, mid - 1, key)
        elif x <= target:
            return self.bisectRightWithKey(target, mid + 1, hi, key)
