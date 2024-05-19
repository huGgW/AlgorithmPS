from bisect import bisect_right

class Solution:
    def countPairs(self, nums1: list[int], nums2: list[int]) -> int:
        diffs = [nums1[i] - nums2[i] for i in range(len(nums1))]
        diffs.sort()

        cnt = 0
        for (i, n) in enumerate(diffs):
            bsRight = bisect_right(diffs, -n, i+1)
            cnt += len(diffs) - bsRight

        return cnt
