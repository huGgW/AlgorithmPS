
class Solution:
    def canJump(self, nums: List[int]) -> bool:
        lastNonZero = -1
        lastZero = -1

        for i in range(len(nums)-1):
            jp = nums[i]

            if i + jp >= len(nums)-1:
                return True

            if jp == 0 and lastZero <= i and lastNonZero <= lastZero:
                return False

            nextVal = nums[i + jp]
            if nextVal == 0:
                lastZero = max(lastZero, i+jp)
            else:
                lastNonZero = max(lastNonZero, i+jp)

        return True
