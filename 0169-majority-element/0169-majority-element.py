class Solution:
    def majorityElement(self, nums: list[int]) -> int:
        cnts = Counter(nums)
        half = len(nums) // 2
        for (n, c) in cnts.items():
            if c > half:
                return n
        raise Exception
