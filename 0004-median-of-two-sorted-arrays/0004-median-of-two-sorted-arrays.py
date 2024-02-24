from typing import *
import sys 
sys.setrecursionlimit(10**6)

class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        for x in nums2:
            idx = self.binarySearch(x, 0, len(nums1)-1, nums1)
            nums1.insert(idx, x)

        if len(nums1) % 2 == 0:
            return (nums1[int(len(nums1) / 2)] + nums1[int(len(nums1) / 2) - 1]) / 2
        else:
            return nums1[int(len(nums1) // 2)]

    def binarySearch(self, target: int, begin: int, end: int, nums: List[int]) -> int:
        if begin > end:
            return begin

        mid = (begin + end) // 2
        x = nums[mid]

        if x < target:
            return self.binarySearch(target, mid+1, end, nums)
        elif x > target:
            return self.binarySearch(target, begin, mid-1, nums)
        else:
            return mid
