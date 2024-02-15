class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        tot = 2**n
        result = [[] for _ in range(tot)]

        sameNum, iterNum = int(tot / 2), 1
        for nIdx in range(n):
            for iCnt in range(iterNum):
                for sCnt in range(sameNum):
                    result[iCnt * 2 * sameNum + sameNum + sCnt].append(nums[nIdx])

            sameNum = int(sameNum / 2); iterNum *= 2

        return result

