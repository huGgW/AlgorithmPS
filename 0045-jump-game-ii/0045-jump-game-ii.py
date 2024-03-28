
class Solution:
    def jump(self, nums: List[int]) -> int:
        cnt = 0
        pos = 0
        while pos < len(nums):
            if pos >= len(nums)-1:
                return cnt

            if (maxAvailableNP := pos + nums[pos]) >= len(nums)-1:
                return cnt+1

            maxNP, maxNNP = -1, -1
            for np in range(pos+1, maxAvailableNP+1):
                nnp = np + nums[np]
                if nnp > maxNNP:
                    maxNP = np
                    maxNNP = nnp

            pos = maxNP
            cnt += 1

        return cnt

        