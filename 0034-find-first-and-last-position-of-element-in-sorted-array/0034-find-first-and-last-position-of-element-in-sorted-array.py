from typing import *
import sys
sys.setrecursionlimit(10**6)

class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        leftInner = self.binarySearch(target - 0.1, False, nums, 0, len(nums)-1)
        rightInner = self.binarySearch(target + 0.1, True, nums, 0, len(nums)-1)

        if not (
            0 <= leftInner < len(nums)
            and 0 <= rightInner < len(nums)
        ) or leftInner > rightInner:
            return [-1, -1]
        else:
            return [leftInner, rightInner]

    def binarySearch(
        self, target: Union[int, float], alterLeft: bool, nums: List[int],
        begin: int, end: int,
    ) -> int:
        if begin > end:
            return end if alterLeft else begin

        mid = (begin + end) // 2
        x = nums[mid]

        if target < x:
            return self.binarySearch(target, alterLeft, nums, begin, mid-1)
        elif x < target:
            return self.binarySearch(target, alterLeft, nums, mid+1, end)
        else:
            return mid
