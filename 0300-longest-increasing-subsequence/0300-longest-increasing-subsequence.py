class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        decreasingToLeft = [0 for i in range(len(nums))]
        maxTotalLen = 1

        for i in range(len(nums)):
            maxLen = 1

            for j in range(i-1, -1, -1):
                if not nums[j] < nums[i]:
                    continue

                ln = decreasingToLeft[j] + 1
                if maxLen < ln:
                    maxLen = ln

            decreasingToLeft[i] = maxLen
            if maxTotalLen < maxLen:
                maxTotalLen = maxLen

        return maxTotalLen