from typing import *
import sys
sys.setrecursionlimit(10**6)

SMALLEST = -2**31 - 1

class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        stack: List[Tuple[int, int]] = [(0, len(nums)-1)]
        maxProd = SMALLEST

        while stack:
            b, e = stack.pop(-1)
            totalProd = 1
            negOrZeroIdxs: List[int] = []
    
            for i in range(b, e+1):
                totalProd *= nums[i]

                if nums[i] == 0:
                    negOrZeroIdxs = [i]
                elif nums[i] < 0 and (
                    not negOrZeroIdxs or nums[negOrZeroIdxs[-1]] != 0
                ):
                    negOrZeroIdxs.append(i)

            if maxProd < totalProd:
                maxProd = totalProd

            if totalProd <= 0:
                for i in negOrZeroIdxs:
                    # right
                    if e > i:
                        stack.append((i + 1, e))
    
                    # left
                    if b < i:
                        stack.append((b, i - 1))

        return maxProd
