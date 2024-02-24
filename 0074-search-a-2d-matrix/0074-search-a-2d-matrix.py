from typing import *
import sys 
sys.setrecursionlimit(10**6)

class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        def get(i: int) -> int:
            x = i // len(matrix[0])
            y = i % len(matrix[0])
            return matrix[x][y]

        return self.binarySearchExists(target, 0, len(matrix) * len(matrix[0]) - 1, get)

    def binarySearchExists(self, target: int, begin: int, end: int, get: Callable[[int], int]) -> bool:
        if begin > end:
            return False

        mid = (begin + end) // 2
        x = get(mid)

        if x < target:
            return self.binarySearchExists(target, mid+1, end, get)
        elif x > target:
            return self.binarySearchExists(target, begin, mid - 1, get)
        else:
            return True
