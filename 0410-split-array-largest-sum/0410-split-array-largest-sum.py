from pprint import pprint
from bisect import *

class Solution:
    def splitArray(self, nums: List[int], k: int) -> int:
        n = len(nums)
        sums = [0] + nums
        for i in range(1, len(sums)):
            sums[i] += sums[i-1]

        minMaxSum = sums[-1] // k
        maxMaxSum = sums[-1]

        while minMaxSum <= maxMaxSum:
            mid = (minMaxSum + maxMaxSum) // 2
            fitResult = self.isMidAvailable(sums, mid, k)

            if fitResult >= 0: # too big or fit
                maxMaxSum = mid-1
            else: # too small
                minMaxSum = mid+1

        return maxMaxSum
                



    # 1: too big,  0: fit, -1: too small
    def isMidAvailable(self, sums: List[int], sumBoundary: int, k: int) -> int:
        cnt = 0
        n = len(sums) - 1
        beg = 0

        while beg < n:
            target = sumBoundary + sums[beg]
            optimalSumIdx = bisect_left(sums, target, lo=beg+1) - 1
            
            if optimalSumIdx < beg+1:
                return -1

            beg = optimalSumIdx-1 + 1
            cnt += 1

        return 0 if cnt == k \
        else -1 if cnt > k \
        else 1 # cnt < k


        pass






