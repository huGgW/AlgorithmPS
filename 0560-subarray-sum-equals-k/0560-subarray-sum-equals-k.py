class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        sumsFromOne: Dict[int, List[int]] = {}
        sum = 0
        for i, n in enumerate(nums):
            sum += n
            if sum in sumsFromOne:
                sumsFromOne[sum].append(i)
            else:
                sumsFromOne[sum] = [i]

        cnt = 0
        plusSum = 0
        for i, n in enumerate(nums):
            # subarray starts from i
            estimate = k + plusSum
            if estimate in sumsFromOne:
                hp = sumsFromOne[estimate]
                while hp and hp[0] < i:
                    del hp[0]

                if hp:
                    cnt += len(hp)
                else:
                    del sumsFromOne[estimate]

            # upate plusSum
            plusSum += n

        return cnt